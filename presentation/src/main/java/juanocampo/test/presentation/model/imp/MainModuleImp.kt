package juanocampo.test.presentation.model.imp

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import juanocampo.test.domain.status.ProcessStatus
import juanocampo.test.domain.usecase.ClearAllUseCase
import juanocampo.test.domain.usecase.SynServerInfoUseCase
import juanocampo.test.presentation.model.MainModel

class MainModuleImp(private val syncServerInformationUseCase: SynServerInfoUseCase,
                    private val clearAllUseCase: ClearAllUseCase
): MainModel {

    override fun syncServerInfo(): Completable {
        return subscribeCompletableIO { syncServerInformationUseCase() }
    }

    override fun deleteAll(): Completable {
        return subscribeCompletableIO { clearAllUseCase() }
    }

    private fun subscribeCompletableIO(function: () -> ProcessStatus): Completable {
        return Completable.fromCallable { function() }.subscribeOn(Schedulers.io())
    }
}