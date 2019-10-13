package juanocampo.test.remote.di

import dagger.Module
import dagger.Provides
import juanocampo.test.data.source.RemoteDataSource
import juanocampo.test.remote.MapperImp
import juanocampo.test.remote.RemoteDataSourceImp
import juanocampo.test.remote.api.Service
import juanocampo.test.remote.api.di.ApiModule
import juanocampo.test.remote.mapper.Mapper

@Module(includes = [ApiModule::class])
class RemoteModule {

    @Provides
    fun providesRemoteDataSource(service: Service, mapper: Mapper) : RemoteDataSource =
        RemoteDataSourceImp(service, mapper)

    @Provides
    fun providesMapper(): Mapper = MapperImp()
}