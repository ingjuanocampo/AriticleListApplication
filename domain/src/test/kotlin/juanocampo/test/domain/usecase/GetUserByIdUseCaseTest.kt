package juanocampo.test.domain.usecase

import juanocampo.test.domain.entity.User
import juanocampo.test.domain.status.UserError
import juanocampo.test.domain.status.UserSuccess
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class GetUserByIdUseCaseTest: DomainTestBase() {

    private val userId = "1"

    @Mock
    lateinit var user: User

    lateinit var getUserByIdUseCase: GetUserByIdUseCase
    @Before
    override fun setUp() {
        super.setUp()
        getUserByIdUseCase = GetUserByIdUseCase(articlesRepository)
    }


    @Test
    fun getUserByIdFailTest() {
        Mockito.`when`(articlesRepository.getUserById(userId)).thenReturn(null)

        val result= getUserByIdUseCase(userId)

        assert(result is UserError)
    }

    @Test
    fun getPostIdSuccessTest() {
        Mockito.`when`(articlesRepository.getUserById(userId)).thenReturn(user)

        val result= getUserByIdUseCase(userId)

        assert(result is UserSuccess)
        if (result is UserSuccess)
            assert(result.user == user)
    }
}