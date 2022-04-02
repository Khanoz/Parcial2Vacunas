package com.example.vacunas

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vacunas.databinding.ActivityMainBinding
import com.example.vacunas.databinding.ActivityNavDrawerBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.empty_toolbar))
        supportActionBar?.title = ""
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        /*navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home ->{
                    findViewById<TextView>(R.id.main_toptext).text = ""
                    findViewById<TextView>(R.id.main_bottomtext).text = ""
                }
                R.id.navigation_dashboard -> {
                    findViewById<TextView>(R.id.main_toptext).text = "Vacunas"
                    findViewById<TextView>(R.id.main_bottomtext).text = "Viena Lujan"
                }
                R.id.navigation_notifications -> {
                    findViewById<TextView>(R.id.main_toptext).text = "Noticias"
                    findViewById<TextView>(R.id.main_bottomtext).text = ""
                }
            }
            true
        }*/

        val drawerlayout = findViewById<DrawerLayout>(R.id.container_drawer)

        toggle = ActionBarDrawerToggle(this, drawerlayout, R.string.open_drawer, R.string.close_drawer)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<NavigationView>(R.id.nav_view_d).setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_settings -> {
                        Log.d("abc", "funciono")
                        true
                    }
                else -> {
                    true
                }
            }
            drawerlayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}