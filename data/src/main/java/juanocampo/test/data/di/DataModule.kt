package juanocampo.test.data.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import juanocampo.test.data.ArticleRepositoryImp
import juanocampo.test.data.MapperImp
import juanocampo.test.data.mapper.Mapper
import juanocampo.test.data.source.LocalDataSource
import juanocampo.test.data.source.RemoteDataSource
import juanocampo.test.domain.repository.ArticlesRepository


@Module
class DataModule {

    @Provides
    fun providesArticlesRepository(localDataSource: LocalDataSource,
                                   remoteDataSource: RemoteDataSource,
                                   mapper: Mapper
    ): ArticlesRepository = ArticleRepositoryImp(localDataSource, remoteDataSource, mapper)

    @Reusable
    @Provides
    fun providesMapper(): Mapper = MapperImp()

}