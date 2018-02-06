package sample.network.rahul.teams.datasource

import retrofit2.Call
import retrofit2.http.GET


/**
 * Created by rahul on 2/2/18.
 */
interface ApiInterface {

    @GET("teams.json")
    fun getTeams(): Call<MutableList<Team>>
}