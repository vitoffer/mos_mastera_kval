package com.example.kval1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userLogin: TextView = findViewById(R.id.login)
        val userPass: TextView = findViewById(R.id.pass)
        val btnLogin: Button = findViewById(R.id.button_login)

        val user = listOf("user1", "user1pass")

        btnLogin.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val pass = userPass.text.toString().trim()
            if (user[0] == login && user[1] == pass) {
                intent = Intent(this, UserDataActivity::class.java)
                startActivity(intent)
            }
        }

    }
}