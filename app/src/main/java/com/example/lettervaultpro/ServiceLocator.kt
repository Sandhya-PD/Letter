//package com.example.lettervaultpro
//
//import android.content.Context
//import androidx.annotation.VisibleForTesting
//import androidx.room.Room
//import com.example.lettervaultpro.data.LetterLocalDataSource
//import com.example.lettervaultpro.data.LetterRoomDatabase
//import com.example.lettervaultpro.remote.TaskRemoteDataSource
//import com.example.lettervaultpro.repository.LetterDataSource
//import com.example.lettervaultpro.repository.LetterRepository
//import com.example.lettervaultpro.repository.LetterTaskRepository
//import kotlinx.coroutines.runBlocking
//
//object ServiceLocator {
//    private val lock = Any()
//    private var database: LetterRoomDatabase? = null
//    @Volatile
//    var tasksRepository: LetterTaskRepository? = null
//        @VisibleForTesting set
////
////    fun provideTasksRepository(context: Context): LetterTaskRepository {
////        synchronized(this) {
////            return tasksRepository ?: createTasksRepository(context)
////        }
////    }
//
////    private fun createTasksRepository(context: Context): LetterTaskRepository {
////        val newRepo = DefaultLetterRepository(TaskRemoteDataSource, createTaskLocalDataSource(context))
////        tasksRepository = newRepo
////        return newRepo
////    }
//
//    private fun createTaskLocalDataSource(context: Context): LetterDataSource {
//        val database = database ?: createDataBase(context)
//        return LetterLocalDataSource(database.letterDao())
//    }
//
//    private fun createDataBase(context: Context): LetterRoomDatabase {
//        val result = Room.databaseBuilder(
//            context.applicationContext,
//            LetterRoomDatabase::class.java, "Tasks.db"
//        ).build()
//        database = result
//        return result
//    }
//
//    @VisibleForTesting
//    fun resetRepository() {
//        synchronized(lock) {
//            runBlocking {
//                TaskRemoteDataSource.deleteAllTasks()
//            }
//            // Clear all data to avoid test pollution.
//            database?.apply {
//                clearAllTables()
//                close()
//            }
//            database = null
//            tasksRepository = null
//        }
//    }
//}