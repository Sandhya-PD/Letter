package com.example.lettervaultpro.data

//import androidx.lifecycle.LiveData
//import com.example.lettervaultpro.repository.LetterDataSource
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.withContext
//import kotlin.Result
//
//class LetterLocalDataSource internal constructor(
//    private val tasksDao: LetterDao,
//    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
//): LetterDataSource {
//    override fun observeTasks(): Flow<Result<List<Letter>>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getTasks(): Result<List<Letter>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun refreshTasks() {
//        TODO("Not yet implemented")
//    }
//
//    override fun observeTask(taskId: String): LiveData<Result<Letter>> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getTask(): Flow<Letter> {
//        return tasksDao.getLetter()
//
//    }
//
//
//    override suspend fun refreshTask(taskId: String) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun saveTask(task: Letter) = withContext(ioDispatcher) {
//        tasksDao.insert(task)
//    }
//
//    override suspend fun completeTask(task: Letter) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun completeTask(taskId: String) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun activateTask(task: Letter) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun activateTask(taskId: String) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun clearCompletedTasks() {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteAllTasks() {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteTask(taskId: String) {
//        TODO("Not yet implemented")
//    }
//
//}