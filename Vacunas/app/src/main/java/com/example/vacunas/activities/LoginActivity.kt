package com.example.vacunas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.vacunas.R
import com.example.vacunas.fragments.RecoverAccountFragment
import com.example.vacunas.Utils

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Utils.getVaccineNamesFromDB()
        findViewById<Button>(R.id.go_to_recover_account).setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RecoverAccountFragment()).commit()
            findViewById<ConstraintLayout>(R.id.login_container).visibility = View.GONE
        }
        findViewById<Button>(R.id.go_to_register).setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.login_btn).setOnClickListener {
            val email = findViewById<EditText>(R.id.email).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            Utils.auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    Utils.userid = Utils.auth.currentUser!!.uid
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    this.finish()
                }
        }
    }
}