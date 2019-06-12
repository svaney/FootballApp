package ge.com.football.footballapp.model

data class ModelMatchSummary(
    val actionTime: String,
    val team1Action: List<ModeTeamAction>?,
    val team2Action: List<ModeTeamAction>?
)