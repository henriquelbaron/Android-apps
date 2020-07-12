package br.com.henrique.model;

public class PessoaBO {

    public static boolean validarNome(String nome) {
        return (nome == null || nome.trim().isEmpty());
    }

    public static boolean validarIdade(Integer idade) {
        return (idade == null || idade < 0);
    }

    public static boolean verificarSeEMaiorDeIdade(Integer idade) {
        return idade >= 18;
    }
}
