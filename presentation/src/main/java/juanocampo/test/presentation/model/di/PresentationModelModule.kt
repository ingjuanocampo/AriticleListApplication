package juanocampo.test.presentation.model.di

import dagger.Module
import dagger.Provides
import juanocampo.test.domain.usecase.ClearAllUseCase
import juanocampo.test.domain.usecase.RefreshServerInformationUseCase
import juanocampo.test.domain.usecase.SynServerInfoUseCase
import juanocampo.test.presentation.model.MainModel
import juanocampo.test.presentation.model.imp.MainModuleImp

@Module
class PresentationModelModule {

    @Provides
    fun providesMainModel(synServerInfoUseCase: SynServerInfoUseCase, clearAllUseCase: ClearAllUseCase, refreshServerInformationUseCase: RefreshServerInformationUseCase): MainModel
            = MainModuleImp(synServerInfoUseCase, clearAllUseCase, refreshServerInformationUseCase)

}