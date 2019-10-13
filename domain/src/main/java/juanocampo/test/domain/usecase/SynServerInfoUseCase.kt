package juanocampo.test.domain.usecase

import io.reactivex.Observable
import juanocampo.test.domain.repository.ArticlesRepository
import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessStatus
import juanocampo.test.domain.status.ProcessSuccess

class SynServerInfoUseCase(private val repository: ArticlesRepository) {

    operator fun invoke() : Observable<ProcessStatus> {
        return Observable.fromCallable {
            return@fromCallable try {
                val sync = repository.sync()
                val updateProcess = repository.markAsReadInitialData()
                if (sync && updateProcess) ProcessSuccess else ProcessError
            } catch (e: Exception) {
                ProcessError
            }
        }
    }
}