package com.naesala.golfscorecard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private Hole[] mHoles;
    private static final int HOLES_AMOUNT = 18;

    private RecyclerView mRecyclerView;
    private HoleAdapter mHoleAdapter;

    private static final String PREFS_FILE = "com.naesala.golfscorecard.preferences";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String[] mStrokesKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        initializeScoreboard();
        setRecyclerView();
    }

    private void initializeScoreboard() {
        mHoles = new Hole[HOLES_AMOUNT];
        mStrokesKeys = new String[mHoles.length];

        for(int i = 0; i < mHoles.length; i++) {
            //Generates unique key values for individual Hole strokes
            //For SharedPreferences purposes
            mStrokesKeys[i] = "Strokes of Hole " + i;
            //Checks to see if there is a saved stroke value (or else it defaults to 0)
            mHoles[i] = new Hole("Hole " + (i + 1) + ":",
                    mSharedPreferences.getInt(mStrokesKeys[i], 0));
        }
    }

    private void setRecyclerView() {
        mHoleAdapter = new HoleAdapter(mHoles);
        mRecyclerView.setAdapter(mHoleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
    }

    private void clearStrokes() {
        for(int i = 0; i < mHoles.length; i++)
            mHoles[i].setStrokes(0);
        mEditor.clear();
        mEditor.apply();
        //Notifies the adapter that the data has changed (apparently re-loads this data)
        mHoleAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        for(int i = 0; i < mHoles.length; i++) {
            mEditor.putInt(mStrokesKeys[i], mHoles[i].getStrokes());
        }
        mEditor.apply();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.clear_strokes) {
            clearStrokes();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
