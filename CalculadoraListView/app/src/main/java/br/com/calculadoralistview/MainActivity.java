package br.com.calculadoralistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText numeroUm, numeroDois;
    private ListView lvResultados;
    private ArrayAdapter<String> adapterResultados;
    private List<String> resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvResultados = findViewById(R.id.list);
        numeroDois = findViewById(R.id.numeroDois);
        numeroUm = findViewById(R.id.numeroUm);
        resultados = new ArrayList<>();
        adapterResultados = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                resultados);
        lvResultados.setAdapter(adapterResultados);
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
        builder.append(" " + operacao + " ");
        builder.append(numero2.toString());
        builder.append(" = " + resultado.toString());
        adapterResultados.add(builder.toString());
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
