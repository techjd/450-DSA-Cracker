package com.techjd.a450dsacracker.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.techjd.a450dsacracker.R
import com.techjd.a450dsacracker.db.questions.solvedQuestions
import com.techjd.a450dsacracker.ui.activities.BookMarkedActivity

class BookMarksAdapter(val names: List<String>, val context: Context) :
    RecyclerView.Adapter<BookMarksAdapter.BookMarksViewHolder>() {


    class BookMarksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val topicTitle: TextView = itemView.findViewById(R.id.topicTitle)
        val mainTopicLayout: LinearLayout = itemView.findViewById(R.id.mainTopicLayout)
        val moreAndDone: LinearLayout = itemView.findViewById(R.id.moreAndDone)
        val totalNumberOfQuestions: LinearLayout =
            itemView.findViewById(R.id.totalNumberOfQuestions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_topic_card,
            parent, false
        )
        return BookMarksViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: BookMarksViewHolder, position: Int) {
        val nameList = names[position]

        holder.topicTitle.text = nameList
        holder.moreAndDone.visibility = View.GONE
        holder.totalNumberOfQuestions.visibility = View.GONE
        holder.mainTopicLayout.background =
            ContextCompat.getDrawable(context, R.drawable.layout_started)
        holder.mainTopicLayout.setOnClickListener(View.OnClickListener {
            val navigate = Intent(context, BookMarkedActivity::class.java)
            navigate.putExtra("TOPICNAME", nameList)
            context.startActivity(navigate)
        })
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}