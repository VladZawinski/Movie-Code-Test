package non.shahad.data.repository

import non.shahad.data.cache.daos.MovieDao
import non.shahad.data.mappers.toDomainModel
import non.shahad.data.mappers.toEntity
import non.shahad.data.service.TMDBService
import non.shahad.domain.model.Movie
import non.shahad.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: TMDBService,
    private val dao: MovieDao
): MovieRepository {

    companion object {
        const val POPULAR = "popular"
        const val UPCOMING = "upcoming"
    }

    override suspend fun freshPopularMovies(): List<Movie> {
        val response = service.getPopularMovies()
        if (response.results.isEmpty()) return emptyList()

        return response.results.map { it.toDomainModel(POPULAR) }
    }

    override suspend fun freshUpcomingMovies(): List<Movie> {
        val response = service.getUpcomingMovies()
        if (response.results.isEmpty()) return emptyList()

        return response.results.map { it.toDomainModel(UPCOMING) }
    }

    override suspend fun cachedPopularMovies(): List<Movie> {
        return dao.queryAll(POPULAR)?.map { it.toDomainModel() } ?: emptyList()
    }

    override suspend fun cachedUpcomingMovies(): List<Movie> {
        return dao.queryAll(UPCOMING)?.map { it.toDomainModel() } ?: emptyList()
    }


    override suspend fun storeToCache(movies: List<Movie>,query: String) {
        try {
            dao.deleteAndInsertByQuery(movies.map { it.toEntity(query) },query)
        } catch (e: Throwable){

        }
    }

    override suspend fun updateMovie(movie: Movie) {
        try {
            dao.update(movie.toEntity())
        } catch (e: Throwable){

        }
    }

    override suspend fun getMovieById(id: Int): Movie? {
        dao.findById(id)?.let {
            return it.toDomainModel()
        }

        return null
    }

    override suspend fun addToFavorite(id: Int) {
        dao.changeStatusById(id, true)
    }

    override suspend fun removeFromFavorite(id: Int) {
        dao.changeStatusById(id, false)
    }

}