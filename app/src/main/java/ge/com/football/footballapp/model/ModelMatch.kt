package ge.com.football.footballapp.model

import ge.com.football.footballapp.model.utils.GoalType
import ge.com.football.footballapp.model.utils.MatchActionType
import java.text.SimpleDateFormat
import java.util.*

data class ModelMatch(
    val matchTime: Number,
    val matchDate: Long,
    val stadiumAdress: String,
    val team1: ModelTeam,
    val team2: ModelTeam,
    val matchSummary: ModelMatchSummaryWrapper
)

fun ModelMatch.getDisplayedTime(): String {
    return matchTime.toInt().toString() + "'"
}

fun ModelMatch.getDisplayedDate(): String {
    val sdf = SimpleDateFormat("dd MMMM yyyy")
    val netDate = Date(matchDate)
    return sdf.format(netDate)
}

fun ModelMatch.getFirstHalfSummery(): List<ModelSummary> {
    return matchSummary.summaries.filter { summery ->
        summery.actionTime.toInt() <= 45
    }
}

fun ModelMatch.getSecondHalfSummery(): List<ModelSummary> {
    return matchSummary.summaries.filter { summery ->
        summery.actionTime.toInt() > 45
    }
}

fun ModelMatch.getMatchScore(): String {
    return team1.score.toString() + " : " + team2.score.toString()
}

fun ModelMatch.getDisplayedScoreByHalf(half: Int): String{
    return getTeamScore(1, half) + " : " + getTeamScore(2, half)
}

fun ModelMatch.getTeamScore(team: Int, half: Int): String {
    val summeries = if (half == 1) getFirstHalfSummery() else getSecondHalfSummery()

    val goals = summeries.filter { summery ->

        val teamActions = if (team == 1) summery.team1Action else summery.team2Action

        teamActions?.firstOrNull { teamAction ->
            teamAction.actionType == MatchActionType.GOAL.value
                    && teamAction.action.goalType == GoalType.GOAL.value
        } != null
    }.size

    val opposeTeamOwnGoals = summeries.filter { summery ->

        val teamActions = if (team == 1) summery.team2Action else summery.team1Action

        teamActions?.firstOrNull { teamAction ->
            teamAction.actionType == MatchActionType.GOAL.value
                    && teamAction.action.goalType == GoalType.OWN_GOAL.value
        } != null
    }.size

    return (goals + opposeTeamOwnGoals).toString()
}
