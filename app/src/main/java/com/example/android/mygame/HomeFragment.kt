package com.example.android.mygame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        root.findViewById<Button>(R.id.play_game_button).setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_playGameFragment)
        )
        return root
    }
}