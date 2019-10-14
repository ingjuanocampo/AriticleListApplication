package juanocampo.test.cache.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import juanocampo.test.cache.database.RoomDB
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDB(context: Context): RoomDB =
        Room.databaseBuilder(context, RoomDB::class.java, "RoomDB").build()
}