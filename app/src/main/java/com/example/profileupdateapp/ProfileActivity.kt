package com.example.profileupdateapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.profileupdateapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var editProfileLauncher: ActivityResultLauncher<Intent>

    private var profile = Profile()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ProfileActivity", "🔵 onCreate called")

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActivityResultLauncher()
        updateProfileDisplay()

        binding.btnEdit.setOnClickListener {
            Log.d("ProfileActivity", "📝 Edit button clicked - launching EditProfileActivity")
            Log.d("ProfileActivity", "⏸️ ProfileActivity will be PAUSED (not destroyed)")

            val intent = Intent(this, EditProfileActivity::class.java).apply {
                putExtra("name", profile.name)
                putExtra("age", profile.age)
                putExtra("email", profile.email)
            }
            editProfileLauncher.launch(intent)
        }
    }

    private fun setupActivityResultLauncher() {
        editProfileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            Log.d("ProfileActivity", "📬 Received result from EditProfileActivity")
            Log.d("ProfileActivity", "Result code: ${if (result.resultCode == Activity.RESULT_OK) "OK" else "CANCELED"}")

            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { data ->
                    profile.name = data.getStringExtra("name") ?: profile.name
                    profile.age = data.getStringExtra("age") ?: profile.age
                    profile.email = data.getStringExtra("email") ?: profile.email

                    Log.d("ProfileActivity", "✅ Profile updated - Name: ${profile.name}, Age: ${profile.age}, Email: ${profile.email}")
                    updateProfileDisplay()
                }
            } else {
                Log.d("ProfileActivity", "❌ Edit was canceled - no changes made")
            }
        }
    }

    private fun updateProfileDisplay() {
        binding.tvName.text = profile.name
        binding.tvAge.text = profile.age
        binding.tvEmail.text = profile.email
        Log.d("ProfileActivity", "🔄 Profile display updated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("ProfileActivity", "🟢 onStart called - Activity is becoming visible")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ProfileActivity", "▶️ onResume called - Activity is now active and ready for user interaction")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ProfileActivity", "⏸️ onPause called - Another activity is taking focus (EditProfileActivity launched)")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ProfileActivity", "⏹️ onStop called - Activity is no longer visible")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ProfileActivity", "💀 onDestroy called - Activity is being destroyed")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("ProfileActivity", "🔄 onRestart called - Activity is restarting from stopped state")
    }
}
