package ge.com.football.footballapp.model.utils

enum class MatchActionType(val value: Int) {
    GOAL(1),
    YELLOW_CARD(2),
    RED_CARD(3),
    SUBSTITUTION(4)
}

enum class GoalType(val value: Int) {
    GOAL(1),
    OWN_GOAL(2)
}

enum class MatchTeamType(val value: Int) {
    TEAM1(1),
    TEAM2(2)
}
