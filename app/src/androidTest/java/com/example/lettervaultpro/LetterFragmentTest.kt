package com.example.lettervaultpro

import android.os.Bundle
import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lettervaultpro.repository.LetterTaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class LetterFragmentTest {

    private lateinit var repository: LetterTaskRepository

    @Before
    fun initRepository() {

        repository = FakeAndroidTestRepository()
//        ServiceLocator.tasksRepository = repository
    }

//    @After
//    fun cleanupDb() = runBlockingTest {
//        ServiceLocator.resetRepository()
//    }


    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

@Test
fun clickAddTaskButton_navigateToAddEditFragment() {
    // GIVEN - On the home screen
    val scenario = launchFragmentInContainer<LetterFragment>(Bundle(),androidx.appcompat.R.style.Theme_AppCompat)
    val navController = Mockito.mock(NavController::class.java)
    scenario.onFragment {
        Navigation.setViewNavController(it.view!!, navController)
    }

    // WHEN - Click on the "+" button
    Espresso.onView(withId(R.id.fab)).perform(click()).check(matches(isDisplayed()))

    // THEN - Verify that we navigate to the add screen
    Mockito.verify(navController).navigate(
       R.id.action_letterFragment_to_messageFragment
    )
}


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun clickTask_navigateToDetailFragmentOne()   {
//        repository.saveTask(Letter(1,"TITLE1", "DESCRIPTION1", "AUG 02 2022,11:20AM","AUG 02 2022,11:30AM",false))
//        repository.saveTask(Letter(1,"TITLE2", "DESCRIPTrunBlockingTestION2", "AUG 02 2022,11:40AM","AUG 02 2022,12:30PM",false))
//
//        val activeTask=Letter(1,"TITLE2", "DESCRIPTION2", "AUG 02 2022,11:40AM","AUG 02 2022,12:30PM",false)
//
//        repository.saveTask(activeTask)
        // GIVEN - On the home screen

        launchFragmentInContainer<LetterFragment>(Bundle(), androidx.appcompat.R.style.Theme_AppCompat)
        val navController = Mockito.mock(NavController::class.java)

        Espresso.onView(withId(R.id.recycler_view)).perform(closeSoftKeyboard())

        Espresso.onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,forceClick()))

//            .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
//                ViewMatchers.hasDescendant(ViewMatchers.withText("hh")), click()))

        // WHEN - Click on the first list item
//        Espresso.onView(withId(R.id.border))
//            .perform(ViewActions.click()
//            )


        // THEN - Verify that we navigate to the first detail screen
//        Mockito.verify(navController).navigate(
//            R.id.action_letterFragment_to_openMessage
//        )
    }

    fun forceClick(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(isClickable(), isEnabled(), isDisplayed())
            }

            override fun getDescription(): String {
                return "force click"
            }

            override fun perform(uiController: UiController, view: View) {
                view.performClick() // perform click without checking view coordinates.
                uiController.loopMainThreadUntilIdle()
            }
        }
    }
}