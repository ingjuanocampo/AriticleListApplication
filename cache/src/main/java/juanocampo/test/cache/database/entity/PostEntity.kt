package juanocampo.test.cache.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    @PrimaryKey
    var postId: String,
    var userId: String,
    var title: String,
    var body: String,
    var isFavorite: Boolean = false,
    var isRead:Boolean = true)