package juanocampo.test.domain.usecase


import juanocampo.test.domain.status.ProcessError
import juanocampo.test.domain.status.ProcessSuccess
import org.junit.Before
import org.junit.Test
import java.lang.IllegalStateException
import org.mockito.Mockito.`when` as whenever

class DeleteByIdUseCaseTest : DomainTestBase() {


    lateinit var deleteByIdUseCase: DeleteByIdUseCase


    @Before
    override fun setUp() {
        super.setUp()
        deleteByIdUseCase = DeleteByIdUseCase(articlesRepository)
    }


    @Test
    fun deleteByIdFailTest() {
        whenever(articlesRepository.deleteById("1")).thenReturn(false)
        val result = deleteByIdUseCase("1")

        assert(result is ProcessError)
    }

    @Test
    fun whenThrownAnExceptionDeleteFails() {
        whenever(articlesRepository.deleteById("1")).thenThrow(IllegalStateException::class.java)
        val result = deleteByIdUseCase("1")

        assert(result is ProcessError)
    }


    @Test
    fun deleteByIdSuccessTest() {
        whenever(articlesRepository.deleteById("1")).thenReturn(true)
        val result = deleteByIdUseCase("1")

        assert(result is ProcessSuccess)
    }

}