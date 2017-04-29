package com.enenim.movies.rest;

import com.enenim.movies.model.MoviesResponse;
import com.enenim.movies.model.Review;
import com.enenim.movies.model.ReviewResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
 
 
public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);
 
    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") Long id, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Call<ReviewResponse> getReviews(@Path("id") Long id, @Query("api_key") String apiKey);
}