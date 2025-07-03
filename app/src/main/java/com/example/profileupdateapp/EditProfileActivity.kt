package com.example.profileupdateapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.profileupdateapp.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("EditProfileActivity", "🔵 onCreate called")

        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentName = intent.getStringExtra("name") ?: ""
        val currentAge = intent.getStringExtra("age") ?: ""
        val currentEmail = intent.getStringExtra("email") ?: ""

        binding.etName.setText(currentName)
        binding.etAge.setText(currentAge)
        binding.etEmail.setText(currentEmail)

        Log.d("EditProfileActivity", "📋 Loaded data - Name: $currentName, Age: $currentAge, Email: $currentEmail")

        binding.btnSave.setOnClickListener {
            saveProfile()
        }

        binding.btnCancel.setOnClickListener {
            Log.d("EditProfileActivity", "❌ Cancel button clicked")
            Log.d("EditProfileActivity", "🔙 Returning to ProfileActivity with RESULT_CANCELED")
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun saveProfile() {
        val name = binding.etName.text.toString().trim()
        val age = binding.etAge.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()

        when {
            name.isEmpty() -> {
                binding.etName.error = "Name is required"
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                Log.d("EditProfileActivity", "❌ Validation failed - empty name")
                return
            }
            age.isEmpty() -> {
                binding.etAge.error = "Age is required"
                Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show()
                Log.d("EditProfileActivity", "❌ Validation failed - empty age")
                return
            }
            email.isEmpty() -> {
                binding.etEmail.error = "Email is required"
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
                Log.d("EditProfileActivity", "❌ Validation failed - empty email")
                return
            }
            !email.contains("@") -> {
                binding.etEmail.error = "Please enter a valid email"
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                Log.d("EditProfileActivity", "❌ Validation failed - invalid email format")
                return
            }
            age.toIntOrNull() == null || age.toInt() <= 0 || age.toInt() > 150 -> {
                binding.etAge.error = "Please enter a valid age (1-150)"
                Toast.makeText(this, "Please enter a valid age between 1 and 150", Toast.LENGTH_SHORT).show()
                Log.d("EditProfileActivity", "❌ Validation failed - invalid age")
                return
            }
        }

        Log.d("EditProfileActivity", "✅ Validation passed - saving profile")
        Log.d("EditProfileActivity", "💾 Saving profile - Name: $name, Age: $age, Email: $email")

        val resultIntent = Intent().apply {
            putExtra("name", name)
            putExtra("age", age)
            putExtra("email", email)
        }

        Toast.makeText(this, "Profile saved successfully!", Toast.LENGTH_SHORT).show()
        Log.d("EditProfileActivity", "🔙 Returning to ProfileActivity with RESULT_OK")

        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        Log.d("EditProfileActivity", "🟢 onStart called - Activity is becoming visible")
    }

    override fun onResume() {
        super.onResume()
        Log.d("EditProfileActivity", "▶️ onResume called - Activity is now active and ready for editing")
    }

    override fun onPause() {
        super.onPause()
        Log.d("EditProfileActivity", "⏸️ onPause called - Activity is losing focus")
    }

    override fun onStop() {
        super.onStop()
        Log.d("EditProfileActivity", "⏹️ onStop called - Activity is no longer visible")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("EditProfileActivity", "💀 onDestroy called - Activity is being destroyed")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("EditProfileActivity", "🔄 onRestart called - Activity is restarting from stopped state")
    }

    override fun onBackPressed() {
        Log.d("EditProfileActivity", "🔙 Back button pressed - canceling edit")
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
    }
}
