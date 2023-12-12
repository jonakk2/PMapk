package com.example.nedbal_navigation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nedbal_navigation.ItemAdapter.ExampleViewHolder

class ItemAdapter(private val mHistoryList: ArrayList<HistoryItems>) :
    RecyclerView.Adapter<ExampleViewHolder>() {
    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextViewLine1: TextView
        var mTextViewLine2: TextView
        var mTextViewLine3: TextView

        init {
            mTextViewLine1 = itemView.findViewById(R.id.anime_result)
            mTextViewLine2 = itemView.findViewById(R.id.character_result)
            mTextViewLine3 = itemView.findViewById(R.id.quote_result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_history, parent, false)
        return ExampleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = mHistoryList[position]
        holder.mTextViewLine1.text = currentItem.mAnime
        holder.mTextViewLine2.text = currentItem.mcharacter
        holder.mTextViewLine3.text = currentItem.mQuote
    }

    override fun getItemCount(): Int {
        return mHistoryList.size
    }
}