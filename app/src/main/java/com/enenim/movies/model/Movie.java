package com.enenim.movies.model;

/**
 * Created by enenim on 4/29/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;
import com.orm.util.NamingHelper;

import java.util.ArrayList;
import java.util.List;

@Table
public class Movie implements Parcelable{
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

    public Movie(){

    }

    //used when un-parceling our parcel (creating the object)
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>(){

        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getPosterPath());
        dest.writeValue(isAdult());
        dest.writeString(getOverview());
        dest.writeString(getReleaseDate());
        dest.writeList(getGenreIds());
        dest.writeLong(getMovieId());
        dest.writeString(getOriginalTitle());
        dest.writeString(getOriginalLanguage());
        dest.writeString(getTitle());
        dest.writeString(getBackdropPath());
        dest.writeDouble(getPopularity());
        dest.writeInt(getVoteCount());
        dest.writeValue(getVideo());
        dest.writeDouble(getVoteAverage());
    }

    //constructor used for parcel
    Movie(Parcel parcel){
        setPosterPath(parcel.readString());
        setAdult((Boolean) parcel.readValue(null));
        setOverview(parcel.readString());
        setReleaseDate(parcel.readString());
        setGenreIds(parcel.readArrayList(null));
        setMovieId(parcel.readLong());
        setOriginalTitle(parcel.readString());
        setOriginalLanguage(parcel.readString());
        setTitle(parcel.readString());
        setBackdropPath(parcel.readString());
        setPopularity(parcel.readDouble());
        setVoteCount(parcel.readInt());
        setVideo((Boolean)parcel.readValue(null));
        setVoteAverage(parcel.readDouble());
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

    public void setMovieId(Long movieId){
        this.movieId = movieId;
    }

    public void setId(Long id) {
        this.id = id;
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
}