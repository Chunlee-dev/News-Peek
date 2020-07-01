package com.chunleedev.newspeek.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.chunleedev.newspeek.R;
import com.chunleedev.newspeek.databinding.ActivityMainBinding;
import com.chunleedev.newspeek.model.ArticleModel;
import com.chunleedev.newspeek.parceables.Article;
import com.chunleedev.newspeek.parceables.Config;
import com.chunleedev.newspeek.parceables.Source;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private final String API_KEY = "f1fe2915d7cf40849ccb25aa689b2b72";
    private ItemAdapter adapter;
    private ArrayList<ArticleModel> articleList = new ArrayList<>();
    private ArrayList<Source> sourceList;
    private RecyclerView recyclerView;
    private RequestQueue mQueue;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View mainView = mainBinding.getRoot();
        setContentView(mainView);

        final Toolbar toolbar = mainBinding.toolbar;
        setSupportActionBar(toolbar);

        mQueue = Volley.newRequestQueue(MainActivity.this);

        recyclerView = mainBinding.newsRcv;
        recyclerView.setVisibility(View.INVISIBLE);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        //new Handler().postDelayed(() -> {
           // jsonParse(getCountry(), API_KEY);
        //}, 500);

       /* articleList.add(new ArticleModel("author", "author", "https://wallpaperset.com/w/full/5/b/f/57615.jpg", "title", "publishedAt", "articleUrl", "description"));
        articleList.add(new ArticleModel("author", "author", "https://wallpaperset.com/w/full/5/b/f/57615.jpg", "title", "publishedAt", "articleUrl", "description"));
        articleList.add(new ArticleModel("author", "author", "https://wallpaperset.com/w/full/5/b/f/57615.jpg", "title", "publishedAt", "articleUrl", "description"));
        articleList.add(new ArticleModel("author", "author", "https://tinyurl.com/yawsxg55", "title", "publishedAt", "articleUrl", "description"));
        articleList.add(new ArticleModel("author", "author", "https://tinyurl.com/yawsxg55", "title", "publishedAt", "articleUrl", "description"));
        articleList.add(new ArticleModel("author", "author", "https://tinyurl.com/yawsxg55", "title", "publishedAt", "articleUrl", "description"));
        articleList.add(new ArticleModel("author", "author", "https://tinyurl.com/yawsxg55", "title", "publishedAt", "articleUrl", "description"));
        articleList.add(new ArticleModel("author", "author", "https://tinyurl.com/yawsxg55", "title", "publishedAt", "articleUrl", "description"));
        articleList.add(new ArticleModel("author", "author", "https://tinyurl.com/yawsxg55", "title", "publishedAt", "articleUrl", "description"));


        ItemAdapter adapter = new ItemAdapter(MainActivity.this, articleList);
        recyclerView.setAdapter(adapter);*/
        recyclerView.setVisibility(View.VISIBLE);

        progressBar = mainBinding.progressBar;
        progressBar.setVisibility(View.GONE);

        jsonParse(getCountry());

        //fetchJson(getCountry(), API_KEY);
    }

    private void jsonParse(String country) {
        String url = "http://newsapi.org/v2/top-headlines?country="
                +country+
                "&apiKey=f1fe2915d7cf40849ccb25aa689b2b72";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("articles");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject article = jsonArray.getJSONObject(i);

                            String author = article.getString("author");
                            String title = article.getString("title");
                            String description = article.getString("description");
                            String articleUrl = article.getString("url");
                            String urlToImage = article.getString("urlToImage");
                            String publishedAt = article.getString("publishedAt");

                            ItemAdapter adapter = new ItemAdapter(MainActivity.this, articleList);
                            ArticleModel model = new ArticleModel(author, author, urlToImage, title, publishedAt, articleUrl, description);
                            articleList.add(model);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, Throwable::printStackTrace);
        mQueue.add(request);
    }

   /* private void fetchJson(String country, String api_key) {

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
    }*/

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