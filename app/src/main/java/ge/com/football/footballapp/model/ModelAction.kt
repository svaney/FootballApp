package ge.com.football.footballapp.model

import ge.com.football.footballapp.model.utils.GoalType

data class ModelAction(
    val goalType: GoalType?,
    val player: ModelPlayer?,
    val player1: ModelPlayer?,
    val player2: ModelPlayer?
)