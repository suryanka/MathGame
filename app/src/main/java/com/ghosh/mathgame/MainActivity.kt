package com.ghosh.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var addition:Button
    lateinit var subtraction:Button
    lateinit var multi:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition=findViewById(R.id.buttonAdd)
        subtraction=findViewById(R.id.buttonSub)
        multi=findViewById(R.id.buttonMul)

        addition.setOnClickListener {
            var intent= Intent(this@MainActivity,GameActivity::class.java)
            startActivity(intent)

        }
        subtraction.setOnClickListener {
            var intent= Intent(this@MainActivity,SubActivity::class.java)
            startActivity(intent)
        }
        multi.setOnClickListener {
            var intent=Intent(this@MainActivity,MultiActivity::class.java)
            startActivity(intent)
        }
    }
}