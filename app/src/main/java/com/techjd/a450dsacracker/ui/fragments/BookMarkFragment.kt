package com.techjd.a450dsacracker.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.techjd.a450dsacracker.R
import com.techjd.a450dsacracker.adapters.BookMarksAdapter
import com.techjd.a450dsacracker.db.QuestionsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BookMarkFragment : Fragment() {
    private lateinit var names: List<String>
    private lateinit var recyclerView: RecyclerView
    private lateinit var bookMarksAdapter: BookMarksAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_title: TextView
    private lateinit var centerText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_mark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.tl)
        toolbar_title = view.findViewById(R.id.ttl)
        toolbar_title.text = "BookMarked Topics \uD83D\uDCD5"
        centerText = view.findViewById(R.id.centerText)
        val db = context?.let {
            Room.databaseBuilder(
                it,
                QuestionsDatabase::class.java, "solvedQuestionsDB"
            ).allowMainThreadQueries().build()
        }

        recyclerView = view.findViewById(R.id.bookMarksRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)


        names = db?.solvedQuestionsDAO?.getDistinctNames()!!

        if (names.size == 0) {
            centerText.visibility = View.GONE
        }

        bookMarksAdapter = context?.let { BookMarksAdapter(names, it) }!!
        recyclerView.adapter = bookMarksAdapter

    }

    override fun onResume() {
        super.onResume()

        val db = context?.let {
            Room.databaseBuilder(
                it,
                QuestionsDatabase::class.java, "solvedQuestionsDB"
            ).allowMainThreadQueries().build()
        }

        names = db?.solvedQuestionsDAO?.getDistinctNames()!!

        if (names.isEmpty()) {
            centerText.visibility = View.VISIBLE
        }

        recyclerView.adapter = context?.let { BookMarksAdapter(names, it) }!!
        bookMarksAdapter.notifyDataSetChanged()
    }


}