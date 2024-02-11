package com.example.kval1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserRecordsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_records)



        val types = listOf("количественный", "текстовый")
        val typeSpinner = findViewById<Spinner>(R.id.type_spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        typeSpinner.adapter = adapter

        val recordsList: RecyclerView = findViewById(R.id.records_list)
        val records = mutableListOf<UserRecord>()

        records.add(UserRecord(1, "количественный", "2020-02-02", "медицина", "89786627"))
        records.add(UserRecord(2, "текстовый", "2023-02-03", "медицина", "fkjshbfsfhb"))
        records.add(UserRecord(3, "текстовый", "2023-07-03", "школа", "фаифргаифгамигиуаг гфпагфпаоа арафоаиыа"))


        recordsList.layoutManager = LinearLayoutManager(this)
        recordsList.adapter = RecordsAdapter(records, this)

        val addRecBtn = findViewById<Button>(R.id.new_rec_btn)
        addRecBtn.setOnClickListener {
            val newRecType = typeSpinner.selectedItem.toString().trim()
            val newRecDate = findViewById<TextView>(R.id.new_rec_date)
            val newRecCategory = findViewById<TextView>(R.id.new_rec_category)
            val newRecText = findViewById<TextView>(R.id.new_rec_text)

            records.add(UserRecord(records.last().id, newRecType, newRecDate.text.toString().trim(), newRecCategory.text.toString().trim(), newRecText.text.toString().trim()))
            recordsList.adapter = RecordsAdapter(records, this)

            typeSpinner.setSelection(0)
            newRecDate.text = ""
            newRecCategory.text = ""
            newRecText.text = ""
        }

        val filterSpinner = findViewById<Spinner>(R.id.filter_spinner)
        val filterText = findViewById<TextView>(R.id.filter_text)

        val filterBtn = findViewById<Button>(R.id.filter_btn)
        val sortBtn = findViewById<Button>(R.id.sort_btn)
        val filterOptions = listOf("дата", "категория", "количественный", "текстовый")
        val filterAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, filterOptions)
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        filterSpinner.adapter = filterAdapter
        sortBtn.setOnClickListener {
            var records1: List<UserRecord>? = null
            if (filterSpinner.selectedItem.toString() == "дата") {
                records1 = records.sortedByDescending { record -> record.date }
            } else if (filterSpinner.selectedItem.toString() == "категория") {
                records1 = records.sortedByDescending { record -> record.category }
            } else if (filterSpinner.selectedItem.toString() == "количественный" || filterSpinner.selectedItem.toString() == "текстовый") {
                records1 = records.sortedBy { record -> record.type }
            }
            recordsList.adapter = RecordsAdapter(records1!!, this)
        }

        filterBtn.setOnClickListener {
            var records1: List<UserRecord>? = null
            if (filterSpinner.selectedItem.toString() == "дата") {
                records1 = records.filter { it.date == filterText.text.toString().trim() }
            } else if (filterSpinner.selectedItem.toString() == "категория") {
                records1 = records.filter { it.category == filterText.text.toString().trim() }
            } else if (filterSpinner.selectedItem.toString() == "текстовый") {
                records1 = records.filter { it.type == "текстовый" }
            } else if (filterSpinner.selectedItem.toString() == "количественный") {
                records1 = records.filter { it.type == "количественный" }
            }
            recordsList.adapter = RecordsAdapter(records1!!, this)
        }
    }
}