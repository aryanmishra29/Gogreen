package com.example.gogreen.Login_Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.gogreen.R
import com.example.gogreen.User_Activity.UsersActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterScreen : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
   // private lateinit var addressEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var gender: String
    private lateinit var passwordEditText: EditText
    private lateinit var confirmEditText: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var usersRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)
        nameEditText = findViewById(R.id.fullNameEditText)
        emailEditText = findViewById(R.id.registerEmailEditText)
       // addressEditText = findViewById(R.id.)
        usernameEditText = findViewById(R.id.registerUsernameEditText)
        gender = ""
//        gender = findViewById(R.id.spinnerGender)
        passwordEditText = findViewById(R.id.registerPasswordEditText)
        confirmEditText = findViewById(R.id.confirmPasswordEditText)
        val submitButton: Button = findViewById(R.id.registerButton)
        val spinner = findViewById<Spinner>(R.id.spinnerGender)

        val genderOptions = arrayOf("Select the gender","Male", "Female", "Others")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0)
        var isGenderSelected = false
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Check if a valid selection (not the hint) is made
                if (position != 0) {
                    isGenderSelected = true
                    gender = genderOptions[position] // Capture the selected gender
                } else {
                    isGenderSelected = false
                    gender = "" // Reset gender when nothing is selected
                }
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
                Toast.makeText(this@RegisterScreen, "Please select a gender", Toast.LENGTH_SHORT).show()
            }
        }
        auth = FirebaseAuth.getInstance()


        val database = FirebaseDatabase.getInstance()
        usersRef = database.getReference("users")

        submitButton.setOnClickListener {
            val intent = Intent(this@RegisterScreen, UsersActivity::class.java)
            startActivity(intent)
            this.finish()

            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            //val address = addressEditText.text.toString()
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmEditText.text.toString()
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this@RegisterScreen, "Invalid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isGenderSelected) {
                Toast.makeText(this@RegisterScreen, "Please select a gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (password != confirmPassword) {
                Toast.makeText(this@RegisterScreen, "Password and confirm password do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }



            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val user = auth.currentUser
                        val userId = user?.uid
                        if (userId != null) {
                            val userData = User(name, email,
                                "", username, gender, password,false)
                            usersRef.child(userId).setValue(userData)
                            Toast.makeText(this@RegisterScreen, "register successfully", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@RegisterScreen, LoginScreen::class.java)
                            startActivity(intent)
                            this.finish()
                        }
                    } else {
                        //val errorMessage = task.exception?.message
                        Toast.makeText(this@RegisterScreen, "could not register", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}





