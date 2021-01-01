package com.techjd.a450dsacracker.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.techjd.a450dsacracker.R
import com.techjd.a450dsacracker.db.QuestionsDatabase
import com.techjd.a450dsacracker.db.questions.solvedQuestions
import com.techjd.a450dsacracker.models.questionsItem
import com.techjd.a450dsacracker.ui.activities.QuestionActivity
import com.techjd.a450dsacracker.ui.fragments.HomeFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class TopicsAdapter(  var topicsList: List<questionsItem>, val context: Context ) :
    RecyclerView.Adapter<TopicsAdapter.QuestionsViewHolder>() {

    private lateinit var solvedQuestions: List<solvedQuestions>
    private lateinit var element: String

    class QuestionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val topicTitle: TextView = itemView.findViewById(R.id.topicTitle)
        val totalNumberOfQuestions: TextView = itemView.findViewById(R.id.totalQuestions)
        val totalNumberOfSolvedQuestion: TextView =
            itemView.findViewById(R.id.numberOfSolvedQuestions)
        val percentage: TextView = itemView.findViewById(R.id.percentage)
        val card: CardView = itemView.findViewById(R.id.topicCard)
        val moreAndDone: LinearLayout = itemView.findViewById(R.id.moreAndDone)
        val mainTopicLayout: LinearLayout = itemView.findViewById(R.id.mainTopicLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_topic_card,
            parent, false
        )
        return QuestionsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {



//            print(solvedQuestion.Problem)

        val topics = topicsList[position]

        val db = Room.databaseBuilder(
            context,
            QuestionsDatabase::class.java, "solvedQuestionsDB"
        ).build()



        holder.topicTitle.text = topics.topicName
        holder.totalNumberOfQuestions.text = topics.questions.size.toString()

        holder.card.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra("POSITION", topics.position)
            intent.putExtra("TOPICNAME", topics.topicName)
            context.startActivity(intent)


        })


        var solvedNumber: Int
        val questiondao = db.solvedQuestionsDAO
        GlobalScope.launch {
            solvedNumber = questiondao?.getNumberOfSolvedQuestions(topics.topicName)!!
            Log.d("SOLVED NUMBER", solvedNumber.toString())
            withContext(Dispatchers.Main) {
                if (solvedNumber == 0) {
                    holder.totalNumberOfSolvedQuestion.visibility = View.GONE
                    holder.percentage.visibility = View.GONE
                    holder.moreAndDone.visibility = View.GONE
                    holder.mainTopicLayout.background = ContextCompat.getDrawable(context, R.drawable.layout_started)

                } else {
                    holder.mainTopicLayout.background = ContextCompat.getDrawable(context, R.drawable.layout)
                    holder.totalNumberOfSolvedQuestion.text =
                        (topics.questions.size - solvedNumber).toString()
                    holder.percentage.text =
                        ((solvedNumber.toDouble() / topics.questions.size) * 100).roundToInt()
                            .toString()
                }
            }
            db.close()
        }


//        (context as AppCompatActivity).supportFragmentManager.beginTransaction()
//            .replace(
//                R.id.fragment_container,
//                HomeFragment()
//            ).commit()



//        GlobalScope.launch {
//            solvedQuestions = questiondao?.getAllQuestions()!!
//
//            for (element in solvedQuestions) {
//                Log.d("ALL TOPICS", element.topicName)
//            }
//            withContext(Dispatchers.Main) {
//                holder.totalNumberOfSolvedQuestion.text = solvedQuestions.size.toString()
//            }
//        }


    }




    override fun getItemCount(): Int {
        return topicsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }




}