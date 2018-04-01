package com.example.tiagoiwamoto.appclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LivroFavoritoActivity extends Activity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference favoritos;
    List<MeuLivro> livroList;
    ListView listaLivro;
    LivroFavoritoAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livro_favorito);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("favoritos");
        listaLivro = (ListView) findViewById(R.id.listViewLivro);
        livroList = new ArrayList<>();
    }
    @Override
    protected void onStart() {
        super.onStart();

        livroAdapter = new LivroFavoritoAdapter(getApplicationContext(), R.layout.activity_livro_favorito_item, new ArrayList<MeuLivro>());

        ListView listView = (ListView) findViewById(R.id.listViewLivro);
        listView.setAdapter(livroAdapter);

        databaseReference.addValueEventListener(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            livroList.clear();


            for(DataSnapshot x : dataSnapshot.getChildren()){
                Log.d("XXX: ", x.getKey().toString());
                MeuLivro livro = new MeuLivro();
                livro.setNome(x.getValue().toString());
                livro = x.getValue(MeuLivro.class);
                livroList.add(livro);
            }

            livroAdapter.setDados(livroList);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
