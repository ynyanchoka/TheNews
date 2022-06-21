package com.monari.thenews.network;

import com.monari.thenews.models.NewsSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines")
    Call<NewsSearchResponse> getNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String api_key
    );
    @GET("everything")
    Call<NewsSearchResponse> getEveryNews(
            @Query("q") String query,
            @Query("apiKey") String api_key
    );
}
