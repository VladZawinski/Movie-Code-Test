package non.shahad.moviecodemanagement.features.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import non.shahad.domain.flow.LDEFlow
import non.shahad.domain.usecase.DetailUseCase
import non.shahad.moviecodemanagement.base.MVIViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: DetailUseCase
): MVIViewModel<DetailState,DetailSideEffect>() {

    override val container: Container<DetailState, DetailSideEffect>
        = container(DetailState())

    fun fetch(id: Int) = intent {
        useCase.streamMovieDetail(id)
            .flowOn(Dispatchers.IO)
            .collectLatest {
                when(it){
                    is LDEFlow.Data -> {
                        reduce { state.copy(movie = it.data, isLoading = false) }
                    }
                    is LDEFlow.Error -> {
                        postSideEffect(DetailSideEffect.ShowSnackBar(it.message))
                    }
                    LDEFlow.Loading -> {
                        reduce { state.copy(isLoading = true) }
                    }
                }
            }
    }

    fun actionFavorite() = intent {
        state.movie?.let {
            if (it.isFavorite){
                useCase.removeFromFavorite(it.id).collect()
                reduce { state.copy(movie = it.copy(isFavorite = false)) }
                postSideEffect(DetailSideEffect.ShowSnackBar("Remove from favorite"))
            } else {
                useCase.addToFavorite(it.id).collect()
                reduce { state.copy(movie = it.copy(isFavorite = true)) }
                postSideEffect(DetailSideEffect.ShowSnackBar("Added to favorite"))
            }
        }
    }

}