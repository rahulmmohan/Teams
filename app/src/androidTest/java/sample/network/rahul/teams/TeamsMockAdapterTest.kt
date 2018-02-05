package sample.network.rahul.teams

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import android.test.InstrumentationTestCase
import com.example.rahul.autocoupons.data.ApiClient
import com.example.rahul.autocoupons.data.ApiInterface
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

/**
 * Created by rahul on 5/2/18.
 */
@RunWith(AndroidJUnit4::class)
class TeamsMockAdapterTest: InstrumentationTestCase() {
    lateinit var retrofit:Retrofit
    private var server: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    override fun setUp() {
        server = MockWebServer()
        server!!.start()
        injectInstrumentation(InstrumentationRegistry.getInstrumentation())
        ApiClient.BASE_URL = server!!.url("/").toString()
        retrofit = ApiClient.getClient()
    }


    @Test
    fun test_team_fetch_fail(){
        //server!!.start()
        server!!.enqueue(MockResponse()
                .setResponseCode(404)
                .setBody(""))
        val mockTeamServiceFail = retrofit.create(ApiInterface::class.java)
        val call = mockTeamServiceFail.getTeams()
        val teams = call.execute()
        server!!.takeRequest()
        Assert.assertEquals(404,teams.code())
    }


    @Test
    fun test_team_fetch(){
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
        server!!.shutdown()
    }


    @After
    @Throws(Exception::class)
    public override fun tearDown() {
        server!!.shutdown()
    }

}