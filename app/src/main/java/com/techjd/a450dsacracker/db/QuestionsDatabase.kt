package com.techjd.a450dsacracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.techjd.a450dsacracker.db.questions.solvedQuestions
import com.techjd.a450dsacracker.db.questions.solvedQuestionsDAO
import com.techjd.a450dsacracker.models.Question
import com.techjd.a450dsacracker.models.questions
import com.techjd.a450dsacracker.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Database(entities = [solvedQuestions::class], version = 1)
abstract class QuestionsDatabase : RoomDatabase() {
    abstract val solvedQuestionsDAO: solvedQuestionsDAO?
}


