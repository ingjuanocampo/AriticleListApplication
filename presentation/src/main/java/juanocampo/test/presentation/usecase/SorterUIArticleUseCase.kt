package juanocampo.test.presentation.usecase

import juanocampo.test.presentation.entity.PostViewType
import juanocampo.test.presentation.entity.RecyclerViewType

class SorterUIArticleUseCase {

    operator fun invoke(toggleSort: Boolean, list: List<RecyclerViewType>): List<RecyclerViewType> {
        val mutableList = list.toMutableList()
        if (toggleSort) {
            mutableList.sortWith(nameComparator)
        } else {
            mutableList.sortWith(normalComparator)
        }
        return mutableList
    }

    private val nameComparator = Comparator { r1: RecyclerViewType?, r2: RecyclerViewType? ->
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

    private val normalComparator = Comparator { r1: RecyclerViewType?, r2: RecyclerViewType? ->
        val id1 = r1?.getDelegateId() ?: 0
        val id2 = r2?.getDelegateId() ?: 0
        return@Comparator id1.compareTo(id2)
    }
}