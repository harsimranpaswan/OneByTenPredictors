package com.workshop.onebytenpredictors;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    String BASE_URL="https://newsapi.org/v2/";

    @GET ("top-headlines")
    Call<NewsArrayClass> getNews(
        @Query("country") String country,
        @Query("pageSize") String pageSize,
        @Query("apiKey") String apiKey
    );
    @GET ("top-headlines")
    Call<NewsArrayClass> getCategoryNews(
        @Query("country") String country,
        @Query("category") String category,
        @Query("pageSize") String pageSize,
        @Query("apiKey") String apiKey
    );
}