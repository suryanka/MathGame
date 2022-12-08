package com.ghosh.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class MultiActivity : AppCompatActivity() {
    lateinit var textScore: TextView
    lateinit var textLife: TextView
    lateinit var textTime: TextView

    lateinit var textQuestion: TextView
    lateinit var editTextAnswer: TextView

    lateinit var buttonOk: Button
    lateinit var buttonNext: Button

    var corrrectAnswer: Int =0
    var userScore: Int=0
    var userLife: Int=3

    lateinit var timer: CountDownTimer
    private val startTimerInMilis: Long= 60000
    var timeLeftInMilis: Long= startTimerInMilis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi)

        supportActionBar!!.title="Multiplication"

        textScore= findViewById(R.id.textViewScore)
        textLife=findViewById(R.id.textViewLife)
        textTime=findViewById(R.id.textViewTime)

        buttonOk=findViewById(R.id.buttonOk)
        buttonNext=findViewById(R.id.buttonNext)

        textQuestion=findViewById(R.id.textViewQuestion)
        editTextAnswer=findViewById(R.id.editTextAnswer)

        gameContinue()

        buttonOk.setOnClickListener {

            val input=editTextAnswer.text.toString()

            if(input=="")
            {
                Toast.makeText(applicationContext,"Please enter the answer or click next button",
                    Toast.LENGTH_LONG).show()
            }
            else
            {
                pauseTimer()

                val userAnswer=input.toInt()
                if(userAnswer== corrrectAnswer  && (textQuestion.text!="Congratulations!! Your answer is correct" &&
                            textQuestion.text!="Sorry!! your answer is wrong"))
                {
                    userScore= userScore+10
                    textQuestion.text="Congratulations!! Your answer is correct"
                    textScore.text=userScore.toString()
                }
                else if(textQuestion.text=="Congratulations!! Your answer is correct" ||
                    textQuestion.text=="Sorry!! your answer is wrong")
                {
                    Toast.makeText(this@MultiActivity,"Please click the next Button!!", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    userLife--
                    textQuestion.text="Sorry!! your answer is wrong"
                    textLife.text=userLife.toString()
                }
            }
        }

        buttonNext.setOnClickListener {
            pauseTimer()
            resetTimer()

            editTextAnswer.text=""

            if(userLife<=0)
            {
                Toast.makeText(applicationContext,"Game Over", Toast.LENGTH_LONG).show()
                val intent= Intent(this@MultiActivity,ResultActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()
            }
            else
            {
                gameContinue()
            }
        }
    }

    fun gameContinue()
    {
        var num1= Random.nextInt(0,100)
        var num2= Random.nextInt(0,100)

        textQuestion.text="$num1 * $num2"
        corrrectAnswer=num1*num2

        startTimer()

    }

    fun startTimer()
    {
        timer= object:CountDownTimer(timeLeftInMilis, 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMilis=millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()
                userLife--
                textLife.text=userLife.toString()
                textQuestion.text="Sorry ! Time is up"
            }

        }.start()
    }

    fun updateText()
    {
        val remainingTime: Int= (timeLeftInMilis/1000).toInt()
        textTime.text= String.format(Locale.getDefault(),"%02d",remainingTime)
    }
    fun pauseTimer()
    {
        timer.cancel()
    }
    fun resetTimer()
    {
        timeLeftInMilis=startTimerInMilis
        updateText()
    }
}