package com.naesala.golfscorecard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.HoleViewHolder> {
    private Hole[] mHoles;

    public HoleAdapter(Hole[] holes) {
        mHoles = holes;
    }

    @Override
    public HoleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hole_scorecard_item, parent, false);
        HoleViewHolder holder = new HoleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HoleViewHolder holder, int position) {
        holder.bindHole(mHoles[position]);
    }

    @Override
    public int getItemCount() {
        return mHoles.length;
    }

    public static class HoleViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mStrokes;
        public Button mPlus;
        public Button mMinus;
        public Hole mHole;

        public HoleViewHolder(View view) {
            super(view);
            mName = (TextView) view.findViewById(R.id.holeName);
            mStrokes = (TextView) view.findViewById(R.id.holeStrokes);
            mPlus = (Button) view.findViewById(R.id.plusButton);
            mMinus = (Button) view.findViewById(R.id.minusButton);
        }
        public void bindHole(Hole hole) {
            mHole = hole;
            mName.setText(mHole.getName());
            mStrokes.setText(String.valueOf(mHole.getStrokes()));

            mPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHole.incrementStrokes();
                    mStrokes.setText(String.valueOf(mHole.getStrokes()));
                }
            });
            mMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHole.decrementStrokes();
                    mStrokes.setText(String.valueOf(mHole.getStrokes()));
                }
            });
        }
    }
}
