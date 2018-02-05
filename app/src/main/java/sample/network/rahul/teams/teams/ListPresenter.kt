package sample.network.rahul.teams.teams

import android.content.Context
import android.util.Log
import com.example.rahul.autocoupons.data.ApiClient
import com.example.rahul.autocoupons.data.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sample.network.rahul.teams.datasource.Team


/**
 * Created by rahul on 2/2/18.
 */

/**
 * Listens to user actions from the UI ([ListActivity]), retrieves the data and updates
 * the UI as required.
 */
class ListPresenter(val context: Context,
                    private val mListActivity: ListContract.View) : ListContract.Presenter {
    private val apiService = ApiClient.getClient().create(ApiInterface::class.java)

    override fun loadTeams() {
        apiService.getTeams().enqueue(object : Callback<MutableList<Team>> {
            override fun onFailure(call: Call<MutableList<Team>>?, t: Throwable?) {
                Log.d("onFailure",t!!.message)
                mListActivity.showNoTeams()
            }

            override fun onResponse(call: Call<MutableList<Team>>?, response: Response<MutableList<Team>>?) {
                if(response!!.body()!=null) {
                    if(response.body()!!.isEmpty()){
                        mListActivity.showNoTeams()
                    }else {
                        mListActivity.showTeams(response.body()!!)
                    }
                }else{
                    mListActivity.showNoTeams()
                }
            }

        })
    }


}
