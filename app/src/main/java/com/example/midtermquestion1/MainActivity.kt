package com.example.midtermquestion1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val number1: EditText = findViewById(R.id.number1)
        val number2: EditText = findViewById(R.id.number2)
        val result: TextView = findViewById(R.id.result)

        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            performOperation(number1, number2, result, "ADD")
        }

        findViewById<Button>(R.id.buttonSubtract).setOnClickListener {
            performOperation(number1, number2, result, "SUBTRACT")
        }

        findViewById<Button>(R.id.buttonMultiply).setOnClickListener {
            performOperation(number1, number2, result, "MULTIPLY")
        }

        findViewById<Button>(R.id.buttonDivide).setOnClickListener {
            performOperation(number1, number2, result, "DIVIDE")
        }

        val intentData = intent.getStringExtra("extra_data")
        if (intentData != null) {
            result.text = "Received: $intentData"
        }


    }

    private fun performOperation(number1: EditText, number2: EditText, result: TextView, operation: String) {
        val num1 = number1.text.toString().toDoubleOrNull()
        val num2 = number2.text.toString().toDoubleOrNull()

        if (num1 != null && num2 != null) {
            val res = when (operation) {
                "ADD" -> num1 + num2
                "SUBTRACT" -> num1 - num2
                "MULTIPLY" -> num1 * num2
                "DIVIDE" -> if (num2 != 0.0) num1 / num2 else "Cannot divide by zero"
                else -> "Invalid operation"
            }
            result.text = res.toString()
        } else {
            result.text = "Invalid input"
        }
    }


}

