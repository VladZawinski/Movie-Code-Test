package non.shahad.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import non.shahad.domain.flow.HomeDataFlow
import non.shahad.domain.flow.InteractorFlow
import non.shahad.domain.model.Movie
import non.shahad.domain.repository.HomeRepository
import non.shahad.domain.repository.MovieRepository
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class HomeUseCase  @Inject constructor(
    private val movieRepository: MovieRepository
) {

    fun streamHomeData(fresh: Boolean): Flow<HomeDataFlow> = flow {
        try {
            emit(HomeDataFlow.StartedLoading)

            val upcomingCache = movieRepository.cachedUpcomingMovies()
            val popularCache = movieRepository.cachedPopularMovies()


            emit(HomeDataFlow.Cached(upcomingCache,popularCache))

            if (fresh || upcomingCache.isExpired() || popularCache.isExpired()){

                val freshPopular = movieRepository.freshPopularMovies()
                val freshUpcoming = movieRepository.freshUpcomingMovies()

                movieRepository.storeToCache(freshPopular, "popular")
                movieRepository.storeToCache(freshUpcoming, "upcoming")

                emit(HomeDataFlow.FreshByCached(freshUpcoming, freshPopular))
            }

            emit(HomeDataFlow.Satisfied)

        } catch (e: Exception){
            Timber.d("$e")
            emit(HomeDataFlow.Error(e.message!!, e))
        }
    }

    fun removeFromFavorite(id: Int): Flow<InteractorFlow> = flow<InteractorFlow> {
        try {
            emit(InteractorFlow.JobStarted)
            movieRepository.removeFromFavorite(id)
            emit(InteractorFlow.Done)
        } catch (e: Exception){
            emit(InteractorFlow.Error(e.message!!, e))
        }
    }

    fun addToFavorite(id: Int): Flow<InteractorFlow> = flow {
        try {
            emit(InteractorFlow.JobStarted)
            movieRepository.addToFavorite(id)
            emit(InteractorFlow.Done)
        } catch (e: Exception){
            emit(InteractorFlow.Error(e.message!!, e))
        }
    }

    private fun List<Movie>.isExpired(): Boolean {
        return isEmpty() || firstOrNull() == null || isCacheExpired(first().lastUpdateTimestamp)
    }


    private fun isCacheExpired(validTimestamp: Long): Boolean {
        val now = Date().time
        return now > validTimestamp
    }

}