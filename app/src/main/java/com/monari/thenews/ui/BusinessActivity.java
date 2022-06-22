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

public class BusinessActivity extends AppCompatActivity {

    private static final String TAG = BusinessActivity.class.getSimpleName(); // returns the simple name of the underlying class as given in the source code.
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    NewsApi newsApi;

    public List<Article> articles;
    private Article mNews;
    private NewsSearchResponse details;

    private NewsListAdapter mAdapter;
    Button b1,b2,b3,b4,b5,b6,b7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        ButterKnife.bind(this);


        b1 = findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BusinessActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        b2 = findViewById(R.id.btn2);
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BusinessActivity.this, BusinessActivity.class);
                startActivity(intent);

            }
        });
        b3 = findViewById(R.id.btn3);
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BusinessActivity.this, EntertainmentActivity.class);
                startActivity(intent);

            }
        });
        b4 = findViewById(R.id.btn4);
        b4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BusinessActivity.this, HealthActivity.class);
                startActivity(intent);

            }
        });
        b5 = findViewById(R.id.btn5);
        b5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BusinessActivity.this, Science.class);
                startActivity(intent);

            }
        });
        b6 = findViewById(R.id.btn6);
        b6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BusinessActivity.this, SportsActivity.class);
                startActivity(intent);

            }
        });
        b7 = findViewById(R.id.btn7);
        b7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BusinessActivity.this, TechActivity.class);
                startActivity(intent);

            }
        });

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
                    case R.id.saved:
                        startActivity(new Intent(getApplicationContext(), SavedActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });







        newsApi = NewsClient.getClient();

        retrofit2.Call<NewsSearchResponse> call = newsApi.getNews("us","business", BuildConfig.NEWS_API_KEY);

        call.enqueue(new retrofit2.Callback<NewsSearchResponse>() {
            @Override
            public void onResponse(retrofit2.Call<NewsSearchResponse> call, retrofit2.Response<NewsSearchResponse> response) {

                if (response.isSuccessful()) {
                    hideProgressBar();

                    articles = response.body().getArticles();
                    mAdapter = new NewsListAdapter(BusinessActivity.this, articles);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(BusinessActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showNews();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<NewsSearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
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

    private void showNews() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }


}