package juanocampo.test.presentation.model.imp

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessStatus
import juanocampo.test.domain.usecase.ClearAllUseCase
import juanocampo.test.domain.usecase.RefreshServerInformationUseCase
import juanocampo.test.domain.usecase.SynServerInfoUseCase
import juanocampo.test.presentation.model.MainModel
import java.lang.IllegalStateException

class MainModuleImp(private val syncServerInformationUseCase: SynServerInfoUseCase,
                    private val clearAllUseCase: ClearAllUseCase,
                    private val refreshServerInformationUseCase: RefreshServerInformationUseCase
): MainModel {
    override fun refresh(): Completable {
        return subscribeCompletableIO {
             refreshServerInformationUseCase()
        }
    }

    override fun syncServerInfo(): Completable {
        return subscribeCompletableIO { syncServerInformationUseCase() }
    }

    override fun deleteAll(): Completable {
        return subscribeCompletableIO { clearAllUseCase() }
    }

    private fun subscribeCompletableIO(function: () -> ProcessStatus): Completable {

        return Completable.fromCallable {
            val processStatus = function()
            if  (processStatus is ProcessError) {
                throw IllegalStateException("Something when wrong")
            }
        }.subscribeOn(Schedulers.io())
    }
}