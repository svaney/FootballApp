package ge.com.football.footballapp.model

import ge.com.football.footballapp.model.utils.MatchActionType
import ge.com.football.footballapp.model.utils.MatchTeamType

data class ModeTeamAction(
    val actionType: MatchActionType,
    val teamType: MatchTeamType,
    val action: ModelAction
)
