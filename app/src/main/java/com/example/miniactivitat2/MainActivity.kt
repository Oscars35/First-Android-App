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
import com.example.miniactivitat2.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Launcher for new activity (activityResult deprecated)
    private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            changeTextView(result.data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goToByeScreenBT.setOnClickListener {
            if (TextUtils.isEmpty(binding.enterMessageET.text))
                binding.enterMessageET.error = "No message was entered"
            else
                openActivity2()
        }
    }

    //Function that opens activity 2 and checks the number of repetitions. (Default repetitions 1)
    private fun openActivity2() {
        checkForEmptyRepetitions()
        val finalMessage = getFinalMessage(binding.enterMessageET.text.toString(), binding.enterRepetitionsET.text.toString().toInt())
        val intent = Intent(this, Activity2::class.java)
        binding.enterMessageET.text.clear()
        binding.enterRepetitionsET.text.clear()
        intent.putExtra("message", finalMessage)
        getResult.launch(intent)
    }

    //Establishing default repetitions to 1
    private fun checkForEmptyRepetitions() {
        if(TextUtils.isEmpty(binding.enterRepetitionsET.text.toString()))
            binding.enterRepetitionsET.setText("1")
    }


    //Changing text view with the text received from activity2
    private fun changeTextView(data: Intent?) {
        binding.textView2.text = data!!.getStringExtra("messageFromAct2").toString()
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