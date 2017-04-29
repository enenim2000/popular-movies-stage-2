package com.enenim.movies;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.enenim.movies.config.Config;
import com.enenim.movies.model.Movie;
import com.enenim.movies.model.MoviesResponse;
import com.enenim.movies.model.Review;
import com.enenim.movies.model.ReviewResponse;
import com.enenim.movies.rest.ApiClient;
import com.enenim.movies.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewActivity extends AppCompatActivity {
    private Intent intent;
    private Movie movie;
    private List<Review> reviews;
    private int maxPage;
    private static final String TAG = ReviewActivity.class.getSimpleName();

    @BindView(R.id.recyclerview_review)
    RecyclerView recyclerView;

    @BindView(R.id.pb_loading_indicator)
    ProgressBar mLoadingIndicator;

    @BindView(R.id.tv_review)
    TextView textViewReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);

       intent = getIntent();

        if (intent != null){
            if (intent.hasExtra(Config.MOVIE_KEY)){
                movie = intent.getParcelableExtra(Config.MOVIE_KEY);
                //Toast.makeText(this, movie.getOverview(), Toast.LENGTH_LONG).show();
            }
        }

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ReviewResponse> call;

        call = apiService.getReviews(movie.getMovieId(), Config.API_KEY);

        mLoadingIndicator.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse>call, Response<ReviewResponse> response) {
                //Call to refresh menu in order to update menuInfo in onPrepareOptionsMenu(Menu menu)

                reviews = response.body().getResults();
                maxPage = response.body().getTotalPages();

                mLoadingIndicator.setVisibility(View.INVISIBLE);

                if (reviews != null) {

                    /*for (Review r : reviews){
                        textViewReview.append(r.getContent() + "\n\n\n");
                    }*/

                    initViews(reviews);
                    ReviewAdapter adapter = new ReviewAdapter(getApplicationContext(), reviews);
                    recyclerView.setAdapter(adapter);

                    //showMovieDataView();
                    //mMovieAdapter.setMovies(movies);

                    //mRecyclerView.setAdapter(mMovieAdapter);
                } else {
                    //showErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private void initViews(List<Review> reviewList){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_review);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        reviews = reviewList;

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Review review = reviews.get(position);

                //Intent intent = new Intent(ReviewActivity.this, ReviewDetails.class);
                //Bundle bundle = new Bundle();

                //bundle.putSerializable(Common.USER_KEY, user);
                //intent.putExtras(bundle);

                //startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }

}
