package juanocampo.test.presentation.di

import dagger.Module
import dagger.Provides
import juanocampo.test.presentation.mapper.Mapper
import juanocampo.test.presentation.model.ArticleDetailModel
import juanocampo.test.presentation.model.ArticleListModel
import juanocampo.test.presentation.model.MainModel
import juanocampo.test.presentation.model.di.PresentationModelModule
import juanocampo.test.presentation.viewmodel.MapperImp
import juanocampo.test.presentation.viewmodel.factory.ArticleDetailViewModelFactory
import juanocampo.test.presentation.viewmodel.factory.ArticleListViewModelFactory
import juanocampo.test.presentation.viewmodel.factory.MainViewModelFactory

@Module(includes = [PresentationModelModule::class])
class PresentationModule {

    @ActivityScope
    @Provides
    fun providesMainViewModelFactory(model: MainModel) = MainViewModelFactory(model = model)

    @ActivityScope
    @Provides
    fun providesArticleListViewModelFactory(model: ArticleListModel, mapper: Mapper) =
        ArticleListViewModelFactory(
            model, mapper
        )

    @Provides
    fun providesMapper(): Mapper = MapperImp()

    @ActivityScope
    @Provides
    fun providesArticleDetailViewModelFactory(articleDetailModel: ArticleDetailModel) = ArticleDetailViewModelFactory(articleDetailModel)

}