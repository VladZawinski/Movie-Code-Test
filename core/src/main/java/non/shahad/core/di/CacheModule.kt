package non.shahad.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import non.shahad.data.cache.database.TMDBDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideTMDBDatabase(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        TMDBDatabase::class.java,
        "tmdb"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideMovieDao(db: TMDBDatabase) = db.movieDao()

}