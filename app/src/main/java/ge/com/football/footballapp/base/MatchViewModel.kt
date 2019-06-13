package ge.com.football.footballapp.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import ge.com.football.footballapp.R
import ge.com.football.footballapp.model.ModelMatch
import ge.com.football.footballapp.model.NetResult
import ge.com.football.footballapp.net.NetApi
import ge.com.football.footballapp.net.utils.RESPONSE_OK
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MatchViewModel : BaseViewModel() {
    @Inject
    lateinit var netApi: NetApi

    private lateinit var subscription: Disposable

    val matchLoading: MutableLiveData<Boolean> = MutableLiveData()
    val matchErrorTextId: MutableLiveData<Int> = MutableLiveData()
    val matchInfo: MutableLiveData<ModelMatch> = MutableLiveData()

    val retryClickListener = View.OnClickListener { loadMatch() }

    init {
        loadMatch()
    }

    private fun loadMatch() {
        subscription = netApi.getMatchSummery()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onGetMatchLoading() }
            .subscribe(
                {
                    result -> onGetMatchSuccess(result)
                },
                {
                    onGetMatchError()
                }
            )
    }

    private fun onGetMatchLoading() {
        matchLoading.value = true
        matchErrorTextId.value = null
    }

    private fun onGetMatchSuccess(result: NetResult<ModelMatch>) {
        when (result.resultCode){
            RESPONSE_OK -> matchInfo.value = result.match
            else -> {
                matchErrorTextId.value = R.string.network_error
            }
        }
        matchLoading.value = false
    }

    private fun onGetMatchError() {
        matchLoading.value = false
        matchErrorTextId.value = R.string.network_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}