        package com.example.gogreen

        import android.content.Intent
        import androidx.appcompat.app.AppCompatActivity
        import android.os.Bundle
        import android.util.Log
        import android.view.View
        import android.widget.Button
        import android.widget.EditText
        import android.widget.TextView
        import android.widget.Toast
        import com.google.firebase.auth.FirebaseAuth
        import com.google.firebase.database.DataSnapshot
        import com.google.firebase.database.DatabaseError
        import com.google.firebase.database.FirebaseDatabase
        import com.google.firebase.database.ValueEventListener
        import com.google.firebase.firestore.FirebaseFirestore

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
                    val username = usernameEditText.text.toString()
                    val password = passwordEditText.text.toString()
                    isValidLogin(username, password)

                }

                clickHereTextView.setOnClickListener {
                    val intent = Intent(this@MainActivity, Register::class.java)
                    startActivity(intent)
                }
            }

            private fun isValidLogin(username: String, password: String) {
                val auth = FirebaseAuth.getInstance()

                auth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser

                            if (user != null) {
                                val userId = user.uid
                                val databaseReference =
                                    FirebaseDatabase.getInstance().getReference("users/$userId")

                                databaseReference.addListenerForSingleValueEvent(object :
                                    ValueEventListener {
                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            val isAdmin = dataSnapshot.child("status")
                                                .getValue(Boolean::class.java)

                                            if (isAdmin == true) {
                                                // User is an admin, go to AdminActivity
                                                val intent = Intent(
                                                    this@MainActivity,
                                                    AdminActivity::class.java
                                                )
                                                startActivity(intent)
                                            } else {
                                                // User is not an admin, go to UsersActivity
                                                val intent = Intent(
                                                    this@MainActivity,
                                                    UsersActivity::class.java
                                                )
                                                startActivity(intent)
                                            }
                                        } else {
                                            Log.e("FirebaseDatabase", "User data does not exist")
                                            showToast("User data not found")
                                        }
                                    }

                                    override fun onCancelled(databaseError: DatabaseError) {
                                        Log.e(
                                            "FirebaseDatabase",
                                            "Error fetching user data: ${databaseError.message}"
                                        )
                                        showToast("Error: ${databaseError.message}")
                                    }
                                })
                            }
                        } else {
                            showToast("Login Failed. Please check your credentials.")
                        }
                    }
            }


            private fun showToast(message: String) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }