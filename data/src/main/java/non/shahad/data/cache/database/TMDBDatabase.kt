package non.shahad.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import non.shahad.data.cache.daos.MovieDao
import non.shahad.data.cache.entities.MovieEntity

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class TMDBDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}