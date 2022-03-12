package com.example.miniactivitat2

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        val goMainScreenButton = findViewById<Button>(R.id.activity2Button)
        getMessageFromMain()
        goMainScreenButton.setOnClickListener {
            goBackToMainActivity()
        }
    }

    //Function that gets the message from activity_main
    private fun getMessageFromMain() {
        val message = intent.getStringExtra("message")
        val textView = findViewById<TextView>(R.id.textViewActivity2)
        textView.text = message
        intent.putExtra("messageFromAct2", message)
        setResult(Activity.RESULT_OK, intent)
    }

    //Going back to main activity
    private fun goBackToMainActivity() {
        finish()
    }
}