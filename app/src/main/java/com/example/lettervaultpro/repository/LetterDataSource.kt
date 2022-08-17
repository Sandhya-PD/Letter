package com.example.lettervaultpro.repository

import androidx.lifecycle.LiveData
import com.example.lettervaultpro.data.Letter
import kotlinx.coroutines.flow.Flow

interface LetterDataSource {
    fun observeTasks(): Flow<Result<List<Letter>>>

    suspend fun getTasks(): Result<List<Letter>>

    suspend fun refreshTasks()

    fun observeTask(taskId: String): LiveData<Result<Letter>>

    suspend fun getTask(): Flow<Letter>

    suspend fun refreshTask(taskId: String)

    suspend fun saveTask(task: Letter)

    suspend fun completeTask(task: Letter)

    suspend fun completeTask(taskId: String)

    suspend fun activateTask(task: Letter)

    suspend fun activateTask(taskId: String)

    suspend fun clearCompletedTasks()

    suspend fun deleteAllTasks()

    suspend fun deleteTask(taskId: String)
}