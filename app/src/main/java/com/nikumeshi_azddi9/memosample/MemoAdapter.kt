package com.nikumeshi_azddi9.memosample

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemoAdapter (
    private val memos: List<Memo>,
    private val onMemoClicked: (Memo) -> Unit
) : RecyclerView.Adapter<MemoAdapter.MemoViewHolder>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoAdapter.MemoViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_row, parent, false)
        val viewHolder = MemoViewHolder(view)
        view.setOnClickListener {
            onMemoClicked(memos[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount() = memos.size

    override fun onBindViewHolder(holder: MemoAdapter.MemoViewHolder, position: Int) {
        memos[position].also { memo ->
            holder.apply{
                title.text = memo.name
                updateTime.text = memo.lastModified
            }
        }
    }

    inner class MemoViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.title)
        val updateTime: TextView = view.findViewById(R.id.lastModified)
    }

}