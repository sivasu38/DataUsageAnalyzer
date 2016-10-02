package com.myntra.networkanalyzer;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class EspressoTests {
    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setTimeout() {

        IdlingPolicies.setMasterPolicyTimeout(120, TimeUnit.SECONDS);
        IdlingPolicies.setIdlingResourceTimeout(120, TimeUnit.SECONDS);
    }

    @Test
    public void checkDataUsageActivityTest() {
        IdlingResource idlingResource = new ElapsedTimeIdlingResource(60);
        Espresso.registerIdlingResources(idlingResource);
        Espresso.onView(ViewMatchers.withId(R.id.editText6)).check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.typeText("Myntra"));
        Espresso.onView((ViewMatchers.withId(R.id.recycler_view))).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        Espresso.onView(ViewMatchers.withId(R.id.textView12)).check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
    }
}
