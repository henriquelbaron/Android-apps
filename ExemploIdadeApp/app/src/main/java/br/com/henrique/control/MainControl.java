package br.com.henrique.control;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import br.com.henrique.R;
import br.com.henrique.model.Pessoa;
import br.com.henrique.model.PessoaBO;
import br.com.henrique.view.MainActivity;
import br.com.henrique.view.SobreActivity;

public class MainControl {

    private MainActivity activity;

    public MainControl(MainActivity activity) {
        this.activity = activity;
    }

    public void verificarAction(View view) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(activity.getEditNome().getText().toString());
        pessoa.setIdade(activity.getEditIdade().getText().toString());

        TextView tvRedDinamico = new TextView(activity);

        if (PessoaBO.validarNome(pessoa.getNome())) {
            Toast.makeText(activity, R.string.erro_nome, Toast.LENGTH_SHORT).show();
            activity.getEditNome().setError(activity.getString(R.string.erro_nome));
        } else if (PessoaBO.validarIdade(pessoa.getIdade())) {
            Toast.makeText(activity, R.string.erro_idade, Toast.LENGTH_SHORT).show();
            activity.getEditIdade().setError(activity.getString(R.string.erro_idade));
        } else {
            StringBuilder msg = new StringBuilder();
            msg.append("Olá ").append(pessoa.getNome()).append(", você possui ").append(pessoa.getIdade()).append(" ano(s)");
            activity.getTvResultado().setText(msg.toString());
            int imag = PessoaBO.verificarSeEMaiorDeIdade(pessoa.getIdade()) ? R.drawable.cnh_bean : R.drawable.menor18;
            activity.getImageView().setImageResource(imag);
        }
        retirarFocus();
    }

    public void ir(View v){
        Intent it = new Intent(activity, SobreActivity.class);
        activity.startActivity(it);
    }

    public void retirarFocus() {
        InputMethodManager mImMan = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        mImMan.hideSoftInputFromWindow(activity.getEditNome().getWindowToken(), 0);
        mImMan.hideSoftInputFromWindow(activity.getEditIdade().getWindowToken(), 0);
    }

    public void fecharAction(View view) {
        activity.finish();
    }

    public void limparAction(View view) {
        activity.getEditNome().setText("");
        activity.getEditIdade().setText("");
        activity.getImageView().setImageResource(0);
        retirarFocus();
    }
}
