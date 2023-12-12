package com.example.nedbal_navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ExampleViewHolder> {
    private ArrayList<HistoryItems> mHistoryList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;
        public TextView mTextViewLine3;
        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextViewLine1 = itemView.findViewById(R.id.anime_result);
            mTextViewLine2 = itemView.findViewById(R.id.character_result);
            mTextViewLine3 = itemView.findViewById(R.id.quote_result);
        }
    }

    public ItemAdapter(ArrayList<HistoryItems> exampleList) {
        mHistoryList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_history, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        HistoryItems currentItem = mHistoryList.get(position);

        holder.mTextViewLine1.setText(currentItem.getMAnime());
        holder.mTextViewLine2.setText(currentItem.getMcharacter());
        holder.mTextViewLine3.setText(currentItem.getMQuote());
    }

    @Override
    public int getItemCount() {
        return mHistoryList.size();
    }
}