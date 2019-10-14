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

@Module
class PresentationModelModule {

    @Provides
    fun providesMainModel(synServerInfoUseCase: SynServerInfoUseCase, clearAllUseCase: ClearAllUseCase, refreshServerInformationUseCase: RefreshServerInformationUseCase): MainModel
            = MainModuleImp(synServerInfoUseCase, clearAllUseCase, refreshServerInformationUseCase)

    @Provides
    fun providesArticleListModel(getListPostUseCase: GetListPostUseCase, favoriteUseCase: FavoriteUseCase): ArticleListModel = ArticleListModelImp(getListPostUseCase, favoriteUseCase)

    @Provides
    fun providesArticleDetailModel(getPostByIdUseCase: GetPostByIdUseCase, getUserByIdUseCase: GetUserByIdUseCase): ArticleDetailModel
            = ArticleDetailModelImp(getPostByIdUseCase, getUserByIdUseCase)
}