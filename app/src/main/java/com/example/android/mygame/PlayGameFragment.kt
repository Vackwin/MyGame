package com.example.android.mygame

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.example.android.mygamelibrary.MyGameView
import com.example.android.mygamelibrary.Prize

class PlayGameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_play_game, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val game = requireView().findViewById<MyGameView>(R.id.my_game)
        val bmpA : Bitmap = BitmapFactory.decodeResource(resources,android.R.drawable.alert_dark_frame )
        val bmpB : Bitmap = BitmapFactory.decodeResource(resources,android.R.drawable.alert_light_frame)
        val prizeImage = requireView().findViewById<ImageView>(R.id.prize_image)
        val maxDrawTimes = 2
        val callback = object : MyGameView.MyCallback {
            override fun onPrizeSelected(prize: Prize, drawTimes: Int) {
                prizeImage.setImageBitmap(prize.img)
                game.visibility = View.INVISIBLE
                prizeImage.visibility = View.VISIBLE
                prizeImage.setOnClickListener {
                    if(drawTimes >= maxDrawTimes){
                        requireView().findNavController().navigate(R.id.action_playGameFragment_to_homeFragment)
                    }
                    it.visibility = View.GONE
                    game.visibility = View.VISIBLE
                }
            }
        }
        game.setPrizes(
                listOf(
                        Prize(0.5F,bmpA),
                        Prize(0.5F,bmpB)
                )
        )
        game.setCallback(callback)
    }

}