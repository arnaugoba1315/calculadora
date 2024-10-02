package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var switchDegrees: Switch
    private var operand1 = 0.0
    private var operand2 = 0.0
    private var operation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Asociar vistas con el código
        tvResult = findViewById(R.id.tvResult)
        switchDegrees = findViewById(R.id.switchDegrees)

        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)
        val btnDivide: Button = findViewById(R.id.btnDivide)
        val btnSin: Button = findViewById(R.id.btnSin)
        val btnCos: Button = findViewById(R.id.btnCos)
        val btnTan: Button = findViewById(R.id.btnTan)
        val btnClear: Button = findViewById(R.id.btnClear)

        // Configuración de los botones de números
        btn7.setOnClickListener { appendNumber(7) }
        btn8.setOnClickListener { appendNumber(8) }
        btn9.setOnClickListener { appendNumber(9) }

        // Botones de operaciones aritméticas
        btnDivide.setOnClickListener { setOperation("/") }

        // Botones de funciones trigonométricas
        btnSin.setOnClickListener { calculateTrigFunction("sin") }
        btnCos.setOnClickListener { calculateTrigFunction("cos") }
        btnTan.setOnClickListener { calculateTrigFunction("tan") }

        // Botón para limpiar
        btnClear.setOnClickListener { clear() }
    }

    // Método para añadir números a la operación
    private fun appendNumber(number: Int) {
        if (tvResult.text.toString() == "0") {
            tvResult.text = number.toString()
        } else {
            tvResult.text = tvResult.text.toString() + number
        }
    }

    // Método para definir la operación
    private fun setOperation(op: String) {
        operand1 = tvResult.text.toString().toDoubleOrNull() ?: 0.0
        operation = op
        tvResult.text = "" // Limpiar la pantalla para ingresar el segundo operando
    }

    // Método para calcular las funciones trigonométricas
    private fun calculateTrigFunction(trigFunction: String) {
        var value = tvResult.text.toString().toDoubleOrNull() ?: 0.0

        // Verifica si se usan grados o radianes
        if (switchDegrees.isChecked) {
            value = Math.toRadians(value) // Convertir a radianes si está en grados
        }

        val result = when (trigFunction) {
            "sin" -> sin(value)
            "cos" -> cos(value)
            "tan" -> tan(value)
            else -> 0.0
        }

        tvResult.text = result.toString()
    }

    // Método para limpiar la pantalla
    private fun clear() {
        tvResult.text = "0"
        operand1 = 0.0
        operand2 = 0.0
        operation = ""
    }
}
