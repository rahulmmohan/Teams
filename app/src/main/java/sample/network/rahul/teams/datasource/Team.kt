package sample.network.rahul.teams.datasource

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created by Rahul on 04/02/18.
 */
data class Team(
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("national")
    @Expose
    var national: Boolean,
    @SerializedName("country_name")
    @Expose
    var countryName: String)