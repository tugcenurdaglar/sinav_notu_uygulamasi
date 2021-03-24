package com.tugcenurdaglar.notlaruygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;

    private ArrayList<Notlar> notlarArrayList;
    private NotlarAdapter adapter;

    private NotlarInterface notlarDIF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);


        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));


        notlarDIF = ApiUtils.getNotlarInterface();


        notlarDIF.tumNotlar().enqueue(new Callback<NotlarCevap>() {
            @Override
            public void onResponse(Call<NotlarCevap> call, Response<NotlarCevap> response) {

                double toplam= 0;

                List<Notlar> liste = response.body().getNotlar();

                for (Notlar n:liste){
                    toplam = toplam+(Integer.parseInt(n.getNot1())+ Integer.parseInt(n.getNot2()))/2;
                }

                adapter = new NotlarAdapter(MainActivity.this, liste);

                rv.setAdapter(adapter);

                toolbar.setSubtitle("Ortalama: "+toplam/liste.size());
            }

            @Override
            public void onFailure(Call<NotlarCevap> call, Throwable t) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotKayitActivity.class));

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}