package br.com.exemplolistview.model.bo;

public class EstadoBO {

    public static boolean validarCep(String cep){
        return cep != null && !cep.isEmpty();
    }
}
