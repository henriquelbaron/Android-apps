package br.com.henrique.model;

public class Pessoa {

    private String nome;
    private Integer idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        try {
            this.idade = Integer.parseInt(idade);
        } catch (NumberFormatException e) {
            this.idade = null;

        }
    }
}
