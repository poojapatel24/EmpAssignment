package com.task.empassignment.ui

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.task.empassignment.R
import com.task.empassignment.databinding.ActivityMainBinding
import com.task.empassignment.databinding.ActivitySplashBinding
import com.task.empassignment.localdatabase.DBHelper

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val query = binding.editText.text.toString()
            executeQuery(query)
        }
    }

    private fun executeQuery(query: String) {
        val dbHelper = DBHelper(this)
        val db = dbHelper.readableDatabase
        try {
            val cursor = db.rawQuery(query, null)
            val resultText = formatCursor(cursor)
            binding.textView.text = resultText
            cursor.close()
        } catch (e: Exception) {
            binding.textView.text = "Error: ${e.message}"
        } finally {
            db.close()
        }
    }

    private fun formatCursor(cursor: Cursor): String {
        val stringBuilder = StringBuilder()
        val columnNames = cursor.columnNames
        while (cursor.moveToNext()) {
            for (columnName in columnNames) {
                val columnIndex = cursor.getColumnIndex(columnName)
                stringBuilder.append("$columnName: ${cursor.getString(columnIndex)}\n")
            }
            stringBuilder.append("\n")
        }
        return stringBuilder.toString()
    }
}