package mhha.sample.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import mhha.sample.calculator.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val firstNumberText = StringBuilder("")
    private val secondNumberText = StringBuilder("")
    private val operatorText = StringBuilder("")
    private val decimalFormat = DecimalFormat("#,###")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }//onCreate


    fun numberClicked(view : View){
        var numberString = (view as? Button)?.text?.toString() ?: ""
        var numberText = if(operatorText.isEmpty()) firstNumberText else secondNumberText

        numberText.append(numberString)
        updateEquationTextView()

    }//numberClick
    fun clearClicked(view : View){
        firstNumberText.clear()
        secondNumberText.clear()
        operatorText.clear()
        updateEquationTextView()
    }//clearClicked
    fun equalClicked(view : View){
        if(firstNumberText.isEmpty() || secondNumberText.isEmpty() || operatorText.isEmpty()){
            Toast.makeText(this, "올바르지 않은 수식 입니다.",Toast.LENGTH_SHORT).show()
            return
        }

//        var firstNum = firstNumberText.toString().toInt()
//        var secondNum = secondNumberText.toString().toInt()
        var firstNum = firstNumberText.toString().toBigDecimal()
        var secondNum = secondNumberText.toString().toBigDecimal()

        var result = when(operatorText.toString()){
            "+" -> decimalFormat.format(firstNum + secondNum)
            "-" -> decimalFormat.format(firstNum - secondNum)
            else -> "잘못된 수식 입니다."
        }.toString()

        binding.resultTextView.text=result


    }//equalClicked
    fun operatorclicked(view : View){
        var operratorString = (view as? Button)?.text?.toString() ?: ""

        if(firstNumberText.isEmpty()){
            Toast.makeText(this, "숫자를 먼저 입력해주세요.",Toast.LENGTH_SHORT).show()
            return
        } //if(firstNumberText.isEmpty())

        if(secondNumberText.isNotEmpty()){
            Toast.makeText(this, "1개의 연산자에 대해서만 연산이 가능합니다.",Toast.LENGTH_SHORT).show()
            return
        }//if(secondNumberText.isNotEmpty())

        operatorText.append(operratorString)
        updateEquationTextView()

    }//operatorclicked

    private fun updateEquationTextView(){
        val firstFormat = if(firstNumberText.isNotEmpty())decimalFormat.format(firstNumberText.toString().toBigDecimal()) else ""
        val seconFormat = if(secondNumberText.isNotEmpty())decimalFormat.format(secondNumberText.toString().toBigDecimal()) else ""

        binding.equationTextView.text = "$firstFormat $operatorText $seconFormat"
        binding.resultTextView.text = ""
    }//updateEquationTextView

}//MainActivity