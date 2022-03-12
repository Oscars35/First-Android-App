package com.example.miniactivitat2

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.miniactivitat2.databinding.Activity2Binding
import com.example.miniactivitat2.databinding.ActivityMainBinding

class Activity2 : AppCompatActivity() {

    private lateinit var binding: Activity2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        getMessageFromMain()
        binding.activity2Button.setOnClickListener {
            goBackToMainActivity()
        }
    }

    //Function that gets the message from activity_main
    private fun getMessageFromMain() {
        val message = intent.getStringExtra("message")
        binding.textViewActivity2.text = message
        intent.putExtra("messageFromAct2", message)
        setResult(Activity.RESULT_OK, intent)
    }

    //Going back to main activity
    private fun goBackToMainActivity() {
        finish()
    }
}