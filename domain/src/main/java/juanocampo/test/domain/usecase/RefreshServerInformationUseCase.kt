package juanocampo.test.domain.usecase

import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessStatus
import juanocampo.test.domain.status.ProcessSuccess

class RefreshServerInformationUseCase(private val synServerInfoUseCase: SynServerInfoUseCase,
                                      private val clearAllUseCase: ClearAllUseCase) {

    operator fun invoke(): ProcessStatus {
        val clear = clearAllUseCase()
        val sync = synServerInfoUseCase()
        return if (clear is ProcessSuccess && sync is ProcessSuccess) ProcessSuccess else ProcessError
    }

}