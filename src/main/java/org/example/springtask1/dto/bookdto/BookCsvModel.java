package org.example.springtask1.dto.bookdto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookCsvModel {

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("title")
    private String title;

    @JsonProperty("bookId")
    private String bookId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("rating")
    private String rating;

    @JsonProperty("edition")
    private String edition;

    @JsonProperty("pages")
    private String pages;

    @JsonProperty("price")
    private String price;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("publishDate")
    private String publishDate;

    @JsonProperty("firstPublishDate")
    private String firstPublishDate;

    @JsonProperty("numRatings")
    private String numRatings;

    @JsonProperty("ratingsByStars")
    private String ratingsByStars;

    @JsonProperty("likedPercent")
    private String likedPercent;

    @JsonProperty("coverImg")
    private String coverImg;

    @JsonProperty("bbeScore")
    private String bbeScore;

    @JsonProperty("bbeVotes")
    private String bbeVotes;

    @JsonProperty("author")
    private String authorList;

    @JsonProperty("genres")
    private String genreList;

    @JsonProperty("language")
    private String languageList;

    @JsonProperty("characters")
    private String characterList;

    @JsonProperty("setting")
    private String settingList;

    @JsonProperty("awards")
    private String awardList;

    @JsonProperty("series")
    private String seriesList;

    @JsonProperty("bookFormat")
    private String formatList;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getFirstPublishDate() {
        return firstPublishDate;
    }

    public void setFirstPublishDate(String firstPublishDate) {
        this.firstPublishDate = firstPublishDate;
    }

    public String getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(String numRatings) {
        this.numRatings = numRatings;
    }

    public String getRatingsByStars() {
        return ratingsByStars;
    }

    public void setRatingsByStars(String ratingsByStars) {
        this.ratingsByStars = ratingsByStars;
    }

    public String getLikedPercent() {
        return likedPercent;
    }

    public void setLikedPercent(String likedPercent) {
        this.likedPercent = likedPercent;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getBbeScore() {
        return bbeScore;
    }

    public void setBbeScore(String bbeScore) {
        this.bbeScore = bbeScore;
    }

    public String getBbeVotes() {
        return bbeVotes;
    }

    public void setBbeVotes(String bbeVotes) {
        this.bbeVotes = bbeVotes;
    }

    public String getAuthorList() {
        return authorList;
    }

    public void setAuthorList(String authorList) {
        this.authorList = authorList;
    }

    public String getGenreList() {
        return genreList;
    }

    public void setGenreList(String genreList) {
        this.genreList = genreList;
    }

    public String getLanguageList() {
        return languageList;
    }

    public void setLanguageList(String languageList) {
        this.languageList = languageList;
    }

    public String getCharacterList() {
        return characterList;
    }

    public void setCharacterList(String characterList) {
        this.characterList = characterList;
    }

    public String getSettingList() {
        return settingList;
    }

    public void setSettingList(String settingList) {
        this.settingList = settingList;
    }

    public String getAwardList() {
        return awardList;
    }

    public void setAwardList(String awardList) {
        this.awardList = awardList;
    }

    public String getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(String seriesList) {
        this.seriesList = seriesList;
    }

    public String getFormatList() {
        return formatList;
    }

    public void setFormatList(String formatList) {
        this.formatList = formatList;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}