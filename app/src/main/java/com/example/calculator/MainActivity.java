package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   TextView resultTv, solutionTv;
   AppCompatButton buttonC, buttonBackOpen, buttonBackClose;
   AppCompatButton buttonAC, buttonDot;
   AppCompatButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
   AppCompatButton buttonEqual, buttonDivide, buttonMultiply, buttonPlus, buttonMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_textview);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC, R.id.btn_C);
        assignId(buttonBackOpen, R.id.btn_open_bracket);
        assignId(buttonBackClose, R.id.btn_close_bracket);
        assignId(buttonAC, R.id.btn_AC);
        assignId(buttonDot, R.id.btn_dot);
        assignId(button0, R.id.btn_0);
        assignId(button1, R.id.btn_1);
        assignId(button2, R.id.btn_2);
        assignId(button3, R.id.btn_3);
        assignId(button4, R.id.btn_4);
        assignId(button5, R.id.btn_5);
        assignId(button6, R.id.btn_6);
        assignId(button7, R.id.btn_7);
        assignId(button8, R.id.btn_8);
        assignId(button9, R.id.btn_9);
        assignId(buttonEqual, R.id.btn_equal);
        assignId(buttonPlus, R.id.btn_add);
        assignId(buttonMinus, R.id.btn_sub);
        assignId(buttonMultiply, R.id.btn_mul);
        assignId(buttonDivide, R.id.btn_div);
    }

    void assignId(AppCompatButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        AppCompatButton button = (AppCompatButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if (buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);

        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }


        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "javascript",1, null).toString();

            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }

            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}