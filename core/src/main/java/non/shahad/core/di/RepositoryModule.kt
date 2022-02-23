package non.shahad.core.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import non.shahad.data.cache.daos.MovieDao
import non.shahad.data.repository.HomeRepositoryImpl
import non.shahad.data.repository.MovieRepositoryImpl
import non.shahad.data.service.TMDBService
import non.shahad.domain.repository.HomeRepository
import non.shahad.domain.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideMovieRepository(service: TMDBService, dao: MovieDao): MovieRepository {
        return MovieRepositoryImpl(service, dao)
    }
}