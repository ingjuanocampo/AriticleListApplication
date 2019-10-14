package juanocampo.test.domain.usecase

import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessSuccess
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SynServerInfoUseCaseTest: DomainTestBase() {


    lateinit var synServerInfoUseCase: SynServerInfoUseCase
    @Before
    override fun setUp() {
        super.setUp()
        synServerInfoUseCase = SynServerInfoUseCase(articlesRepository)
    }

    @Test
    fun whenSynFailSyncUseCaseFails() {
        Mockito.`when`(articlesRepository.sync()).thenReturn(false)
        val result= synServerInfoUseCase()

        assert(result is ProcessError)
    }

    @Test
    fun whenMarkInitialUnReadPostFailsSyncUseCaseFails() {
        Mockito.`when`(articlesRepository.markAsReadInitialData()).thenReturn(false)
        val result= synServerInfoUseCase()

        assert(result is ProcessError)
    }


    @Test
    fun whenMarkInitialUnReadAndSyncSuccessPostSyncUseCaseSuccess() {
        Mockito.`when`(articlesRepository.markAsReadInitialData()).thenReturn(true)
        Mockito.`when`(articlesRepository.sync()).thenReturn(true)
        val result= synServerInfoUseCase()

        assert(result is ProcessSuccess)
    }
}