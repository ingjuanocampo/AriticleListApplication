package juanocampo.test.domain.usecase

import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessSuccess
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException
import org.mockito.Mockito.`when` as whenever


class ClearAllUseCaseTest: DomainTestBase() {


    lateinit var clearAllUseCase: ClearAllUseCase
    @Before
    override fun setUp() {
        super.setUp()
        clearAllUseCase = ClearAllUseCase(articlesRepository)
    }

    @Test
    fun deleteFailTest() {
        whenever(articlesRepository.deleteAll()).thenReturn(false)
        val result= clearAllUseCase()

        assert(result is ProcessError)
    }

    @Test
    fun whenThrownAnExceptionDeleteFails() {
        whenever(articlesRepository.deleteAll()).thenThrow(IllegalStateException::class.java)
        val result= clearAllUseCase()

        assert(result is ProcessError)
    }

    @Test
    fun deleteSucessTest() {
        whenever(articlesRepository.deleteAll()).thenReturn(true)
        val result= clearAllUseCase()

        assert(result is ProcessSuccess)
    }
}