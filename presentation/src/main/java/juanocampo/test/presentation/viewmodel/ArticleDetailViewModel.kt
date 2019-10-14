package juanocampo.test.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import juanocampo.test.domain.entity.Post
import juanocampo.test.domain.entity.User
import juanocampo.test.domain.status.PostSuccess
import juanocampo.test.domain.status.UserSuccess
import juanocampo.test.presentation.model.ArticleDetailModel

class ArticleDetailViewModel(private val model: ArticleDetailModel): ViewModel() {

    val postLiveData = MutableLiveData<Post>()
    val userLiveData = MutableLiveData<User>()
    val errorLive = MutableLiveData<Boolean>()

    private val compositeDisposable = CompositeDisposable()

    fun getPostId(id: String) {
        compositeDisposable.add(
            model.getPostById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it is PostSuccess) {
                        postLiveData.value = it.post
                    } else {
                        errorLive.value = true
                    }
                }, { errorLive.value = true }))
    }

    fun getUserId(id: String) {
        compositeDisposable.add(
            model.getUserById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it is UserSuccess) {
                        userLiveData.value = it.user
                    } else {
                        errorLive.value = true
                    }
                }, { errorLive.value = true }))
    }

}