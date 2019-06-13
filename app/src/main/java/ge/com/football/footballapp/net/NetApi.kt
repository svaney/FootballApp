package ge.com.football.footballapp.net

import ge.com.football.footballapp.model.ModelMatch
import ge.com.football.footballapp.model.NetResult
import io.reactivex.Single
import retrofit2.http.GET

interface NetApi {
    @GET("v2/5b9264193300006b00205fb9")
    fun getMatchSummery(): Single<NetResult<ModelMatch>>
}