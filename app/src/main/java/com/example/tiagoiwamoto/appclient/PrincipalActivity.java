package com.example.tiagoiwamoto.appclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PrincipalActivity extends Activity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference favoritos;
    List<Livro> livroList;
    ListView listaLivro;
    LivroAdapter livroAdapter;
    TextView valorChave;
    String codigoLivro;
    Button btnFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("livro");
        listaLivro = (ListView) findViewById(R.id.listLivro);
        livroList = new ArrayList<>();
        listaLivro.setOnItemLongClickListener(longClickListener);

        btnFavoritos = (Button) findViewById(R.id.btnFavoritos);
        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrincipalActivity.this, LivroFavoritoActivity.class);
                startActivity(intent);
            }
        });

    }

    private AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.d("Chave: ", livroAdapter.getDados().get(i).getKey());
            databaseReference = firebaseDatabase.getReference("livro/"+livroAdapter.getDados().get(i).getKey());
            codigoLivro = livroAdapter.getDados().get(i).getKey();

            databaseReference.addListenerForSingleValueEvent(valueEventListenerFavoritos);


            return true;
        }
    };

    ValueEventListener valueEventListenerFavoritos = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String id = UUID.randomUUID().toString();
            Livro livro = dataSnapshot.getValue(Livro.class);
            MeuLivro meuLivro = new MeuLivro();
            meuLivro.setCodigoLivro(codigoLivro);
            meuLivro.setNome(livro.getNome());
            meuLivro.setCategoria(livro.getCategoria());
            meuLivro.setDescricao(livro.getDescricao());
            meuLivro.setUserCodigo(id);
            favoritos = firebaseDatabase.getReference("favoritos");
            favoritos.child(id).setValue(livro);
            Toast.makeText(PrincipalActivity.this, "Livro favoritado com sucesso!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };



    @Override
    protected void onStart() {
        super.onStart();

        livroAdapter = new LivroAdapter(getApplicationContext(), R.layout.activity_livro_item, new ArrayList<Livro>());

        ListView listView = (ListView) findViewById(R.id.listLivro);
        listView.setAdapter(livroAdapter);

        databaseReference.addValueEventListener(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            livroList.clear();


            for(DataSnapshot x : dataSnapshot.getChildren()){
                Log.d("XXX: ", x.getKey().toString());
                Livro livro = new Livro();
                livro.setNome(x.getValue().toString());
                livro = x.getValue(Livro.class);
                livro.setKey(x.getKey().toString());
                livroList.add(livro);
            }

            livroAdapter.setDados(livroList);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
