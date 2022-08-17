package com.example.lettervaultpro.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.repository.LetterDataSource
import kotlinx.coroutines.flow.Flow

object TaskRemoteDataSource :LetterDataSource{

    private var TASKS_SERVICE_DATA = LinkedHashMap<String, Letter>(2)

    init {
        addTask(1,"Build tower in Pisa", "Ground looks good, no foundation work required.","Aug 02 2022, 12:30PM","Aug 02 2022, 12:30PM",true)
        addTask(2,"Finish bridge in Tacoma", "Found awesome girders at half the cost!","Aug 02 2022, 12:30PM","Aug 02 2022, 12:30PM",false)
    }

    private val observableTasks = MutableLiveData<Result<List<Letter>>>()

    private fun addTask(id:Int,title: String, description: String,created_date:String,selected_date:String,status:Boolean) {
        val newTask = Letter(id,title, description,created_date,selected_date,status)
        TASKS_SERVICE_DATA[newTask.id.toString()] = newTask
    }

    override fun observeTasks(): Flow<Result<List<Letter>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(): Result<List<Letter>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshTasks() {
        TODO("Not yet implemented")
    }

    override fun observeTask(taskId: String): LiveData<Result<Letter>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTask(): Flow<Letter> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveTask(task: Letter) {
        TASKS_SERVICE_DATA[task.id.toString()] = task
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
        TODO("Not yet implemented")
    }
}