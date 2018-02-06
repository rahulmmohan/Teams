package sample.network.rahul.teams

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.test.InstrumentationTestCase
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sample.network.rahul.teams.datasource.ApiClient
import sample.network.rahul.teams.teams.ListActivity

@RunWith(AndroidJUnit4::class)
class ListActivityTest : InstrumentationTestCase() {


    @Rule
    @JvmField
    var mActivityRule = ActivityTestRule(ListActivity::class.java, true, false)
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
    fun test_empty_response() {
        val fileName = "200_ok_response_empty.json"

        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(instrumentation.context, fileName)))


        val intent = Intent()
        mActivityRule.launchActivity(intent)

        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
        onView(withId(R.id.noTeams)).check(matches(isDisplayed()))
    }


    @Test
    @Throws(Exception::class)
    fun test_failed_response() {
        server!!.enqueue(MockResponse()
                .setResponseCode(404))

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
        onView(withId(R.id.noTeams)).check(matches(isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun test_success_response() {
        val fileName = "200_ok_response.json"

        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(instrumentation.context, fileName)))


        val intent = Intent()
        mActivityRule.launchActivity(intent)

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.noTeams)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
    }

    @After
    @Throws(Exception::class)
    public override fun tearDown() {
        server!!.shutdown()
    }

}
