package juanocampo.test.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import juanocampo.test.presentation.entity.RecyclerViewType
import juanocampo.test.presentation.mapper.Mapper
import juanocampo.test.presentation.model.ArticleListModel

class ArticleListViewModel(private val model: ArticleListModel,
                           private val uiMapper: Mapper
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val posListLiveData = MutableLiveData<List<RecyclerViewType>>()
    private val deleteStatus = MutableLiveData<Boolean>()
    private val favoriteStatus = MutableLiveData<Boolean>()
    val errorLive = MutableLiveData<Boolean>()

    fun observePostList(isFavorite: Boolean): LiveData<List<RecyclerViewType>> {
        compositeDisposable.add(
            getObservableList(isFavorite)
                .map { list -> list.map { uiMapper.map(it) } }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        posListLiveData.value = it
                    }
                }, { errorLive.value = true })
        )
        return posListLiveData
    }

    private fun getObservableList(favorite: Boolean) = model.observePostList(favorite)

    fun deleteById(id: String): LiveData<Boolean> {
        val deleteDisposable = model.deleteById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                deleteStatus.value = true
            }, { deleteStatus.value = false })
        compositeDisposable.add(deleteDisposable)
        return deleteStatus
    }

    fun setAsFavorite(id: String, favorite: Boolean): LiveData<Boolean> {
        val favoriteDisposable = model.setAsFavoriteById(id, favorite)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                favoriteStatus.value = true
            }, { favoriteStatus.value = false })
        compositeDisposable.add(favoriteDisposable)
        return favoriteStatus
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}