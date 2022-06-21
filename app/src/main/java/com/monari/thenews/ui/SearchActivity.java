package com.monari.thenews.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.monari.thenews.BuildConfig;
import com.monari.thenews.MainActivity;
import com.monari.thenews.R;
import com.monari.thenews.adapters.NewsListAdapter;
import com.monari.thenews.models.Article;
import com.monari.thenews.models.NewsSearchResponse;
import com.monari.thenews.network.NewsApi;
import com.monari.thenews.network.NewsClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = SearchActivity.class.getSimpleName(); // returns the simple name of the underlying class as given in the source code.
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.searchMovies)
    SearchView mSearchView;
    @BindView(R.id.searchMoviesButton)
    Button mSearchNewssButton;

    NewsApi newsApi;

    public List<Article> articles;
    private Article mArticles;
    private NewsSearchResponse details;
    private NewsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        return true;
                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext(),SavedActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        newsApi = NewsClient.getClient();
        mSearchNewssButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrofit2.Call<NewsSearchResponse> call = newsApi.getEveryNews(mSearchView.getQuery().toString(), BuildConfig.NEWS_API_KEY);
                call.enqueue(new retrofit2.Callback<NewsSearchResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<NewsSearchResponse> call, retrofit2.Response<NewsSearchResponse> response) {

                        hideProgressBar();

                        articles = response.body().getArticles();
                        mAdapter = new NewsListAdapter(SearchActivity.this, articles);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        showMovies();

                    }


                    @Override
                    public void onFailure(Call<NewsSearchResponse> call, Throwable t) {

                        Log.i(TAG, "onFailure: show something ",t );
                        t.printStackTrace();
                        hideProgressBar();
                        showFailureMessage();
                        showUnsuccessfulMessage();

                    }
                });

            }
        });


    }


    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showMovies() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }


}