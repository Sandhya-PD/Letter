package com.example.lettervaultpro

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.MenuItemCompat.setShowAsAction
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.lettervaultpro.data.Letter
import com.example.lettervaultpro.repository.LetterTaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*

import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)

class HomeFragmentTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)


    @Test fun test_default_values() {
        val defaultLetter = Letter(1,"hello","welcome","12/12/2021","2/08/2022",true)
        Assert.assertEquals("hello", defaultLetter.subject)

    }


    @Test
    fun addLetterButton_navigateToAddFragment() {
        // GIVEN - On the home screen
        val scenario = launchFragmentInContainer<HomeFragment>(Bundle())
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }


//        closeSoftKeyboard()
        // Click menu
//        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext);
//
//        // WHEN - Click on the "+" button
//
//        onView(withText("add")).perform(click())
//        verify(navController).navigate(R.id.action_homeFragment_to_messageFragment)

//        onView(withId(R.id.subject)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.subject)).perform(ViewActions.typeText("Hello"))
//        onView(withId(R.id.subject)).check(ViewAssertions.matches(withText("Hello")))
//
//        onView(withId(R.id.message_txt)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//        onView(withId(R.id.message_txt)).perform(ViewActions.typeText("Welcome"))
//        onView(withId(R.id.message_txt)).check(ViewAssertions.matches(withText("Welcome")))

//        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext);
//        onView(withText("menulist")).perform(click())



    }



    @Test
    fun clickList_navigateToList(){
        val scenario = launchFragmentInContainer<HomeFragment>(Bundle())
        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)

        onView(withText("menulist")).perform(click())

        verify(navController).navigate(R.id.action_homeFragment_to_letterFragment)
    }

//    @Test
//    fun latestLetterDetails_DisplayedInUI()= runBlockingTest {
//        val task1 = Letter(1,"hello","welcome","12/12/2021","2/08/2022",false)
//
//        repository.saveTask(task1)
//
//        val scenario = launchFragmentInContainer<MessageFragment>(Bundle())
//        val navController = mock(NavController::class.java)
//
//        scenario.onFragment {
//            Navigation.setViewNavController(it.view!!, navController)
//        }
//
//        onView(withId(R.id.message_txt)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
//
//    }

}