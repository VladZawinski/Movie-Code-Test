package non.shahad.moviecodemanagement.features.home

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import non.shahad.domain.flow.HomeDataFlow
import non.shahad.domain.repository.HomeRepository
import non.shahad.domain.repository.MovieRepository
import non.shahad.domain.usecase.HomeUseCase
import non.shahad.moviecodemanagement.base.MVIViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase
): MVIViewModel<HomeState,HomeSideEffect>() {

    override val container: Container<HomeState, HomeSideEffect>
        = container(HomeState()) {
        fetch(false)
    }

    /**
     * Intent Scope
     */
    private fun fetch(fresh: Boolean) = intent {
        useCase.streamHomeData(fresh)
            .flowOn(Dispatchers.IO)
            .collectLatest {
                when(it){
                    is HomeDataFlow.Cached -> {
                        reduce { state.copy(
                            upcomingMovies = it.upcoming,
                            popularMovies = it.popular,
                            isLoading = false)
                        }
                    }
                    is HomeDataFlow.Error -> {
                        postSideEffect(HomeSideEffect.ShowSnackBar(it.message))
                    }
                    is HomeDataFlow.FreshByCached -> {
                        reduce { state.copy(
                            upcomingMovies = it.upcoming,
                            popularMovies = it.popular,
                            isLoading = false)
                        }
                    }
                    HomeDataFlow.Satisfied -> {
                        reduce { state.copy(
                            isLoading = false)
                        }
                    }
                    HomeDataFlow.StartedLoading -> {
                        reduce {
                            state.copy(isLoading = true)
                        }
                    }
                }
            }
    }

    fun refresh() = kotlin.run { fetch(true) }

}