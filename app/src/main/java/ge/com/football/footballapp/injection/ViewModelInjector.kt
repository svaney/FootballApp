package ge.com.football.footballapp.injection

import dagger.Component
import ge.com.football.footballapp.base.MatchViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class)])
interface ViewModelInjector {

    fun inject(matchViewModel: MatchViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun netModule(netModule: NetModule): Builder
    }
}