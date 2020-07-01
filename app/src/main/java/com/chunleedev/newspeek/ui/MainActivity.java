package com.chunleedev.newspeek.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chunleedev.newspeek.R;
import com.chunleedev.newspeek.databinding.ActivityMainBinding;
import com.chunleedev.newspeek.model.ArticleModel;
import com.chunleedev.newspeek.parceables.Article;
import com.chunleedev.newspeek.parceables.Config;
import com.chunleedev.newspeek.parceables.Source;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String API_KEY = "f1fe2915d7cf40849ccb25aa689b2b72";
    private ItemAdapter adapter;
    private ArrayList<Article> articleList = new ArrayList<>();
    private ArrayList<Source> sourceList;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View mainView = mainBinding.getRoot();
        setContentView(mainView);

        final Toolbar toolbar = mainBinding.toolbar;
        setSupportActionBar(toolbar);

        recyclerView = mainBinding.newsRcv;
        final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        fetchJson(getCountry(), API_KEY);
    }

    private void fetchJson(String country, String api_key) {

        Call<Config> configCall = Client
                .getInstance()
                .getApi()
                .getConfig(country, api_key);

        configCall.enqueue(new Callback<Config>() {
            @Override
            public void onResponse(Call<Config> call, Response<Config> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    articleList.clear();
                    articleList = response.body().getArticles();
                    adapter = new ItemAdapter(MainActivity.this, articleList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Config> call, Throwable t) {
                new AlertDialog.Builder(MainActivity.this).setMessage("Fuu  " + t).create().show();
            }
        });
    }

    private String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //  if (item.getGroupId() == R.id.reload_item){        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        //adapter.notifyDataSetChanged();
        super.onResume();
    }
}