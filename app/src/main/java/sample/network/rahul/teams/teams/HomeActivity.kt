package sample.network.rahul.teams.teams

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.rahul.autocoupons.data.ApiClient
import com.example.rahul.autocoupons.data.ApiInterface
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sample.network.rahul.teams.R
import sample.network.rahul.teams.datasource.Team


class HomeActivity : AppCompatActivity(), HomeContract.View {


    private lateinit var mPresenter: HomeContract.Presenter
    private lateinit var mAdapter: TeamsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        mPresenter = HomePresenter(this, this)
        setupRecyclerView()
        mPresenter.loadTeams()
    }

    private fun setupRecyclerView() {
        mAdapter = TeamsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    override fun showNoTeams() {
        recyclerView.visibility = View.INVISIBLE
        noNotes.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    override fun showTeams(teams: MutableList<Team>) {
        mAdapter.replaceData(teams)
        recyclerView.visibility = View.VISIBLE
        noNotes.visibility = View.INVISIBLE
        progressBar.visibility = View.INVISIBLE
    }

}
