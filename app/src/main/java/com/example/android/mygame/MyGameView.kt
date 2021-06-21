package com.example.android.mygame

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class MyGameView : ConstraintLayout {
    interface MyCallback{
        fun onPrizeSelected(prize: Prize, drawTimes: Int)
    }
    private var callback : MyCallback? = null
    private var prizes = mutableListOf<Prize>()
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
            context.theme.obtainStyledAttributes(
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
                    callback?.onPrizeSelected(prize, drawTimes)
            }
        }
    }
    fun setPrizes(prizes : List<Prize>){
        this.prizes.clear()
        this.prizes.addAll(prizes)
    }
    private fun drawPrize() : Prize {
        val randomProb: MutableList<Int> = mutableListOf()
        for(item in prizes){
            val x = Random().nextInt((item.probability*10000).toInt())
            randomProb.add(x)
        }
        if(prizes.isEmpty()){
            throw(IndexOutOfBoundsException("Need to setPrizes() first."))
        }
        return prizes[randomProb.indexOf(randomProb.maxOrNull())]

    }

}