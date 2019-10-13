package juanocampo.test.articleapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import juanocampo.test.articleapp.R
import juanocampo.test.articleapp.ui.main.adapter.SectionsPagerAdapter
import juanocampo.test.presentation.viewmodel.MainViewModel
import juanocampo.test.presentation.viewmodel.factory.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    private lateinit var viewModel: MainViewModel
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(
            this,
            supportFragmentManager
        )
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            viewModel.deleteAll().observe((this), Observer{
                if (!it) {
                    Snackbar.make(view, "Something when wrong", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()                }
            })

        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)


        syncServer()


    }

    private fun syncServer() {
        viewModel.syncServerInformation().observe((this), Observer {
            progressBar.visibility = View.GONE
            if (!it) {
                Toast.makeText(this, "Something when wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.reloads -> {
                progressBar.visibility = View.VISIBLE
                syncServer()
                return false
            }
            else -> {
            }
        }

        return false
    }

}