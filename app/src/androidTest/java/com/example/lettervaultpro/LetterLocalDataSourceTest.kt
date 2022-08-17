package com.example.lettervaultpro

import android.os.Bundle

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.asLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.data.LetterLocalDataSource
import com.example.lettervaultpro.data.LetterRoomDatabase
import com.example.lettervaultpro.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import com.example.lettervaultpro.data.Result.Success

import com.example.lettervaultpro.data.succeeded
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsNot
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class LetterLocalDataSourceTest {
    // Executes each task synchronously using Architecture Components.
//    @get:Rule
//    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var localDataSource: LetterLocalDataSource
    private lateinit var database: LetterRoomDatabase

    @Before
    fun setup(){
        // Using an in-memory database for testing, because it doesn't survive killing the process.
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LetterRoomDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        localDataSource =
            LetterLocalDataSource(
                database.letterDao(),
                Dispatchers.Main
            )
    }
    @After
    fun clean_up(){
        database.close()
    }

    @Test
    fun saveTask_retrievesTask() = runBlocking {
        // GIVEN - A new task saved in the database.
        val newTask = Letter(1, "title", "Description","Aug 2 2022,10:13AM","Aug 2 2022,10:23AM",false)
        localDataSource.saveTask(newTask)

        // WHEN  - Task retrieved by ID.
        val result = localDataSource.getTask()


       val letter= result.asLiveData().value

        launchFragmentInContainer<HomeFragment>(Bundle())


        Espresso.onView(withId(R.id.msg_subject))
            .check(ViewAssertions.matches(ViewMatchers.withText("${letter?.subject}")))


        // THEN - Same task is returned.

//        assertThat(result.succeeded,`is`(true))
//        result as Success
//        Assert.assertThat(result.toString(), `is`(1))
//        Assert.assertThat(result.data., `is`("title"))
//        Assert.assertThat(result.data.detail, `is`("Description"))
//        Assert.assertThat(result.data.created_date,`is`("Aug 2 2022,10:13AM"))
//        Assert.assertThat(result.data.selected_date, `is`("Aug 2 2022,10:23AM"))
//        Assert.assertThat(result.data.status, `is`(false))
    }

}