package com.example.lettervaultpro

import androidx.lifecycle.LiveData
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.repository.LetterDataSource
import com.example.lettervaultpro.repository.LetterTaskRepository
import com.example.lettervaultpro.util.EspressoIdlingResource.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DefaultLetterRepository(
    private val tasksRemoteDataSource: LetterDataSource,
    private val tasksLocalDataSource: LetterDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
):LetterTaskRepository {
    override fun observeTasks(): LiveData<Result<List<Letter>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(forceUpdate: Boolean): com.example.lettervaultpro.data.Result.Success<List<Letter>> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshTasks() {
        TODO("Not yet implemented")
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
        wrapEspressoIdlingResource {
            coroutineScope {
                launch { tasksRemoteDataSource.saveTask(task) }
                launch { tasksLocalDataSource.saveTask(task) }
            }
        }
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