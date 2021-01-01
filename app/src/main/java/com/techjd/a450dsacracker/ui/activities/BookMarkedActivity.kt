package com.techjd.a450dsacracker.ui.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.techjd.a450dsacracker.R
import com.techjd.a450dsacracker.adapters.BookMarkQuestionsAdapter
import com.techjd.a450dsacracker.db.QuestionsDatabase
import com.techjd.a450dsacracker.db.questions.solvedQuestions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class BookMarkedActivity : AppCompatActivity() {
    private lateinit var bookMarks: RecyclerView
    private lateinit var adater: BookMarkQuestionsAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_title: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_marked)

        val topicName = intent.extras!!.getString("TOPICNAME")

        toolbar = findViewById(R.id.tl)
        toolbar_title = findViewById(R.id.ttl)
        toolbar_title.text = topicName



        bookMarks = findViewById(R.id.bookMarksQuestionRecyclerView)
        bookMarks.layoutManager = LinearLayoutManager(this)
        bookMarks.setHasFixedSize(true)

        val db = Room.databaseBuilder(
            this,
            QuestionsDatabase::class.java, "solvedQuestionsDB"
        ).allowMainThreadQueries().build()
        val questiondao = db.solvedQuestionsDAO
        var bookMarkedquestionsList =
            topicName?.let { questiondao?.getBookMarkedQuestions(topicName) }



        adater = BookMarkQuestionsAdapter()
        bookMarks.adapter = adater
        if (bookMarkedquestionsList != null) {
            if (topicName != null) {
                adater.setBookMarkedList(bookMarkedquestionsList as ArrayList<solvedQuestions>, this, topicName)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}