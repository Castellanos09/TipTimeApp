package garcia.imelda.tiptime

import android.os.Bundle
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        //EXTRAE LOS DATOS POR ID EN EL ARCHIVO XML
        val tipRadioGroup = findViewById<RadioGroup>(R.id.tip_options)
        val totalAmount = findViewById<EditText>(R.id.cost_of_service)

        // SE REALIZA UN LISTENER SEGUN EL BOTON QUE ESTE SELECCIONADO
        //IDENTIFICANDO POR MEDIO DE LO ID´S
        tipRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            val tipPercentage = when (checkedId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_fifteen_percent -> 0.15
                R.id.option_eighteen_percent -> 0.18
                else -> 0.0 // VALOR POR DEFAULT
            }

            //SE CALCULA EL TOTAL DE PROPINAS MULTIPLICANDO EL TOTAL DE LA CUENTA POR EL
            //PORCENTAJE SELECCIONADO

            val tipAmount = totalAmount * tipPercentage


            //SE MUESTRA EN EL CAMPO DEL TEXT VIEW ENCONTRADO MEDIANTE ID
            //EL VALOR PREVIAMENTE CALCULADO "TIPAMOUNT"
            val tipTextView = findViewById<TextView>(R.id.tip_result)
            tipTextView.text = getString(R.string.tip_amount, tipAmount)


            setContentView(R.layout.activity_main)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}