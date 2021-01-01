package com.techjd.a450dsacracker.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.techjd.a450dsacracker.R
import com.techjd.a450dsacracker.adapters.TopicsAdapter
import com.techjd.a450dsacracker.db.QuestionsDatabase
import com.techjd.a450dsacracker.db.questions.solvedQuestions
import com.techjd.a450dsacracker.models.Question
import com.techjd.a450dsacracker.models.questions
import com.techjd.a450dsacracker.models.questionsItem
import com.techjd.a450dsacracker.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var topicsAdapter: TopicsAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_title: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.tl)
        toolbar_title = view.findViewById(R.id.ttl)
        toolbar_title.text = "450 DSA Cracker \uD83D\uDD25"


        val appContext: Context? = context
        recyclerView = view.findViewById(R.id.topicsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)


        val db = context?.let {
            Room.databaseBuilder(
                it,
                QuestionsDatabase::class.java, "solvedQuestionsDB"
            ).build()
        }
//        GlobalScope.launch {
//            QuestionsDatabase.getInstance(appContext).solvedQuestionsDAO?.getAllQuestions()
//        }


        val topicsJSON = context?.let { Utils().getJsonDataFromAsset(it, "questions.json") }
        val gson = Gson()
        val listQuestionType = object : TypeToken<List<questionsItem>>() {}.type
        val questions = Gson().fromJson(topicsJSON, questions::class.java)

        val topicsList = ArrayList<questions>()
        val questionsList = ArrayList<Question>()
        Log.d("TOPIC", questions[0].topicName)

        for (i in 0 until questions.size) {
            for (j in 0 until questions[i].questions.size) {

                questionsList.add(
                    Question(
                        questions[i].questions[j].Done,
                        questions[i].questions[j].Problem,
                        questions[i].questions[j].Topic,
                        questions[i].questions[j].URL
                    )
                )
            }

        }

        for (z in 0 until questionsList.size) {
            Log.d("ALL QUES", z.toString() + " " + questionsList[z].Problem)

            val solvedQuestion = solvedQuestions(
                z,
                questionsList[z].Topic,
                questionsList[z].Problem.trim(),
                questionsList[z].Done,
                questionsList[z].URL,
                false
            )
            GlobalScope.launch(Dispatchers.IO) {
                if (db != null) {
                    db.solvedQuestionsDAO?.insert(solvedQuestion)
                }
            }
        }

        topicsAdapter = context?.let { TopicsAdapter(questions, it) }!!

        recyclerView.adapter = topicsAdapter

    }

    override fun onResume() {
        super.onResume()

        recyclerView.adapter = topicsAdapter
        topicsAdapter.notifyDataSetChanged()
    }


}