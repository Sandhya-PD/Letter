package com.example.lettervaultpro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.repository.LetterTaskRepository
import java.util.LinkedHashMap
import com.example.lettervaultpro.data.Result.Success
import com.example.lettervaultpro.data.succeeded

class FakeAndroidTestRepository:LetterTaskRepository{

    var tasksServiceData: LinkedHashMap<String, Letter> = LinkedHashMap()

    private val observableTasks = MutableLiveData<Result<List<Letter>>>()


    override fun observeTasks(): LiveData<Result<List<Letter>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(forceUpdate: Boolean): Success<List<Letter>> {
        return Success(tasksServiceData.values.toList())
    }

    override suspend fun refreshTasks() {
//        observableTasks.value= getTasks()
    }

    override fun observeTask(taskId: String): LiveData<Result<Letter>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(taskId: String, forceUpdate: Boolean): Result<Letter> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveTask(task: Letter) {
        tasksServiceData[task.id.toString()] = task
    }

    override suspend fun completeTask(task: Letter) {
        TODO("Not yet implemented")
    }

    override suspend fun completeTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun activateTask(task: Letter) {
        TODO("Not yet implemented")
    }

    override suspend fun activateTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearCompletedTasks() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllTasks() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(taskId: String) {
        tasksServiceData.remove(taskId)
        refreshTasks()
    }
}