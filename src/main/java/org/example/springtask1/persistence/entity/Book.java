package org.example.springtask1.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
//@Setter
//@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    @SequenceGenerator(
            name = "book_id_seq",
            sequenceName = "book_id_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(name = "isbn", nullable = false, unique = true)
    private Long isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "bookid", nullable = false)
    private String bookId;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "edition")
    private String edition;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "price")
    private String price;

    @Column(name = "publishDate")
    private String publishDate;

    @Column(name = "firstPublishDate")
    private String firstPublishDate;

    @Column(name = "numRatings")
    private Integer numRatings;

    @Column(name = "ratingsByStars")
    private String ratingsByStars;

    @Column(name = "likedPercent")
    private Integer likedPercent;

    @Column(name = "coverImg")
    private String coverImg;

    @Column(name = "bbeScore")
    private Integer bbeScore;

    @Column(name = "bbeVotes")
    private Integer bbeVotes;

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<BookGenre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<BookAuthor> authors = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<BookLanguage> languages = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<BookCharacter> characters = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<BookSetting> settings = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<BookAward> awards = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<BookSeries> series = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.PERSIST)
    private List<BookFormat> formats = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cover_id")
//    private Cover cover;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
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

    public Integer getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    public String getRatingsByStars() {
        return ratingsByStars;
    }

    public void setRatingsByStars(String ratingsByStars) {
        this.ratingsByStars = ratingsByStars;
    }

    public Integer getLikedPercent() {
        return likedPercent;
    }

    public void setLikedPercent(Integer likedPercent) {
        this.likedPercent = likedPercent;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getBbeScore() {
        return bbeScore;
    }

    public void setBbeScore(Integer bbeScore) {
        this.bbeScore = bbeScore;
    }

    public Integer getBbeVotes() {
        return bbeVotes;
    }

    public void setBbeVotes(Integer bbeVotes) {
        this.bbeVotes = bbeVotes;
    }

    public List<BookGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<BookGenre> genres) {
        this.genres = genres;
    }

    public List<BookAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<BookAuthor> authors) {
        this.authors = authors;
    }

    public List<BookLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(List<BookLanguage> languages) {
        this.languages = languages;
    }

    public List<BookCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(List<BookCharacter> characters) {
        this.characters = characters;
    }

    public List<BookSetting> getSettings() {
        return settings;
    }

    public void setSettings(List<BookSetting> settings) {
        this.settings = settings;
    }

    public List<BookAward> getAwards() {
        return awards;
    }

    public void setAwards(List<BookAward> awards) {
        this.awards = awards;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public List<BookSeries> getSeries() {
        return series;
    }

    public void setSeries(List<BookSeries> series) {
        this.series = series;
    }

    public List<BookFormat> getFormats() {
        return formats;
    }

    public void setFormats(List<BookFormat> formats) {
        this.formats = formats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    public void addBookGenre(Genre genre) {
        BookGenre bookGenre = new BookGenre();
        bookGenre.setGenre(genre);
        bookGenre.setBook(this);

        genres.add(bookGenre);
        genre.getBooks().add(bookGenre);
    }

    public void addBookAuthor(Author author, String authorRole) {
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setAuthor(author);
        bookAuthor.setBook(this);
        bookAuthor.setAuthorRole(authorRole);

        authors.add(bookAuthor);
        author.getBooks().add(bookAuthor);
    }

    public void addBookAward(Award award, Integer year) {
        BookAward bookAward = new BookAward();
        bookAward.setAward(award);
        bookAward.setBook(this);
        bookAward.setYear(year);

        awards.add(bookAward);
        award.getBooks().add(bookAward);
    }


    public void addBookCharacter(Character character) {
        BookCharacter bookCharacter = new BookCharacter();
        bookCharacter.setCharacter(character);
        bookCharacter.setBook(this);

        characters.add(bookCharacter);
        character.getBooks().add(bookCharacter);
    }

    public void addBookLanguage(Language language) {
        BookLanguage bookLanguage = new BookLanguage();
        bookLanguage.setLanguage(language);
        bookLanguage.setBook(this);

        languages.add(bookLanguage);
        language.getBooks().add(bookLanguage);
    }

    public void addBookSetting(Setting setting) {
        BookSetting bookSetting = new BookSetting();
        bookSetting.setSetting(setting);
        bookSetting.setBook(this);

        settings.add(bookSetting);
        setting.getBooks().add(bookSetting);
    }

    public void addBookSeries(Series series, String seriesNumber) {
        BookSeries bookSeries = new BookSeries();
        bookSeries.setSeries(series);
        bookSeries.setSeriesNumber(seriesNumber);
        bookSeries.setBook(this);

        this.series.add(bookSeries);
        series.getBooks().add(bookSeries);
    }

    public void addBookFormat(Format format) {
        BookFormat bookFormat = new BookFormat();
        bookFormat.setFormat(format);
        bookFormat.setBook(this);

        formats.add(bookFormat);
        format.getBooks().add(bookFormat);
    }

    public void addPublisher(Publisher publisher) {

        this.publisher = publisher;
        publisher.getBooks().add(this);
    }
}
