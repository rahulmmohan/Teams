package sample.network.rahul.teams.teams

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
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
    }

    override fun showTeams(teams: MutableList<Team>) {
        mAdapter.replaceData(teams)
        recyclerView.visibility = View.VISIBLE
        noNotes.visibility = View.INVISIBLE
        progressBar.visibility = View.INVISIBLE
    }

}
