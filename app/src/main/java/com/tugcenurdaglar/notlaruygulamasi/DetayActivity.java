package com.tugcenurdaglar.notlaruygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class DetayActivity extends AppCompatActivity {
    private Toolbar toolbar3;
    private EditText editTextDerss, editTextNott1, editTextNott2;

    private Notlar not;
    private NotlarInterface notlardif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        toolbar3 = findViewById(R.id.toolbar3);
        editTextDerss = findViewById(R.id.editTextDerss);
        editTextNott1 = findViewById(R.id.editTextNott1);
        editTextNott2 = findViewById(R.id.editTextNott2);

        toolbar3.setTitle("Not Detay");
        setSupportActionBar(toolbar3);

        notlardif =ApiUtils.getNotlarInterface();

        not = (Notlar) getIntent().getSerializableExtra("nesne");

        editTextDerss.setText(not.getDersAdi());
        editTextNott1.setText(not.getNot1());
        editTextNott2.setText(not.getNot2());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_sil:
                Snackbar.make(toolbar3,"Silinsin mi ?", Snackbar.LENGTH_SHORT).setAction(
                        "Evet", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                notlardif.notSil(Integer.parseInt(not.getNotId())).enqueue(new Callback<CRUDCevap>() {
                                    @Override
                                    public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<CRUDCevap> call, Throwable t) {

                                    }
                                });
                                startActivity(new Intent(DetayActivity.this,MainActivity.class));
                                finish();

                            }
                        }
                ).show();
                return true;

            case R.id.action_duzenle:
                String ders_adi = editTextDerss.getText().toString().trim();
                String not1 = editTextNott1.getText().toString().trim();
                String not2 = editTextNott2.getText().toString().trim();

                notlardif.notGuncelle(Integer.parseInt(not.getNotId()), ders_adi,
                        Integer.parseInt(not1), Integer.parseInt(not2)).enqueue(new Callback<CRUDCevap>() {
                    @Override
                    public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                    }

                    @Override
                    public void onFailure(Call<CRUDCevap> call, Throwable t) {

                    }
                });

                if (TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(toolbar3,"Ders adı giriniz", Snackbar.LENGTH_SHORT).show();
                    return false;
                }

                if (TextUtils.isEmpty(not1)){
                    Snackbar.make(toolbar3,"Not 1 giriniz", Snackbar.LENGTH_SHORT).show();
                    return false;
                }

                if (TextUtils.isEmpty(not2)){
                    Snackbar.make(toolbar3,"Not 2 giriniz", Snackbar.LENGTH_SHORT).show();
                    return false;
                }



                startActivity(new Intent(DetayActivity.this,MainActivity.class));
                finish();
                return true;

            default:
                return false;
        }

    }
}