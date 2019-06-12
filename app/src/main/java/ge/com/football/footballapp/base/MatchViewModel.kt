package ge.com.football.footballapp.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import ge.com.football.footballapp.R
import ge.com.football.footballapp.net.NetApi
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
                { onGetMatchSuccess() },
                {
                    System.out.println(it)
                    onGetMatchError()
                }
            )
    }

    private fun onGetMatchLoading() {
        matchLoading.value = true
        matchErrorTextId.value = null
    }

    private fun onGetMatchTerminate() {
        matchLoading.value = false
        matchErrorTextId.value = null
    }

    private fun onGetMatchSuccess() {
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