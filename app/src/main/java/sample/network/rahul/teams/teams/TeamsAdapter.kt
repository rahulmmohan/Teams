package sample.network.rahul.teams.teams

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.amulyakhare.textdrawable.util.ColorGenerator


import java.util.ArrayList

import sample.network.rahul.teams.R
import sample.network.rahul.teams.datasource.Team
import com.amulyakhare.textdrawable.TextDrawable




class TeamsAdapter : RecyclerView.Adapter<TeamsAdapter.MyViewHolder>() {

    private var teams: MutableList<Team> = ArrayList()


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var teamName: TextView = view.findViewById(R.id.teamName)
        internal var countryName: TextView = view.findViewById(R.id.countryName)
        internal var nationalTeam: TextView = view.findViewById(R.id.nationalTeam)
        internal var imageView: ImageView = view.findViewById(R.id.imageView)


    }

    fun replaceData(teams: MutableList<Team>) {
        setList(teams)
        notifyDataSetChanged()
    }


    private fun setList(notes: MutableList<Team>) {
        this.teams = notes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.team_list_item, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val (name, national, countryName) = teams[position]
        holder.teamName.text = name
        holder.countryName.text = countryName
        holder.nationalTeam.visibility = if(national)  View.VISIBLE else View.INVISIBLE
        val generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate color based on a key (same key returns the same color), useful for list/grid views
        val color = generator.getColor(name)
        val drawable = TextDrawable.builder()
                .buildRound(name[0].toString(), color)
        holder.imageView.setImageDrawable(drawable)
    }


    override fun getItemCount(): Int {
        return teams.size
    }

}