package com.example.android.mygame

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class MyGameView : ConstraintLayout {
    interface MyCallback{
        fun onPrizeSelected(prize: Prize, drawTimes: Int)
    }
    private lateinit var callback : MyCallback
    private lateinit var prizes : List<Prize>
    private var imgBtn1 : ImageButton
    private var imgBtn2 : ImageButton
    private var imgBtn3 : ImageButton
    private var imgBtn4 : ImageButton
    private var drawTimes = 0
    constructor(context: Context):this(context,null)
    constructor(context: Context, attrs: AttributeSet?):this(context, attrs,0)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        View.inflate(context, R.layout.mygameview, this)
        imgBtn1 = findViewById(R.id.img1)
        imgBtn2 = findViewById(R.id.img2)
        imgBtn3 = findViewById(R.id.img3)
        imgBtn4 = findViewById(R.id.img4)
        setListeners()
        if (attrs != null) {
            val attributes = context.theme.obtainStyledAttributes(
                    attrs,
                    R.styleable.MyGameView,
                    0, 0
            )
        }
    }
    fun setCallback(cb : MyCallback){
        callback = cb
    }
    private fun setListeners() {
        val imgButtons : List<ImageButton> = listOf(
                imgBtn1,
                imgBtn2,
                imgBtn3,
                imgBtn4
        )
        for(item in imgButtons){
            item.setOnClickListener{
                    drawTimes++
                    val prize = drawPrize()
                    callback.onPrizeSelected(prize, drawTimes)
            }
        }
    }
    fun setPrizes(prizes : List<Prize>){
        this.prizes = prizes
    }
    private fun drawPrize() : Prize {
        val randomProb = mutableListOf<Int>()
        for(item in prizes){
            val x = Random().nextInt((item.probability*10000).toInt())
            randomProb.add(x)
        }
        for(item in randomProb){
            Log.e("randomP", "${item}")
        }
        return prizes[randomProb.indexOf(randomProb.maxOrNull())]
    }

}