package com.example.goonerquizahh

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second4)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val radioGroup2 = findViewById<RadioGroup>(R.id.radioGroup2)
        val btnNext2 = findViewById<Button>(R.id.btnNext2)
        val tvResult2 = findViewById<TextView>(R.id.tvResult2)

        btnNext2.setOnClickListener {
            val selectedId = radioGroup2.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedButton = findViewById<RadioButton>(selectedId)
            val answer = selectedButton.text.toString().trim().lowercase()

            if (answer == "death") {
                tvResult2.text = "Correct! 💀"
                playSound(R.raw.correct)

                val intent = Intent(this, ThirdActivity4::class.java)
                startActivity(intent)

                // Example: next question if you add it
                // val intent = Intent(this, ThirdActivity::class.java)
                // startActivity(intent)
            } else {
                tvResult2.text = "Incorrect! Try again."
                playSound(R.raw.incorrect)

            }
        }

    }

    private fun playSound(soundResId: Int) {
        val mediaPlayer = MediaPlayer.create(this, soundResId)
        mediaPlayer.start()

        mediaPlayer.setOnCompletionListener {
            mediaPlayer.reset()
            mediaPlayer.release()
        }
        mediaPlayer.start()
    }
}
