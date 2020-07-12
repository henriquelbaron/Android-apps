package br.com.calculadoracomhistrico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout resultados;
    private EditText numeroUm, numeroDois;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultados = findViewById(R.id.resultadosLayout);
        numeroDois = findViewById(R.id.numeroDois);
        numeroUm = findViewById(R.id.numeroUm);
    }

    public void calcular(String operacao) {
        Double numero1, numero2;
        try {
            numero1 = Double.valueOf(numeroUm.getText().toString());
            numero2 = Double.valueOf(numeroDois.getText().toString());
        } catch (NumberFormatException e) {
            return;
        }
        Double resultado = 0.0;
        switch (operacao) {
            case "+":
                resultado = numero1 + numero2;
                break;
            case "-":
                resultado = numero1 - numero2;
                break;
            case "/":
                resultado = numero1 / numero2;
                break;
            case "*":
                resultado = numero1 * numero2;
                break;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(numero1.toString());
        builder.append(" "+operacao+" ");
        builder.append(numero2.toString());
        builder.append(" = "+ resultado.toString());
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(builder.toString());
        resultados.addView(textView);
        numeroUm.setText("");
        numeroDois.setText("");
    }

    public void somarAction(View view) {
        calcular("+");
    }

    public void subtracaoAction(View view) {
        calcular("-");
    }

    public void multiplicacaoAction(View view) {
        calcular("*");
    }

    public void divisaoAction(View view) {
        calcular("/");
    }
}
