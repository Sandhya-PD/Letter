package com.example.lettervaultpro

import android.app.ActionBar
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.lettervaultpro.data.Letter
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class MessageFragmentTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun addLetter_LatestLetter(){

        val scenario = launchFragmentInContainer<MessageFragment>(Bundle(),androidx.appcompat.R.style.Theme_AppCompat)

//        onView(
//            allOf(
//                instanceOf(ActionBar::class.java),
//
//                )
//        )
//            .check(matches(withText("Created")))

        onView(withId(R.id.subject)).check(matches(isDisplayed()))
        onView(withId(R.id.subject)).perform(ViewActions.typeText("Hello"))
        onView(withId(R.id.subject)).check(matches(withText("Hello")))

        onView(withId(R.id.message_txt)).check(matches(isDisplayed()))
        onView(withId(R.id.message_txt)).perform(ViewActions.typeText("Welcome"))
        onView(withId(R.id.message_txt)).check(matches(withText("Welcome")))

        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

//        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
//
//        onView(withText("finish")).perform(ViewActions.click())
////
//        Mockito.verify(navController).navigate(R.id.action_messageFragment_to_homeFragment)


    }




}