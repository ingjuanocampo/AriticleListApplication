package juanocampo.test.articleapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import juanocampo.test.articleapp.R
import juanocampo.test.articleapp.ui.main.adapter.SectionsPagerAdapter
import juanocampo.test.presentation.viewmodel.MainViewModel
import juanocampo.test.presentation.viewmodel.factory.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
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
        val refresh: FloatingActionButton = findViewById(R.id.refresh)

        fab.setOnClickListener { view ->
            viewModel.deleteAll()
        }

        refresh.setOnClickListener { view ->
            refresh()
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.deleteStatus.observe((this), Observer {
            if (!it) {
                Snackbar.make(fab, "Something when wrong", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        })

        viewModel.sycStatus.observe((this), Observer {
            progressBar.visibility = View.GONE
            if (!it) {
                Toast.makeText(this, "Something when wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        syncServer()
    }

    private fun syncServer() {
        progressBar.visibility = View.VISIBLE
        viewModel.syncServerInformation()
    }

    private fun refresh() {
        progressBar.visibility = View.VISIBLE
        viewModel.refreshServerInformation()

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
                syncServer()
                return false
            }
            else -> {
            }
        }

        return false
    }

}