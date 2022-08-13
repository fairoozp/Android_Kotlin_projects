package com.example.navigationcomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigation : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_bottom_navigation, container, false)

        val bottomNavigation : BottomNavigationView = view.findViewById(R.id.bottomNavigationView)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        // val navController = findNavController(R.id.fragment)
        val navController = navHostFragment.navController
        //val navController =  findNavController()

        val appBar = AppBarConfiguration(setOf(R.id.home2, R.id.call, R.id.contact))
        setupActionBarWithNavController((activity as MainActivity) , navController, appBar)

        bottomNavigation.setupWithNavController(navController)

        return view
    }

}