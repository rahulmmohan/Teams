package sample.network.rahul.teams.teams

import sample.network.rahul.teams.datasource.Team


/**
 * Created by Rahul on 04/02/18.
 */
class ListContract {

    interface View {
        fun showTeams(Notes: MutableList<Team>)
        fun showNoTeams()
    }

    interface Presenter {
        fun loadTeams()
    }
}