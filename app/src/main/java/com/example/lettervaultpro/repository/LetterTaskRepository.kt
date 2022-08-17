package com.example.lettervaultpro.repository

import androidx.lifecycle.LiveData
import com.example.lettervaultpro.data.Letter

interface LetterTaskRepository {
    fun observeTasks(): LiveData<Result<List<Letter>>>

    suspend fun getTasks(forceUpdate: Boolean = false): com.example.lettervaultpro.data.Result.Success<List<Letter>>

    suspend fun refreshTasks()

    fun observeTask(taskId: String): LiveData<Result<Letter>>

    suspend fun getTask(taskId: String, forceUpdate: Boolean = false): Result<Letter>

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