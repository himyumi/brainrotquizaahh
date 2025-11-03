package com.example.goonerquizahh

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third4)

        // FIX: Changed R.id.main to R.id.third_main to match your new XML file
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.third_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --- 1. Find all the views from your XML layout ---
        // Use the new IDs from activity_third4.xml (e.g., radioGroup3)
        val radioGroup3 = findViewById<RadioGroup>(R.id.radioGroup3)
        val btnNext3 = findViewById<Button>(R.id.btnNext3)
        val tvResult3 = findViewById<TextView>(R.id.tvResult3)

        // --- 2. Set the click listener for the button ---
        btnNext3.setOnClickListener {
            val selectedId = radioGroup3.checkedRadioButtonId

            if (selectedId == -1) {
                // Show a message if no answer is selected
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedButton = findViewById<RadioButton>(selectedId)
            val answer = selectedButton.text.toString().trim().lowercase()

            // --- 3. Check the answer for the third riddle ---
            if (answer == "gooner") {
                tvResult3.text = "Correct! ✨"
                playSound(R.raw.correct) // Play correct sound
                val intent = Intent(this, FinalActivity4::class.java)
                startActivity(intent)

            } else {
                tvResult3.text = "Incorrect! Try again."
                playSound(R.raw.incorrect) // Play incorrect sound
            }
        }
    } // <-- onCreate method ends here

    // --- 4. Add the same helper function to play sounds ---
    private fun playSound(soundResourceId: Int) {
        val mediaPlayer = MediaPlayer.create(this, soundResourceId)
        // Release the player once the sound has finished to save memory
        mediaPlayer.setOnCompletionListener { mp ->
            mp.reset()
            mp.release()
        }
        mediaPlayer.start() // Play the sound
    }

} // <-- ThirdActivity4 class ends here