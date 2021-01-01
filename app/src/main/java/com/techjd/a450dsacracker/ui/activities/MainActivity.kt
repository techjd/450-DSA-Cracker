package com.techjd.a450dsacracker.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.techjd.a450dsacracker.R
import com.techjd.a450dsacracker.adapters.TopicsAdapter
import com.techjd.a450dsacracker.db.QuestionsDatabase
import com.techjd.a450dsacracker.db.questions.solvedQuestions
import com.techjd.a450dsacracker.models.Question
import com.techjd.a450dsacracker.models.questions
import com.techjd.a450dsacracker.models.questionsItem
import com.techjd.a450dsacracker.ui.fragments.AboutFragment
import com.techjd.a450dsacracker.ui.fragments.BookMarkFragment
import com.techjd.a450dsacracker.ui.fragments.HomeFragment
import com.techjd.a450dsacracker.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var topicsAdapter: TopicsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                HomeFragment()).commit()
        }


//        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        supportActionBar?.setCustomView(R.layout.toolbar_set)
//
//        val appContext: Context = this
//        recyclerView = findViewById(R.id.topicsRecyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//
//        val db = Room.databaseBuilder(
//            this,
//            QuestionsDatabase::class.java, "solvedQuestionsDB"
//        ).build()
////        GlobalScope.launch {
////            QuestionsDatabase.getInstance(appContext).solvedQuestionsDAO?.getAllQuestions()
////        }
//
//
//        val topicsJSON = Utils().getJsonDataFromAsset(applicationContext, "questions.json")
//        val gson = Gson()
//        val listQuestionType = object : TypeToken<List<questionsItem>>() {}.type
//        val questions = Gson().fromJson(topicsJSON, questions::class.java)
//
//        val topicsList = ArrayList<questions>()
//        val questionsList = ArrayList<Question>()
//        Log.d("TOPIC", questions[0].topicName)
//
//        for (i in 0 until questions.size) {
//            for (j in 0 until questions[i].questions.size) {
//
//                questionsList.add(
//                    Question(
//                        questions[i].questions[j].Done,
//                        questions[i].questions[j].Problem,
//                        questions[i].questions[j].Topic,
//                        questions[i].questions[j].URL
//                    )
//                )
//            }
//
//        }
//
//        for (z in 0 until questionsList.size) {
//            Log.d("ALL QUES", z.toString() + " " + questionsList[z].Problem)
//
//            val solvedQuestion = solvedQuestions(
//                z,
//                questionsList[z].Topic,
//                questionsList[z].Problem,
//                questionsList[z].Done,
//                questionsList[z].URL
//            )
//            GlobalScope.launch(Dispatchers.IO) {
//                db.solvedQuestionsDAO?.insert(solvedQuestion)
//            }
//
//
//        }
//
//        topicsAdapter = TopicsAdapter(questions, this)
//        recyclerView.adapter = topicsAdapter


    }

    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            var selectedFragment: Fragment? = null
            when (item.getItemId()) {
                R.id.nav_home -> selectedFragment = HomeFragment()
                R.id.nav_orders -> selectedFragment = BookMarkFragment()
                R.id.nav_account -> selectedFragment = AboutFragment()
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit()
            }
            return true
        }
    }



}