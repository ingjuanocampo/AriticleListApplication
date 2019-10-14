package juanocampo.test.domain.usecase

import juanocampo.test.domain.entity.Post
import juanocampo.test.domain.status.PostError
import juanocampo.test.domain.status.PostSuccess
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import org.mockito.Mockito.`when` as whenever


class GetPostByIdUseCaseTest: DomainTestBase() {

    private val postId= "1"

    @Mock
    lateinit var post: Post

    lateinit var getPostByIdUseCase: GetPostByIdUseCase
    @Before
    override fun setUp() {
        super.setUp()
        getPostByIdUseCase = GetPostByIdUseCase(articlesRepository)
    }

    @Test
    fun getPostIdFailMarkReadTest() {
        whenever(articlesRepository.getPostById(postId)).thenReturn(post)
        whenever(articlesRepository.setAsReadById(postId)).thenReturn(false)

        val result= getPostByIdUseCase(postId)

        assert(result is PostError)
    }

    @Test
    fun getPostIdFailPostInfoTest() {
        whenever(articlesRepository.getPostById(postId)).thenReturn(null)
        whenever(articlesRepository.setAsReadById(postId)).thenReturn(true)

        val result= getPostByIdUseCase(postId)

        assert(result is PostError)
    }

    @Test
    fun getPostIdFailPostInfoAndMarkReadTest() {
        whenever(articlesRepository.getPostById(postId)).thenReturn(null)
        whenever(articlesRepository.setAsReadById(postId)).thenReturn(false)

        val result= getPostByIdUseCase(postId)

        assert(result is PostError)
    }

    @Test
    fun getPostIdSuccessTest() {
        whenever(articlesRepository.getPostById(postId)).thenReturn(post)
        whenever(articlesRepository.setAsReadById(postId)).thenReturn(true)

        val result= getPostByIdUseCase(postId)

        assert(result is PostSuccess)
        if (result is PostSuccess)
            assert(result.post == post)
    }

}