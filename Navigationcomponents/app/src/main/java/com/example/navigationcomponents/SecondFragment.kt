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

class SecondFragment : Fragment() {

    private val args : SecondFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        val value = args.secondValue

        val secondTextView : TextView = view.findViewById(R.id.secondTextView)

        secondTextView.text = value
        val secondButton : Button = view.findViewById(R.id.secondButton)
        val action = SecondFragmentDirections.actionSecondFragmentToHomeFragment("Message from second fragment")
        secondButton.setOnClickListener { Navigation.findNavController(view).navigate(action) }

        return view
    }
}