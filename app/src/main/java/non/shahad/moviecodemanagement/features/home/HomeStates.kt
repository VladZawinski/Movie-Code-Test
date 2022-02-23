package non.shahad.moviecodemanagement.features.home

import non.shahad.domain.model.Movie

data class HomeState(
    val popularMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
    val isLoading: Boolean = true
)

sealed class HomeSideEffect {
    data class ShowSnackBar(val message: String): HomeSideEffect()
}