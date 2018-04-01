package com.example.tiagoiwamoto.appclient;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

/**
 * Created by Tiago Iwamoto on 01/04/2018.
 */

public class LivroFavoritoAdapter extends ArrayAdapter<MeuLivro>{

    Context context;
    int layoutResourceId;
    List<MeuLivro> dados;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    String valorChave;

    public LivroFavoritoAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<MeuLivro> dados) {
        super(context, resource, dados);

        this.context = context;
        this.layoutResourceId = resource;
        this.dados = dados;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        firebaseStorage = FirebaseStorage.getInstance();
        View view = convertView;
        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(layoutResourceId, parent, false);
        }

        TextView nome = (TextView) view.findViewById(R.id.nomeLivroFavorito);
        TextView categoria = (TextView) view.findViewById(R.id.labelCategoriaFavorito);
        TextView descricao = (TextView) view.findViewById(R.id.descricaoFavorito);

        MeuLivro livro = dados.get(position);

        nome.setText(livro.getNome());
        categoria.setText(livro.getCategoria());
        descricao.setText(livro.getDescricao());
        return view;
    }

    public void setDados(List<MeuLivro> dados){
        this.clear();
        this.addAll(dados);
        this.dados = dados;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayoutResourceId() {
        return layoutResourceId;
    }

    public void setLayoutResourceId(int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }

    public List<MeuLivro> getDados() {
        return dados;
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public void setFirebaseDatabase(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    public FirebaseStorage getFirebaseStorage() {
        return firebaseStorage;
    }

    public void setFirebaseStorage(FirebaseStorage firebaseStorage) {
        this.firebaseStorage = firebaseStorage;
    }

    public String getValorChave() {
        return valorChave;
    }

    public void setValorChave(String valorChave) {
        this.valorChave = valorChave;
    }
}
