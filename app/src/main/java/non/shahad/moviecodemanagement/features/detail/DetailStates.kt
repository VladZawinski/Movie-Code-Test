package non.shahad.moviecodemanagement.features.detail

import non.shahad.domain.model.Movie

data class DetailState(
    val movie: Movie? = null,
    val isLoading: Boolean = true
)

sealed class DetailSideEffect {
    data class ShowSnackBar(val message: String): DetailSideEffect()
}