package br.com.henrique.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import br.com.henrique.R;
import br.com.henrique.model.Produto;
import br.com.henrique.model.ProdutoBO;

public class MainActivity extends AppCompatActivity {

    private TextView textNome, textValorInicial, textValorTotal, textValorParcelas, textJuros;
    private CheckBox checkBox;
    private EditText inputNome, inputValor, inputParcelas, inputJuros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize() {
        checkBox = findViewById(R.id.checkbox);
        textNome = findViewById(R.id.textNome);
        textJuros = findViewById(R.id.textJuros);
        inputNome = findViewById(R.id.inputNome);
        inputValor = findViewById(R.id.inputValor);
        inputJuros = findViewById(R.id.inputJuros);
        inputParcelas = findViewById(R.id.inputParcelas);
        textValorTotal = findViewById(R.id.textValorTotal);
        textValorInicial = findViewById(R.id.textValorInicial);
        textValorParcelas = findViewById(R.id.textValorParcelas);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ProdutoBO.isValidNumber(inputJuros.getText().toString())) {
                    Double juros = Double.valueOf(inputJuros.getText().toString());
                    inputJuros.setText(ProdutoBO.verifyDiscount(juros,isChecked));
                }
            }
        });
        LinearLayout contentTipo = findViewById(R.id.layout);
        contentTipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard();
            }
        });
        inputValor.addTextChangedListener(new MoneyTextWatcher(inputValor));
    }

    public void calcularAction(View v) {
        Produto produto = new Produto();
        String nome = inputNome.getText().toString();
        BigDecimal valorInicial = ProdutoBO.convertStringToBigDecimal(inputValor.getText().toString());
       if (!ProdutoBO.isValidString(nome)) {
            Toast.makeText(this, "Nome Inválido", Toast.LENGTH_SHORT).show();
            inputNome.setError("Nome Inválido");
        } else if (valorInicial == null) {
            Toast.makeText(this, "Valor Inválido", Toast.LENGTH_SHORT).show();
            inputValor.setError("Valor Inválido");
        } else {
            produto.setNome(inputNome.getText().toString());
            produto.setQuantidadeParcelas(Integer.valueOf(inputParcelas.getText().toString()));
            produto.setValor(valorInicial.doubleValue());
            produto.setEntrada(checkBox.isSelected());
            produto.setJuros(Double.valueOf(inputJuros.getText().toString()));
            ProdutoBO bo = ProdutoBO.calcular(produto);
            textNome.setText("Produto: " + nome);
            textJuros.setText("Total de Juros: R$" + bo.getValorJuros());
            textValorInicial.setText("Valor Inicial: R$" + ProdutoBO.formatTextPrice(valorInicial.toString()));
//            textValorInicial.setText("Valor Inicial: R$" + String.format("%.2f",valorInicial.doubleValue()));
            textValorTotal.setText("Valor Total: R$" + bo.getValorTotal());
            textValorParcelas.setText("Valor Parcelas: R$" + bo.getValorParcela());
        }
    }

    public void limparAction(View v) {
        textNome.setText("");
        textJuros.setText("");
        inputNome.setText("");
        inputValor.setText("");
        inputJuros.setText("");
        inputParcelas.setText("");
        checkBox.setChecked(false);
        textValorTotal.setText("");
        textValorInicial.setText("");
        textValorParcelas.setText("");
    }

    private void hideSoftKeyboard() {
        InputMethodManager mImMan = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mImMan.hideSoftInputFromWindow(inputParcelas.getWindowToken(),  InputMethodManager.HIDE_NOT_ALWAYS);
        mImMan.hideSoftInputFromWindow(inputJuros.getWindowToken(),  InputMethodManager.HIDE_NOT_ALWAYS);
        mImMan.hideSoftInputFromWindow(inputValor.getWindowToken(),  InputMethodManager.HIDE_NOT_ALWAYS);
        mImMan.hideSoftInputFromWindow(inputNome.getWindowToken(),  InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
