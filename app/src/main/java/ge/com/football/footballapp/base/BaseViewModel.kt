package ge.com.football.footballapp.base

import androidx.lifecycle.ViewModel
import ge.com.football.footballapp.injection.NetModule
import ge.com.football.footballapp.injection.ViewModelInjector
import ge.com.football.footballapp.injection.DaggerViewModelInjector

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .netModule(NetModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MatchViewModel -> injector.inject(this)
        }
    }
}