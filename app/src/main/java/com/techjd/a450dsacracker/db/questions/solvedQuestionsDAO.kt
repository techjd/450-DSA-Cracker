package com.techjd.a450dsacracker.db.questions

import androidx.lifecycle.LiveData
import androidx.room.*
import com.techjd.a450dsacracker.db.questions.solvedQuestions

@Dao
interface solvedQuestionsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(solvedQuestion: solvedQuestions)

    @Delete
    fun delete(solvedQuestion: solvedQuestions)

    @Query("SELECT * FROM solvedQuestions")
    fun getAllQuestions(): List<solvedQuestions>

    @Query("SELECT * FROM solvedQuestions WHERE topicName = :topicName")
    fun getTopicSelectedQuestions(topicName: String): List<solvedQuestions>

    @Query("UPDATE solvedQuestions SET status = 1 WHERE problem = :problem")
    fun updateQuestion(problem: String)

    @Query("UPDATE solvedQuestions SET status = 0 WHERE problem = :problem")
    fun redoQuestion(problem: String)

    @Query("SELECT COUNT(questionId) FROM solvedQuestions WHERE topicName = :topicName AND status = 1")
    fun getNumberOfSolvedQuestions(topicName: String): Int

    @Query("SELECT * FROM solvedQuestions WHERE topicName = :topicName")
    fun getTopicWise(topicName: String): List<solvedQuestions>

    @Query("UPDATE solvedQuestions SET bookMarked = 1 WHERE problem = :problem")
    fun bookMarked(problem: String)

    @Query("UPDATE solvedQuestions SET bookMarked = 0 WHERE problem = :problem")
    fun unBookMark(problem: String)

    @Query("SELECT DISTINCT topicName FROM solvedQuestions WHERE bookMarked = 1")
    fun getDistinctNames(): List<String>

    @Query("SELECT * FROM solvedQuestions WHERE bookMarked = 1 AND topicName = :topicName")
    fun getBookMarkedQuestions(topicName: String): List<solvedQuestions>



}