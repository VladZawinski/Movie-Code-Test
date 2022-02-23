package non.shahad.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import non.shahad.data.service.TMDBService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Used static url as easy to set up.
     * and we will use default okhttp client so that
     * we don't need to add any interceptor
     */
    @Provides
    @Singleton
    fun provideAppRetrofit() = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/3/")
        .build()

    @Provides
    @Singleton
    fun provideTMDBService(retrofit: Retrofit) =
        retrofit.create(TMDBService::class.java)

}