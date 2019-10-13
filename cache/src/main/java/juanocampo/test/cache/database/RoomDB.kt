package juanocampo.test.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import juanocampo.test.cache.database.dao.PostDao
import juanocampo.test.cache.database.entity.PostEntity


@Database(entities = [PostEntity::class], version = 1)
abstract class RoomDB : RoomDatabase() {

    abstract fun postDao(): PostDao
}