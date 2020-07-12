package br.com.exemplolistview.model.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Collection;

@DatabaseTable(tableName = "pais")
public class Pais implements Serializable {

    @DatabaseField(allowGeneratedIdInsert = true, generatedId = true)
    private Integer id;
    @DatabaseField(canBeNull = false, unique = true, width = 100)
    private String nome;

    @ForeignCollectionField(eager = true)
    private Collection<Estado> estados;

    public Pais() {
    }

    public Pais(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Collection<Estado> getEstados() {
        return estados;
    }

    public void setEstados(Collection<Estado> estados) {
        this.estados = estados;
    }

    public Pais(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nome;
    }

}
