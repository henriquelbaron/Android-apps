package br.com.henrique.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.henrique.R;
import br.com.henrique.control.MainControl;
import br.com.henrique.model.Pessoa;
import br.com.henrique.model.PessoaBO;

public class MainActivity extends AppCompatActivity {

    private MainControl control;
    private EditText editNome, editIdade;
    private TextView tvResultado;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        control= new MainControl(this);
        editIdade = findViewById(R.id.idade);
        editNome = findViewById(R.id.nome);
        tvResultado = findViewById(R.id.tvResultado);
        imageView = findViewById(R.id.imageView);
    }

    public void fecharAction(View view) {
        control.fecharAction(view);
    }
    public EditText getEditNome() {
        return editNome;
    }
    public void setEditNome(EditText editNome) {
        this.editNome = editNome;
    }
    public EditText getEditIdade() {
        return editIdade;
    }
    public void setEditIdade(EditText editIdade) {
        this.editIdade = editIdade;
    }
    public TextView getTvResultado() {
        return tvResultado;
    }
    public void setTvResultado(TextView tvResultado) {
        this.tvResultado = tvResultado;
    }
    public ImageView getImageView() {
        return imageView;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void limparAction(View view) {
        control.limparAction(view);
    }

    public void verificarAction(View view) {
        control.verificarAction(view);
    }
}
