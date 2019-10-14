package juanocampo.test.cache.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import juanocampo.test.cache.LocalDataSourceImp
import juanocampo.test.cache.MapperImp
import juanocampo.test.cache.database.RoomDB
import juanocampo.test.cache.database.di.DatabaseModule
import juanocampo.test.cache.mapper.Mapper
import juanocampo.test.data.source.LocalDataSource
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
class CacheModule {

    @Singleton
    @Provides
    fun providesLocalRepository(db: RoomDB, mapper: Mapper): LocalDataSource = LocalDataSourceImp(db, mapper)

    @Reusable
    @Provides
    fun providesMapper(): Mapper = MapperImp()
}