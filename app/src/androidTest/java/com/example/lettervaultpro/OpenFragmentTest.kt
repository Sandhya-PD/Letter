package com.example.lettervaultpro

import android.os.Bundle
import androidx.appcompat.R
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.repository.LetterTaskRepository
import com.example.lettervaultpro.utilies.testLetters
import com.example.lettervaultpro.utilies.testPlant
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class OpenFragmentTest {

    private lateinit var repository: LetterTaskRepository

    @Before
    fun initRepository() {
        repository = FakeAndroidTestRepository()
        ServiceLocator.tasksRepository = repository
    }

    @After
    fun cleanupDb() = runBlockingTest {
        ServiceLocator.resetRepository()
    }

    @Test
   fun delete_item(){

//        val activeTask =  Letter(1,"hello","welcome","12/12/2021","2/08/2022",true)
//        repository.saveTask(activeTask)

        val bundle=Bundle().apply { putString("leeterid", testPlant.id.toString()) }

        val scenario = launchFragmentInContainer<OpenMessage>(Bundle(), R.style.Theme_AppCompat)
        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // WHEN - Click on the "+" button
//        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)

//        Espresso.onView(withId(com.example.lettervaultpro.R.id.recycler_view)).perform(ViewActions.closeSoftKeyboard())
//
//        Espresso.onView(withId(com.example.lettervaultpro.R.id.recycler_view))
//            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))

//        Mockito.verify(navController).navigate(
//            com.example.lettervaultpro.R.id.action_letterFragment_to_messageFragment,bundle)


        Espresso.onView(ViewMatchers.withText("Delete")).perform(click())
        // THEN - Verify that we navigate to the add screen
        Mockito.verify(navController).navigate(
            com.example.lettervaultpro.R.id.action_openMessage_to_letterFragment)
    }

}