package com.example.tiagoiwamoto.appclient;

/**
 * Created by Tiago Iwamoto on 01/04/2018.
 */

public class MeuLivro {

    public String codigoLivro;
    private String userCodigo;
    public String nome;
    public String categoria;
    public String descricao;

    public String getCodigoLivro() {
        return codigoLivro;
    }

    public void setCodigoLivro(String codigoLivro) {
        this.codigoLivro = codigoLivro;
    }

    public String getUserCodigo() {
        return userCodigo;
    }

    public void setUserCodigo(String userCodigo) {
        this.userCodigo = userCodigo;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MeuLivro{");
        sb.append("codigoLivro='").append(codigoLivro).append('\'');
        sb.append(", userCodigo='").append(userCodigo).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", categoria='").append(categoria).append('\'');
        sb.append(", descricao='").append(descricao).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
