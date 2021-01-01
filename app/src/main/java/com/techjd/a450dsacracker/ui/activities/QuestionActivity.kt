package com.techjd.a450dsacracker.ui.activities

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.techjd.a450dsacracker.R
import com.techjd.a450dsacracker.adapters.QuestionsAdapter
import com.techjd.a450dsacracker.db.QuestionsDatabase
import com.techjd.a450dsacracker.db.questions.solvedQuestions
import com.techjd.a450dsacracker.models.questionsItem
import com.techjd.a450dsacracker.models.questions
import com.techjd.a450dsacracker.utils.Utils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuestionActivity : AppCompatActivity() {
    private lateinit var questionsRecyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_title: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

//        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        supportActionBar?.setCustomView(R.layout.toolbar_set)
        val topicName = intent.extras!!.getString("TOPICNAME")
        toolbar = findViewById(R.id.tl)
        toolbar_title = findViewById(R.id.ttl)
        toolbar_title.text = topicName
        val position = intent.extras!!.getInt("POSITION")

        val db = Room.databaseBuilder(
            this,
            QuestionsDatabase::class.java, "solvedQuestionsDB"
        ).allowMainThreadQueries().build()
        val questiondao = db.solvedQuestionsDAO
        var questionsList = questiondao?.getTopicWise(topicName!!)

        if (questionsList != null) {
            for (i in questionsList) {
                Log.d("VALUES", i.Problem)
            }
        }

        questionsRecyclerView = findViewById(R.id.questionRecyclerView)
        questionsRecyclerView.layoutManager = LinearLayoutManager(this)
        questionsRecyclerView.setHasFixedSize(true)
        val adapter = QuestionsAdapter()
        questionsRecyclerView.adapter = adapter
        if (topicName != null) {
            adapter.setQuestionsList(questionsList, this, topicName)
        }


//        val topicsJSON = Utils().getJsonDataFromAsset(applicationContext, "questions.json")
//
//        val gson = Gson()
//
//        val listQuestionType = object : TypeToken<List<questionsItem>>() {}.type
//
//        val questionsL = Gson().fromJson(topicsJSON, questions::class.java)

//        for (i in 0 until questionsL.size) {
//            Log.d("ALL TOPICS", questionsL[0].questions[0].Problem)
//            if (position == questionsL[i].position) {
//
//                Log.d("NAME", questionsL[i].topicName)
//                Log.d("SIZE", questionsL[i].questions.size.toString())
//                for (j in 0 until questionsL[i].questions.size) {
//                    Log.d("NAME", questionsL[i].questions[j].Problem)
//                    val solvedQuestion = solvedQuestions(
//                        j,
//                        questionsL[0].questions[j].Topic,
//                        questionsL[0].questions[j].Problem,
//                        false,
//                        questionsL[0].questions[j].URL
//                    )
//                    GlobalScope.launch {
//                        questiondao?.insert(solvedQuestion)
//
//                    }
//
//                }
//            }
//            if (topicName.equals(questionsL[i].topicName)) {
//                Log.d("ITS A MATCGH", "MATCHED")
//
//            }
//            val questionSize: Int = questionsL[i].questions.size
//            Log.d("SIZE", questionSize.toString())
//            for (j in 0..questionSize-1) {
//                if (topicName.equals(questionsL[0].questions[j].Topic)) {
//                    Log.d("SIZE", "ITS SAME")
//                }
////                val solvedQuestion = solvedQuestions(
////                    j,
////                    questionsL[0].questions[j].Topic,
////                    questionsL[0].questions[j].Problem,
////                    false,
////                    questionsL[0].questions[j].URL
////                )
////                GlobalScope.launch {
////                    questiondao?.insert(solvedQuestion)
////                }
//            }


//        }


    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}