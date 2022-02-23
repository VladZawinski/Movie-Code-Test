package non.shahad.domain.repository

import non.shahad.domain.model.Movie

/**
 * @author Shahad Zawinski
 */
interface MovieRepository {
    suspend fun freshPopularMovies(): List<Movie>
    suspend fun freshUpcomingMovies(): List<Movie>
    suspend fun cachedPopularMovies(): List<Movie>
    suspend fun cachedUpcomingMovies(): List<Movie>
    suspend fun storeToCache(movies: List<Movie>)
    suspend fun updateMovie(movie: Movie)
}