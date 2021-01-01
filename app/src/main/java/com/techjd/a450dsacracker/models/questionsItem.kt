package com.techjd.a450dsacracker.models

data class questionsItem(
    val doneQuestions: Int,
    val position: Int,
    val questions: List<Question>,
    val started: Boolean,
    val topicName: String
)