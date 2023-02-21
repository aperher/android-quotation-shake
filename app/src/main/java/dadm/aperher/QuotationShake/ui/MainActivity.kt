package dadm.aperher.QuotationShake.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import dadm.aperher.QuotationShake.R
import dadm.aperher.QuotationShake.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MenuProvider {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = binding.navHostFragment.getFragment<NavHostFragment>().navController
        (binding.bottomNavigation as NavigationBarView).setupWithNavController(navController)

        setSupportActionBar(binding.materialToolbar)
        val appBarConf : AppBarConfiguration = AppBarConfiguration(setOf(R.id.newQuotationFragment, R.id.favouritesFragment, R.id.settingsFragment))
        setupActionBarWithNavController(navController, appBarConf)

        addMenuProvider(this)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_about, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        navController.navigate(R.id.aboutDialogFragment)
        return true
    }
}