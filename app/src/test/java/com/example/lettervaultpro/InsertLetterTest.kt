package com.example.lettervaultpro

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.lettervaultpro.data.Letter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class InsertLetterTest {

    // Subject under test
    private lateinit var tasksViewModel: LetterViewModel


    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


//    @Before
//    fun setupViewModel() {
//        // We initialise the tasks to 3, with one active and two completed
//
//        val task1 = Letter(1,"hello","welcome","12/12/2021","2/08/2022",false)
////        val task2 = Task("Title2", "Description2", true)
////        val task3 = Task("Title3", "Description3", true)
//        tasksViewModel.insertItem(task1)
//
//
//    }
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun addNewTask_setsNewTaskValue()= runBlockingTest {
//        // When adding a new task
////        tasksViewModel.getLatestLetter()
//
//        val task1 = Letter(1,"hello","welcome","12/12/2021","2/08/2022",false)
//
//        tasksViewModel.insertItem(task1)
//
//        // Then the new task event is triggered
//        val value = tasksViewModel.getLatestLetter()
//
//
//
//    }

}