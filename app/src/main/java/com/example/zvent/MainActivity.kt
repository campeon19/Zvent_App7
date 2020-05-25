package com.example.zvent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.zvent.data.Invitados
import com.example.zvent.data.InvitadosUser
import com.example.zvent.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity(), InvitadosUser {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration : AppBarConfiguration
    override val invitados: MutableList<Invitados> = arrayListOf(
        Invitados("Christian Perez", "4719-5171", "per19710@uvg.edu.gt"),
        Invitados("Pedro Lopez", "3759-4558", "pedro@gmail.com"),
        Invitados("Maria Rodriguez", "6887-9523", "rodriguezmaria38@gmail.com"),
        Invitados("Luisa Gallegos", "4280-2135", "gallegosluisa@gmail.com"),
        Invitados("Edward Paiz", "3862-1544", "edward1985.paiz@outlook.com"),
        Invitados("Alberto Hernandez", "3887-8798", "alberto.hern.1990@gmail.com")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout

        val navController = this.findNavController(R.id.myNavHostFragment)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        // prevent nav gesture if not on start destination
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, _: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    fun addInvitado(nombre: String, telefono: String, email: String){
        invitados.add(Invitados(nombre, telefono, email))
    }

}
