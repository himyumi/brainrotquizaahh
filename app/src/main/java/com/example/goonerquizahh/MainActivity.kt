package com.example.goonerquizahh

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnNext.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedButton = findViewById<RadioButton>(selectedId)
            val answer = selectedButton.text.toString().trim().lowercase()

            if (answer == "time") {
                tvResult.text = "Correct! ⏳"
                playSound(R.raw.correct)
                // Proceed to second question
                val intent = Intent(this, SecondActivity4::class.java)
                startActivity(intent)
            } else {
                tvResult.text = "Incorrect! Try again."
                playSound(R.raw.incorrect)
            }
        }
    }

    private fun playSound(soundResId: Int) {
        val mediaPlayer = MediaPlayer.create(this, soundResId)

        mediaPlayer.setOnCompletionListener {
            it.release()
        }

        mediaPlayer.start()
    }
}
