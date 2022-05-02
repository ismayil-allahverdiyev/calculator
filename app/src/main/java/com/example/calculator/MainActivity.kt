package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException
import kotlin.math.floor

class MainActivity : AppCompatActivity() {

    private var resultInput : TextView? = null

    var lastIsNumber = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultInput = findViewById(R.id.result)
    }

    fun onDigit(view: View){
        val viewButton = view as Button
        resultInput?.append(viewButton.text)
        lastDot = false
        lastIsNumber = true
    }

    fun onClear(view: View){
        resultInput?.text = ""
    }

    fun onDot(view: View){
        print(lastDot)
        if(lastDot == false && lastIsNumber == true){
            resultInput?.append(".")
            lastIsNumber = false
            lastDot = true

        }
        print(lastDot)
    }

    fun onEquals(view: View){

        try {
            var checker = false
            if(resultInput?.text?.startsWith("-") == true){

                resultInput?.text = resultInput?.text?.substring(1)
                checker = true

            }
            if (resultInput?.text?.contains("-") == true) {

                val calculation = resultInput?.text?.split("-")

                resultInput?.text =
                    ((if (checker === true) -1 else 1) * calculation?.elementAt(0)?.toDouble()!! - calculation.elementAt(1)
                        .toDouble()).toString()
            } else if (resultInput?.text?.contains("+") == true) {

                val calculation = resultInput?.text?.split("+")


                resultInput?.text =
                    ((if (checker === true) -1 else 1) * calculation?.elementAt(0)?.toDouble()!! + calculation.elementAt(1)
                        .toDouble()).toString()
            } else if (resultInput?.text?.contains("x") == true) {

                val calculation = resultInput?.text?.split("x")


                resultInput?.text =
                    ((if (checker === true) -1 else 1) * calculation?.elementAt(0)?.toDouble()!! * calculation.elementAt(1)
                        .toDouble()).toString()
            } else if (resultInput?.text?.contains("/") == true) {

                val calculation = resultInput?.text?.split("/")


                resultInput?.text =
                    ((if (checker === true) -1 else 1) * calculation?.elementAt(0)?.toDouble()!! / calculation.elementAt(1)
                        .toDouble()).toString()
            }

            var resultInDouble = resultInput?.text?.toString()?.toDouble()

            if (resultInDouble == resultInDouble?.let { floor(it) }){
                resultInput?.text = resultInDouble?.toInt().toString()
            }
        } catch (e : ArithmeticException){

            print(e)

        }

    }

    fun onOperator(view: View){
        resultInput?.text?.let {

            if(it.isEmpty()){
                if((view as Button).text == "-"){
                    resultInput?.append((view as Button).text)
                    lastIsNumber = false
                    lastDot = false
                }
            }else if(isOperatorAdded(it.toString())){

            }else{

                if(lastIsNumber == true && lastDot == false){
                    resultInput?.append(" ${(view as Button).text} ")
                    lastIsNumber = false
                    lastDot = false
                }
            }
        }
    }

    private fun  isOperatorAdded(value : String) : Boolean{
        if(value.startsWith("-") == true){
            if(value.substring(1, value.length).contains("-") ||
                value.substring(1, value.length).contains("+") ||
                value.substring(1, value.length).contains("/") ||
                value.substring(1, value.length).contains("x")
            ){
                return true
            }else return false
        }else{
            if(value.contains("-") ||
                value.contains("+") ||
                value.contains("/") ||
                value.contains("x")
            ){
                return true
            }else return false
        }
    }
}