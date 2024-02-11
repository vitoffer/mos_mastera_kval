package com.example.kval1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import org.w3c.dom.Text
import java.time.LocalDate
import java.time.Period

class UserDataActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)

        val user = User("user1photo", "2020-03-02","89167884612", "uio uio ada")

        val photoView = findViewById<ImageView>(R.id.photo)
        val birthDateView = findViewById<TextView>(R.id.birth_date)
        val ageView = findViewById<TextView>(R.id.age)
        val telView = findViewById<TextView>(R.id.tel)
        val statusView = findViewById<TextView>(R.id.status)
        val btnToRecords = findViewById<Button>(R.id.btn_to_records)

        val imageId = resources.getIdentifier(
            user.photo,
            "drawable",
            packageName
        )

        birthDateView.text = user.birthDate
        val bd = user.birthDate.split('-')
        ageView.text = getAge(bd[0].toInt(), bd[1].toInt(), bd[2].toInt()).toString()
        telView.text = user.tel
        statusView.text = user.status

        birthDateView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (birthDateView.text.toString().trim().length == 10) {
                    val bd1 = birthDateView.text.toString().trim().split('-')
                    ageView.text = getAge(bd1[0].toInt(), bd1[1].toInt(), bd1[2].toInt()).toString()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        photoView.setImageResource(imageId)

        btnToRecords.setOnClickListener {
            intent = Intent(this, UserRecordsActivity::class.java)
            startActivity(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAge(year: Int, month: Int, dayOfMonth: Int): Int {
        return Period.between(
            LocalDate.of(year, month, dayOfMonth),
            LocalDate.now()
        ).years
    }

}