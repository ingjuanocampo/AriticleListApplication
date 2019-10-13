package juanocampo.test.presentation.model.di

import dagger.Module
import dagger.Provides
import juanocampo.test.presentation.model.MainModel
import juanocampo.test.presentation.model.imp.MainModuleImp

@Module
class PresentationModelModule {


    @Provides
    fun providesMainModel(): MainModel = MainModuleImp()

}