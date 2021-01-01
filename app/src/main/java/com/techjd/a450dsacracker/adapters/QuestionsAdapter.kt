package com.techjd.a450dsacracker.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.techjd.a450dsacracker.R
import com.techjd.a450dsacracker.db.QuestionsDatabase
import com.techjd.a450dsacracker.db.questions.solvedQuestions
import com.techjd.a450dsacracker.ui.activities.WebViewActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class QuestionsAdapter() :
    RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder>() {

    private var solvedQuestions: List<solvedQuestions> = emptyList<solvedQuestions>()
    private lateinit var context: Context
    private lateinit var name: String

    class QuestionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionNumber: TextView = itemView.findViewById(R.id.questionNumber)
        val questionTitle: TextView = itemView.findViewById(R.id.questionTitle)
        val questionsCard: CardView = itemView.findViewById(R.id.questionsCard)
        val questionsLayout: LinearLayout = itemView.findViewById(R.id.questionsLayout)
        val doneAndRedo: LinearLayout = itemView.findViewById(R.id.doneAndRedoBcg)
        val finalLayout: LinearLayout = itemView.findViewById(R.id.finalLayout)
        val done: ImageButton = itemView.findViewById(R.id.done)
        val redo: ImageButton = itemView.findViewById(R.id.redo)
        val bookMark: ImageButton = itemView.findViewById(R.id.bookMark)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_questions_card,
            parent, false
        )
        return QuestionsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {

        val ques = solvedQuestions[position]
        val db = Room.databaseBuilder(
            context,
            QuestionsDatabase::class.java, "solvedQuestionsDB"
        ).build()

        val questiondao = db.solvedQuestionsDAO
        holder.questionTitle.text = ques.Problem
        holder.questionNumber.text = position.toString()
        Log.d("BOOKMARKED VALUE", ques.isBookMarked.toString())
        if (ques.isBookMarked) {
            holder.bookMark.setImageResource(R.drawable.ic_isbooked)
        } else {
            holder.bookMark.setImageResource(R.drawable.ic_isnotbooked)
        }



        if (ques.Done) {
            holder.finalLayout.background =
                ContextCompat.getDrawable(context, R.drawable.layout_started)
//            holder.doneAndRedo.background =
//                ContextCompat.getDrawable(context, R.drawable.layout_started)
        } else {
            holder.finalLayout.background =
                ContextCompat.getDrawable(context, R.drawable.layout_questions)
//            holder.doneAndRedo.background =
//                ContextCompat.getDrawable(context, R.drawable.layout_questions)
        }

//        val solvedQuestion =
//            solvedQuestions(
//                position,
//                ques.topicName,
//                ques.Problem,
//                true,
//                ques.URL
//            )
//        .apply {
//            questionId = position
//            topicName = ques.Topic
//            Problem = ques.Problem
//            Done = true
//            URL = ques.URL
//
//        }

//        for (i in this!!.solvedQuestions!!) {
//            Log.d("VALUES", i.Problem)
//        }
        holder.done.setOnClickListener(View.OnClickListener {

//            Log.d("HERE", solvedQuestion.toString())

            GlobalScope.launch {
                questiondao?.updateQuestion(ques.Problem.trim())
                solvedQuestions = questiondao?.getTopicWise(name)!!
                withContext(Dispatchers.Main) {
                    setQuestionsList(solvedQuestions, context, name)
                }
            }

            holder.finalLayout.background =
                ContextCompat.getDrawable(context, R.drawable.layout_started)

//            (HomeFragment as AppCompatActivity).supportFragmentManager.beginTransaction()
//                .replace(
//                    R.id.fragment_container,
//                    HomeFragment()
//                ).commit()

//            print(solvedQuestion.Problem)
        })

        holder.redo.setOnClickListener(View.OnClickListener {
//            Log.d("HERE", solvedQuestion.toString())
            GlobalScope.launch {
                questiondao?.redoQuestion(ques.Problem.trim())
                solvedQuestions = questiondao?.getTopicWise(name)!!
                withContext(Dispatchers.Main) {
                    setQuestionsList(solvedQuestions, context, name)
                }

            }
            holder.finalLayout.background =
                ContextCompat.getDrawable(context, R.drawable.layout_questions)
        })

        holder.bookMark.setOnClickListener(View.OnClickListener {

        })

        holder.bookMark.setOnClickListener(View.OnClickListener {

            if (ques.isBookMarked) {
                GlobalScope.launch {
                    questiondao?.unBookMark(ques.Problem.trim())
                    solvedQuestions = questiondao?.getTopicWise(name)!!
                    withContext(Dispatchers.Main) {
                        setQuestionsList(solvedQuestions, context, name)
                    }
                }

                holder.bookMark.setImageResource(R.drawable.ic_isnotbooked)

            } else {
                GlobalScope.launch {
                    questiondao?.bookMarked(ques.Problem.trim())
                    solvedQuestions = questiondao?.getTopicWise(name)!!
                    withContext(Dispatchers.Main) {
                        setQuestionsList(solvedQuestions, context, name)
                    }
                }
                holder.bookMark.setImageResource(R.drawable.ic_isbooked)


            }
        })

        holder.questionsLayout.setOnClickListener(View.OnClickListener {
            val navigate = Intent(context, WebViewActivity::class.java)
            navigate.putExtra("URL", ques.URL)
            navigate.putExtra("QUESTION", ques.Problem)
            context.startActivity(navigate)
        })
    }


    override fun getItemCount(): Int {

        return solvedQuestions.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setQuestionsList(solvedQuestions: List<solvedQuestions>?, context: Context, name: String) {
        if (solvedQuestions != null) {
            this.solvedQuestions = solvedQuestions
        }
        this.context = context
        this.name = name
        notifyDataSetChanged()
    }
}