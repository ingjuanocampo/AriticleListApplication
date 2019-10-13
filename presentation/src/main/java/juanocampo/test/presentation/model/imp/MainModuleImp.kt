package juanocampo.test.presentation.model.imp

import io.reactivex.Completable
import juanocampo.test.domain.usecase.ClearAllUseCase
import juanocampo.test.domain.usecase.SynServerInfoUseCase
import juanocampo.test.presentation.model.MainModel

class MainModuleImp(private val syncServerInformationUseCase: SynServerInfoUseCase,
                    private val clearAllUseCase: ClearAllUseCase
): MainModel {

    override fun syncServerInfo(): Completable {
        return syncServerInformationUseCase()
    }

    override fun deleteAll(): Completable {
        return clearAllUseCase()
    }
}