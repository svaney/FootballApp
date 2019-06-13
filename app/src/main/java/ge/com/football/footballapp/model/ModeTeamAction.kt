package ge.com.football.footballapp.model

import com.google.gson.annotations.SerializedName
import ge.com.football.footballapp.model.utils.MatchActionType
import ge.com.football.footballapp.model.utils.MatchTeamType

data class ModeTeamAction(
    val actionType: Int?,
    val teamType: Int?,
    val action: ModelAction
)
