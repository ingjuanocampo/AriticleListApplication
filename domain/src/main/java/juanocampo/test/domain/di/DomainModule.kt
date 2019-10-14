package juanocampo.test.domain.di

import dagger.Module
import dagger.Provides
import juanocampo.test.domain.repository.ArticlesRepository
import juanocampo.test.domain.usecase.*

@Module
class DomainModule {

    @Provides
    fun providesSynServerInfoUseCase(articlesRepository: ArticlesRepository) = SynServerInfoUseCase(articlesRepository)

    @Provides
    fun providesClearAllUseCase(articlesRepository: ArticlesRepository) = ClearAllUseCase(articlesRepository)

    @Provides
    fun providesRefreshUseCase(synServerInfoUseCase: SynServerInfoUseCase, clearAllUseCase: ClearAllUseCase) = RefreshServerInformationUseCase(synServerInfoUseCase, clearAllUseCase)

    @Provides
    fun providesGetListPostUseCase(articlesRepository: ArticlesRepository) = GetListPostUseCase(articlesRepository)

    @Provides
    fun providesFavoriteUseCase(articlesRepository: ArticlesRepository) = FavoriteUseCase(articlesRepository)

    @Provides
    fun providesGetUserByidUseCase(articlesRepository: ArticlesRepository) = GetUserByIdUseCase(articlesRepository)

    @Provides
    fun providesGetPostByIduUseCase(articlesRepository: ArticlesRepository) = GetPostByIdUseCase(articlesRepository)

    @Provides
    fun providesDeleteByIdUseCase(articlesRepository: ArticlesRepository) = DeleteByIdUseCase(articlesRepository)

}