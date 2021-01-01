package com.techjd.a450dsacracker.db.questions

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "solvedQuestions")
data class solvedQuestions(
    @PrimaryKey @NonNull
    @ColumnInfo(name = "questionId")
    var questionId: Int,
    @ColumnInfo(name = "topicName")
    var topicName: String,
    @ColumnInfo(name = "problem")
    var Problem: String,
    @ColumnInfo(name = "status")
    var Done: Boolean,
    @ColumnInfo(name = "url")
    var URL: String,
    @ColumnInfo(name = "bookMarked")
    var isBookMarked: Boolean
)

