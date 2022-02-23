package non.shahad.domain.flow

import non.shahad.domain.model.Movie

sealed class HomeDataFlow {
    object StartedLoading: HomeDataFlow()

    data class FreshByCached(
        val upcoming: List<Movie>,
        val popular: List<Movie>
    ): HomeDataFlow()

    data class Cached(
        val upcoming: List<Movie>,
        val popular: List<Movie>
    ): HomeDataFlow()

    data class Error(val message: String,val cause: Exception? = null): HomeDataFlow()

    object Satisfied: HomeDataFlow()
}