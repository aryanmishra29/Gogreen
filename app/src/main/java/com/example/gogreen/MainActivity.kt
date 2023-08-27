package com.example.gogreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickHereTextView = findViewById<TextView>(R.id.text5)
        usernameEditText = findViewById(R.id.editTextInput1)
        passwordEditText = findViewById(R.id.editTextInput2)
        val loginButton: Button = findViewById(R.id.btn1)

        loginButton.setOnClickListener {
            // Get the entered username and password
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Perform login validation (you can add your logic here)
            if (isValidLogin(username, password)) {
                showToast("Login Successful")
            } else {
                // Login failed, show an error toast
                showToast("Login Failed. Please check your credentials.")
            }
        }

        clickHereTextView.setOnClickListener {
            // Start the RegisterActivity when "Click here" is clicked
            val intent = Intent(this@MainActivity, Register::class.java)
            startActivity(intent)
        }
    }

    private fun isValidLogin(username: String, password: String): Boolean {
        // Implement your login validation logic here
        // For example, check if the username and password are correct
        // You can replace this with your actual validation logic
        return username == "your_username" && password == "your_password"
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    fun openRegisterActivity(view: View) {
        val intent = Intent(this@MainActivity, Register::class.java)
        startActivity(intent)
    }
}