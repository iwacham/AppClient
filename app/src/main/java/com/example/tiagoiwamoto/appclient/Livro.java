package com.example.tiagoiwamoto.appclient;

/**
 * Created by Tiago Iwamoto on 01/04/2018.
 */

public class Livro {

    public String nome;
    public String categoria;
    public String descricao;
    public String key;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Livro{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", categoria='").append(categoria).append('\'');
        sb.append(", descricao='").append(descricao).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
