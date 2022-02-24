package non.shahad.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import non.shahad.domain.flow.InteractorFlow
import non.shahad.domain.flow.LDEFlow
import non.shahad.domain.model.Movie
import non.shahad.domain.repository.MovieRepository
import javax.inject.Inject
import kotlin.Exception

class DetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    fun streamMovieDetail(id: Int) = flow<LDEFlow<Movie>> {
       try {
           emit(LDEFlow.Loading)
           val result = movieRepository.getMovieById(id)
           emit(LDEFlow.Data(result!!))
       }catch (e: Exception) {
           emit(LDEFlow.Error(e.message!!,e))
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
}