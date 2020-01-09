package juanocampo.test.presentation.model.di

import dagger.Module
import dagger.Provides
import juanocampo.test.domain.usecase.*
import juanocampo.test.presentation.model.ArticleDetailModel
import juanocampo.test.presentation.model.ArticleListModel
import juanocampo.test.presentation.model.MainModel
import juanocampo.test.presentation.model.imp.ArticleDetailModelImp
import juanocampo.test.presentation.model.imp.ArticleListModelImp
import juanocampo.test.presentation.model.imp.MainModuleImp
import juanocampo.test.presentation.usecase.SorterUIArticleUseCase

@Module
class PresentationModelModule {

    @Provides
    fun providesSorterArticleUseCase() = SorterUIArticleUseCase()

    @Provides
    fun providesMainModel(
        synServerInfoUseCase: SynServerInfoUseCase,
        clearAllUseCase: ClearAllUseCase,
        refreshServerInformationUseCase: RefreshServerInformationUseCase
    ): MainModel =
        MainModuleImp(synServerInfoUseCase, clearAllUseCase, refreshServerInformationUseCase)

    @Provides
    fun providesArticleListModel(
        getListPostUseCase: GetListPostUseCase,
        favoriteUseCase: FavoriteUseCase,
        deleteByIdUseCase: DeleteByIdUseCase,
        sorterArticleUseCase: SorterUIArticleUseCase
    ):
            ArticleListModel = ArticleListModelImp(
        getListPostUseCase,
        favoriteUseCase,
        deleteByIdUseCase,
        sorterArticleUseCase
    )

    @Provides
    fun providesArticleDetailModel(
        getPostByIdUseCase: GetPostByIdUseCase,
        getUserByIdUseCase: GetUserByIdUseCase,
        favoriteUseCase: FavoriteUseCase
    ): ArticleDetailModel =
        ArticleDetailModelImp(getPostByIdUseCase, getUserByIdUseCase, favoriteUseCase)
}