package sample.network.rahul.teams.teams

import sample.network.rahul.teams.datasource.Team


/**
 * Created by Rahul on 16/01/18.
 */
class HomeContract {

    interface View {
        fun showTeams(Notes: MutableList<Team>)
        fun showNoTeams()
    }

    interface Presenter {
        fun loadTeams()
    }
}