package com.example.notes.login

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.notes.R
import com.example.notes.SignUp.SignUpActivity
import com.example.notes.note.BaseActivity
import com.example.notes.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), View.OnClickListener {
    private var auth: FirebaseAuth? = null
    override fun initComponents() {
        btLogin.setOnClickListener(this)
        btSignUp.setOnClickListener(this)
        initialise()
    }

    private fun initialise() {
        auth = FirebaseAuth.getInstance();
        if (auth!!.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    fun signIn() {
        var email = teEmail.text.toString().trim()
        var password = tePassword.text.toString().trim()
        if (!email.isEmpty() && !password.isEmpty()) {
            doLogin(email, password)
        } else {
            Toast.makeText(this, "please fill the credentials", Toast.LENGTH_SHORT).show()
        }
    }
private fun doLogin(email :String, password :String){
    auth?.signInWithEmailAndPassword(email, password)
        ?.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )
            } else {
                Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()

            }
        }
}
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btLogin -> {
                signIn()

            }
            R.id.btSignUp -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }


        }
    }
}