package com.example.notes.SignUp

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.notes.note.BaseActivity
import com.example.notes.R
import com.example.notes.login.LoginActivity
import com.example.notes.ui.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.actiity_signup.*
import kotlinx.android.synthetic.main.actiity_signup.btSignUp
import kotlinx.android.synthetic.main.actiity_signup.teEmail
import kotlinx.android.synthetic.main.actiity_signup.tePassword



@Suppress("DEPRECATION")
class SignUpActivity : BaseActivity(),View.OnClickListener {
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null


    override fun initComponents() {
        initialise()
    }

    override fun getLayoutId(): Int {
        return R.layout.actiity_signup
    }

    private fun initialise() {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")
        mAuth = FirebaseAuth.getInstance()
        btSignUp.setOnClickListener(this)
        btSignIn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btSignUp -> {
                signUp()

            }
            R.id.btSignIn -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }


    fun signUp() {
        var email = teEmail.text.toString().trim()
        var password = tePassword.text.toString().trim()
        var name = teName.text.toString().trim()
        var phone = teMobile.text.toString().trim()
        if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !phone.isEmpty()) {
            doLogin(email, password)
        } else {
            Toast.makeText(this, "please fill the details", Toast.LENGTH_SHORT).show()
        }
    }


    private fun doLogin(email: String, password: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this,
                OnCompleteListener<AuthResult?> { task ->
                    Toast.makeText(
                        this, "Succesfully Registered" + task.isSuccessful,
                        Toast.LENGTH_LONG
                    ).show()
                    if (!task.isSuccessful) {
                        Toast.makeText(
                            this, "Registration failed" + task.exception,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                })

    }
}


