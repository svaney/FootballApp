package ge.com.football.footballapp.model

data class ModelMatch(
    val matchTime : Double,
    val matchDate: Long,
    val stadiumAdress: String,
    val team1: ModelTeam,
    val team2: ModelTeam,
    val matchSummary: ModelMatchSummaryWrapper
)
