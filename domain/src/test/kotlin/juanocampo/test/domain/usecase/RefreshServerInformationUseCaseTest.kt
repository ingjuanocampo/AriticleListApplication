package juanocampo.test.domain.usecase

import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessSuccess
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class RefreshServerInformationUseCaseTest: DomainTestBase() {


    lateinit var refreshServerInformationUseCase: RefreshServerInformationUseCase

    @Mock
    lateinit var synServerInfoUseCase: SynServerInfoUseCase
    @Mock
    lateinit var clearAllUseCase: ClearAllUseCase

    @Before
    override fun setUp() {
        super.setUp()
        refreshServerInformationUseCase = RefreshServerInformationUseCase(synServerInfoUseCase, clearAllUseCase)
    }

    @Test
    fun whenCleanAllFailRefreshFail() {
        Mockito.`when`(clearAllUseCase()).thenReturn(ProcessError)
        Mockito.`when`(synServerInfoUseCase()).thenReturn(ProcessSuccess)

        val result= refreshServerInformationUseCase()

        assert(result is ProcessError)
    }

    @Test
    fun whenSyncFailRefreshFail() {
        Mockito.`when`(clearAllUseCase()).thenReturn(ProcessSuccess)
        Mockito.`when`(synServerInfoUseCase()).thenReturn(ProcessError)

        val result= refreshServerInformationUseCase()

        assert(result is ProcessError)
    }


    @Test
    fun whenCleanAllAndRefreshSuccessRefreshSuccess() {
        Mockito.`when`(clearAllUseCase()).thenReturn(ProcessSuccess)
        Mockito.`when`(synServerInfoUseCase()).thenReturn(ProcessSuccess)

        val result= refreshServerInformationUseCase()


        assert(result is ProcessSuccess)
    }
}