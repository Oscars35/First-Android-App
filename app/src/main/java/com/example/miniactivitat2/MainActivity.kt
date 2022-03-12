package com.example.miniactivitat2

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var editTextByeMessage: EditText? = null
    private var editTextRepetitions: EditText? = null
    private var mainTextView: TextView? = null

    //Launcher for new activity (activityResult deprecated)
    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            changeTextView(result.data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextByeMessage = findViewById<EditText>(R.id.enterMessageET)
        editTextRepetitions = findViewById<EditText>(R.id.enterRepetitionsET)
        mainTextView = findViewById<TextView>(R.id.textView2)

        val goByeScreenButton = findViewById<Button>(R.id.goToByeScreenBT)
        goByeScreenButton.setOnClickListener {
            if (TextUtils.isEmpty(editTextByeMessage!!.text.toString()))
                editTextByeMessage!!.error = "No message was entered"
            else
                openActivity2()
        }
    }

    //Function that opens activity 2 and checks the number of repetitions. (Default repetitions 1)
    private fun openActivity2() {
        checkForEmptyRepetitions()
        val finalMessage = getFinalMessage(editTextByeMessage!!.text.toString(), editTextRepetitions!!.text.toString().toInt())
        val intent = Intent(this, Activity2::class.java)
        editTextByeMessage!!.text.clear()
        editTextRepetitions!!.text.clear()
        intent.putExtra("message", finalMessage)
        getResult.launch(intent)
    }

    //Establishing default repetitions to 1
    private fun checkForEmptyRepetitions() {
        if(TextUtils.isEmpty(editTextRepetitions!!.text.toString()))
            editTextRepetitions!!.setText("1")
    }


    //Changing text view with the text received from activity2
    private fun changeTextView(data: Intent?) {
        mainTextView!!.text = data!!.getStringExtra("messageFromAct2").toString()
    }

    //Getting final message
    private fun getFinalMessage(message: String, repetitions: Int) : String {
        var finalMessage = ""
        for (i in 1..repetitions) {
            finalMessage = finalMessage.plus(message)
        }
        return finalMessage
    }
}