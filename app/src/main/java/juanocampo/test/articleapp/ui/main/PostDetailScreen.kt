package juanocampo.test.articleapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import juanocampo.test.articleapp.R
import juanocampo.test.domain.entity.Post
import javax.inject.Inject

class PostDetailScreen: AppCompatActivity() {

    private var title: TextView? = null
    private var readDoc: View? = null
    private var starFavorite: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

}
