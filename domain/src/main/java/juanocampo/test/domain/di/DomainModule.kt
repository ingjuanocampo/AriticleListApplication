package juanocampo.test.domain.di

import dagger.Module
import dagger.Provides
import juanocampo.test.domain.repository.ArticlesRepository
import juanocampo.test.domain.usecase.ClearAllUseCase
import juanocampo.test.domain.usecase.SynServerInfoUseCase


@Module
class DomainModule {

    @Provides
    fun providesSynServerInfoUseCase(articlesRepository: ArticlesRepository) = SynServerInfoUseCase(articlesRepository)


    @Provides
    fun providesClearAllUseCase(articlesRepository: ArticlesRepository) = ClearAllUseCase(articlesRepository)

}