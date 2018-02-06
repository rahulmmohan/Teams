package sample.network.rahul.teams

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.test.InstrumentationTestCase
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import sample.network.rahul.teams.datasource.ApiClient
import sample.network.rahul.teams.datasource.ApiInterface

/**
 * Created by rahul on 5/2/18.
 */
@RunWith(AndroidJUnit4::class)
class TeamsMockAdapterTest: InstrumentationTestCase() {
    private lateinit var retrofit:Retrofit
    private var server: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    public override fun setUp() {
        server = MockWebServer()
        server!!.start()
        injectInstrumentation(InstrumentationRegistry.getInstrumentation())
        ApiClient.BASE_URL = server!!.url("/").toString()
        retrofit = ApiClient.getClient()
    }


    @Test
    fun test_team_fetch_success(){
        val fileName = "200_ok_response.json"

        server!!.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(instrumentation.context, fileName)))

        val mockTeamService = retrofit.create(ApiInterface::class.java)
        val call = mockTeamService.getTeams()
        val teams = call.execute()
        Assert.assertTrue(teams.isSuccessful)
        Assert.assertEquals("Arsenal FC", teams.body()!![0].name)
        Assert.assertFalse( teams.body()!![0].national)
        Assert.assertEquals("England", teams.body()!![0].countryName)
    }

}