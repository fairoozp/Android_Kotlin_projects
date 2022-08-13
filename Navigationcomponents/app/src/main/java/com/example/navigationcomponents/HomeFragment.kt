package com.example.navigationcomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class HomeFragment : Fragment() {

    private val args : HomeFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val value = args.homeValue

        val homeButton : Button = view.findViewById(R.id.homeButton)
        val bottomNav : Button = view.findViewById(R.id.bottomNavButton)
        val homeTextView : TextView = view.findViewById(R.id.homeTextView)

        homeTextView.text = value

        val action = HomeFragmentDirections.actionHomeFragmentToSecondFragment("Message from home fragment")

        homeButton.setOnClickListener { Navigation.findNavController(view).navigate(action) }

        val bottom = HomeFragmentDirections.actionHomeFragmentToBottomNavigation()
        bottomNav.setOnClickListener { Navigation.findNavController(view).navigate(bottom) }

        return view
    }
}