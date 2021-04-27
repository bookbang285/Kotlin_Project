package com.example.myapplication

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var mSpinner: Spinner
    private lateinit var Distance: EditText
    private lateinit var Stoptime: EditText
    private lateinit var Price: TextView
    private lateinit var priceshow: TextView

    var languages = arrayOf("County","Thai","Singapore","Indonesia","Malaysia","Vietnam","Japan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Distance = findViewById(R.id.KMtext)
        Price = findViewById(R.id.Caltext)
        Stoptime = findViewById(R.id.Stoptext)

        mSpinner = findViewById<Spinner>(R.id.spinner)
        val adapter =
            ArrayAdapter(baseContext, android.R.layout.simple_spinner_dropdown_item, languages)
        mSpinner.adapter = adapter    //กำหนดเป็นรายการของ Spinner

        findViewById<Button>(R.id.button).apply {
            setOnClickListener {
                calculate()
            }
        }
    }

    private fun calculate() {
        if(mSpinner.selectedItemPosition == 1){
            Price.text = caltaxithai()
        }else if (mSpinner.selectedItemPosition == 2){
            Price.text = caltaxising()
        }else if(mSpinner.selectedItemPosition == 3){
            Price.text = caltaxiIndo()
        }else if (mSpinner.selectedItemPosition == 4){
            Price.text = caltaxiMala()
        }else if (mSpinner.selectedItemPosition == 5){
            Price.text = caltaxiViet()
        }else if (mSpinner.selectedItemPosition == 6){
            Price.text = caltaxijapan()
        }else{
            toast("Please Enter Distance")
        }
        Price.onEditorAction(EditorInfo.IME_ACTION_DONE)
    }

    private fun caltaxijapan():String{
        return if (Distance.text.toString().toInt()==1) "138" else (((Distance.text.toString().toInt()-1)*120)+138+Stoptime.text.toString().toInt()*17).toString()
        //return ((Distance.text.toString().toInt())*120)+138+Stoptime.text.toString().toInt()*17).toString()
    }

    private fun caltaxiViet():String{
        return if (Distance.text.toString().toInt()==1) "14" else (((Distance.text.toString().toInt()-1)*15)+14+Stoptime.text.toString().toInt()*0.5).toString()
        //return ((Distance.text.toString().toInt())*15)+14+Stoptime.text.toString().toInt()*0.5).toString()
    }

    private fun caltaxiMala():String{
        return if (Distance.text.toString().toInt()==1) "24" else (((Distance.text.toString().toInt()-1)*12)+24+Stoptime.text.toString().toInt()*3).toString()
        //return ((Distance.text.toString().toInt()*12)+24+Stoptime.text.toString().toInt()*3).toString()
    }

    private fun caltaxiIndo():String{
        return if (Distance.text.toString().toInt()==1) "15" else (((Distance.text.toString().toInt()-1)*9)+15+Stoptime.text.toString().toInt()*2).toString()
        //return ((Distance.text.toString().toInt()*9)+15+Stoptime.text.toString().toInt()*2).toString()
    }

    private fun caltaxising(): String{
        return if (Distance.text.toString().toInt()==1) "83" else (((Distance.text.toString().toInt()-1)*13)+83+Stoptime.text.toString().toInt()*10).toString()
        //return ((Distance.text.toString().toInt()*13)+83+Stoptime.text.toString().toInt()*10).toString()
    }

    private fun caltaxithai(): String{
        var diss = Distance.text.toString().toDouble()
        var price:Double = Stoptime.text.toString().toDouble()*2

        if(diss > 80){
            val xdiss= diss-80
            price+= xdiss*10.5
            diss -= xdiss
        }
        if(diss > 60){
            val xdiss= diss-60
            price+= xdiss*9
            diss -= xdiss
        }
        if(diss > 40){
            val xdiss= diss-40
            price+= xdiss*8
            diss -= xdiss
        }
        if(diss > 20){
            val xdiss= diss-20
            price+= xdiss*7
            diss -= xdiss
        }
        if(diss > 10){
            val xdiss= diss-10
            price+= xdiss*6.5
            diss -= xdiss
        }
        if(diss > 1){
            val xdiss= diss-1
            price+= xdiss*5.5
            diss -= xdiss
        }
        if(diss == 1.0){
            price+= 35
        }
        if((price%2).toInt()==0)price++
        return price.toInt().toString()
    }

    private fun toast(msg: String) {
        val t = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        t.setGravity(Gravity.TOP or Gravity.CENTER, 0, 200)
        t.view?.background?.colorFilter = PorterDuffColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)
        t.view?.findViewById<TextView>(android.R.id.message)?.setTextColor(Color.WHITE)
        t.show()
    }

}