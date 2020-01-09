package juanocampo.test.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import juanocampo.test.presentation.entity.PostViewType
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
    private var toggleSort = false

    val errorLive = MutableLiveData<Boolean>()

    fun observePostList(isFavorite: Boolean): LiveData<List<RecyclerViewType>> {
        compositeDisposable.add(
            getObservableList(isFavorite)
                .map { list -> list.map { uiMapper.map(it) } }
                .map { sortItems(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        posListLiveData.value = it
                    }
                }, { errorLive.value = true })
        )
        return posListLiveData
    }

    fun sort() {
        toggleSort = !toggleSort
        compositeDisposable.add(
            Single.fromCallable { posListLiveData.value }
                .map { sortItems(it) }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    if (it != null) {
                        posListLiveData.value = emptyList()
                        posListLiveData.value = it
                    }
                }, { errorLive.value = true })
        )

    }

    private fun sortItems(list: List<RecyclerViewType>): List<RecyclerViewType> {
        val mutableList = list.toMutableList()
        if (toggleSort) {
            mutableList.sortWith(nameComparator)
        } else {
            mutableList.sortWith(normalComparator)
        }
        return mutableList
    }

    val nameComparator = Comparator { r1: RecyclerViewType?, r2: RecyclerViewType? ->
        if (r1 is PostViewType && r2 is PostViewType) {
            val title1 = r1.title ?: ""
            val title2 = r2.title ?: ""
            return@Comparator title1.compareTo(title2)
        } else {
            val id1 = r1?.getDelegateId() ?: 0
            val id2 = r2?.getDelegateId() ?: 0
            return@Comparator id1.compareTo(id2)
        }
    }

    val normalComparator = Comparator { r1: RecyclerViewType?, r2: RecyclerViewType? ->
        val id1 = r1?.getDelegateId() ?: 0
        val id2 = r2?.getDelegateId() ?: 0
        return@Comparator id1.compareTo(id2)
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