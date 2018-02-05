package sample.network.rahul.teams

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.InstrumentationTestCase

import com.example.rahul.autocoupons.data.ApiClient
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import sample.network.rahul.teams.teams.HomeActivity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

/**
 * @author rebeccafranks
 * @since 15/10/25.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest : InstrumentationTestCase() {


    @Rule @JvmField
    var mActivityRule = ActivityTestRule(HomeActivity::class.java, true, false)
    private var server: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    public override fun setUp() {
        super.setUp()
        server = MockWebServer()
        server!!.start()
        injectInstrumentation(InstrumentationRegistry.getInstrumentation())
        ApiClient.BASE_URL = server!!.url("/").toString()
    }

    @Test
    @Throws(Exception::class)
    fun test_team_list_shown_when_success_response() {
        val fileName = "200_ok_response.json"

        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(instrumentation.context, fileName)))

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.noNotes)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
    }

//    @Test
//    @Throws(Exception::class)
//    fun test_noTeams_shown_when_failed_response() {
//        server!!.enqueue(MockResponse()
//                .setResponseCode(404))
//
//        val intent = Intent()
//        mActivityRule.launchActivity(intent)
//
//        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
//        onView(withId(R.id.noNotes)).check(matches(isDisplayed()))
//    }

    @After
    @Throws(Exception::class)
    public override fun tearDown() {
        server!!.shutdown()
    }

}
