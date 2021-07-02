package com.example.android.mygame

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        val view = requireView()
        val imgTrophy = view.findViewById<ImageView>(R.id.img_trophy)
        val tv = view.findViewById<TextView>(R.id.tv)
        val tvTry = view.findViewById<TextView>(R.id.tv_try)
        val maxDrawTimes = 2
        val callback = object : MyGameView.MyCallback {
            override fun onPrizeSelected(prize: Prize, drawTimes: Int) {
                imgTrophy.visibility = View.VISIBLE
                tv.visibility = View.VISIBLE
                tvTry.visibility = View.INVISIBLE
                view.setOnClickListener {
                    if(drawTimes >= maxDrawTimes){
                        it.findNavController().navigate(R.id.action_playGameFragment_to_homeFragment)
                    }
                    imgTrophy.visibility = View.INVISIBLE
                    tv.visibility = View.INVISIBLE
                    tvTry.visibility = View.VISIBLE
                    game.hidePrize()
                    game.showUi()
                }
            }
        }
        game.setPrizes(
                listOf(
                        Prize(0.5F,bmpB,"白方","白色方塊"),
                        Prize(0.5F,bmpA,"黑方","黑色方塊")
                )
        )
        game.setCallback(callback)
    }

}


