package juanocampo.test.presentation.di

import dagger.Module
import dagger.Provides
import juanocampo.test.presentation.model.MainModel
import juanocampo.test.presentation.model.di.PresentationModelModule
import juanocampo.test.presentation.viewmodel.factory.MainViewModelFactory

@Module(includes = [PresentationModelModule::class])
class PresentationModule {

    @ActivityScope
    @Provides
    fun providesMainViewModelFactory(model: MainModel) = MainViewModelFactory(model = model)
}