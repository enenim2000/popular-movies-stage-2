package com.enenim.movies.model;

import android.graphics.Movie;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;
        import com.orm.SugarRecord;
        import com.orm.dsl.Table;
        import com.orm.dsl.Unique;
        import com.orm.util.NamingHelper;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

@Table
public class Movie2 implements Serializable {
    /**
     * Add transient to exclude database id from serialization,
     * This prevent conflicts arising from Gson and Retrofit
     */
    @SerializedName("db_id")
    private transient Long id = null;

    @SerializedName("id")
    @Expose
    @Unique
    private Long movieId;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("adult")
    @Expose
    private boolean adult;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = new ArrayList<Integer>();

    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("popularity")
    @Expose
    private Double popularity;

    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;

    @SerializedName("video")
    @Expose
    private Boolean video;

    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    public Movie2(){

    }

    public Movie2(String posterPath,
                 boolean adult,
                 String overview,
                 String releaseDate,
                 List<Integer> genreIds,
                 Long movieId,
                 String originalTitle,
                 String originalLanguage,
                 String title,
                 String backdropPath,
                 Double popularity,
                 Integer voteCount,
                 Boolean video,
                 Double voteAverage) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.movieId= movieId;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
    }

    public Long getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setId(Long movieId) {
        this.movieId = movieId;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void save(){
        SugarRecord.save(this);
    }

    public void delete(){
        SugarRecord.deleteAll(Movie.class, NamingHelper.toSQLNameDefault("movieId") + " = ?", getMovieId()+"");
    }

    public void deleteAll(){
        SugarRecord.deleteAll(Movie.class);
    }

    public Movie findById(Long movieId){
        List<Movie> movies = SugarRecord.find(Movie.class, NamingHelper.toSQLNameDefault("movieId") + " = ?", movieId+"");
        int firstItem = 0;
        if(movies != null){
            return movies.get(firstItem);
        }

        return null;

        //return SugarRecord.findById(Movie.class, id);
    }

    public void findByMovieBbApiId(Long movieId){
        //List<Movie> movies = SugarRecord.find(Movie.class, NamingHelper.toSQLNameDefault("movieId")+" = ?", movieId+"");
    }

    public List<Movie> findMoviesByQuery(){
        List<Movie> movies = SugarRecord.findWithQuery(Movie.class, "Select * from Movie where " + NamingHelper.toSQLNameDefault("movieId")+ " = ?", getMovieId()+"");
        return movies;
    }

    public Boolean exist(){
        List<Movie> movie = SugarRecord.find(Movie.class, NamingHelper.toSQLNameDefault("movieId") + " = ?", this.getMovieId()+"");

        if(movie != null && movie.size() > 0) {
            return true;
        }

        return false;
    }

    public Boolean existAlternative(){
        if (SugarRecord.find(Movie.class, NamingHelper.toSQLNameDefault("movieId")
                + " = ?", this.getMovieId()+"").isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}