package org.example.springtask1.service.manager;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.springtask1.dto.*;
import org.example.springtask1.dto.bookdto.BookDto;
import org.example.springtask1.service.additional.BookError;
import org.example.springtask1.service.additional.BookErrorState;
import org.example.springtask1.service.additional.BookUploadResult;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVManager {

    public static BookUploadResult getBookList(MultipartFile file) {

        CSVReader reader = null;
        try {
            reader = new CSVReader(new InputStreamReader(file.getInputStream()));
        }
        catch (IOException e) {
            return null;
        }
//        finally {
//            if (reader != null) {
//                IOUtils.closeQuietly(reader);
//            }
//        }

        List<BookDto> bookDtoList = new ArrayList<>();
        List<AuthorDto> authorDtoList = new ArrayList<>();
        List<GenreDto> genreDtoList = new ArrayList<>();
        List<LanguageDto> languageDtoList = new ArrayList<>();
        List<PublisherDto> publisherDtoList = new ArrayList<>();
        List<CharacterDto> characterDtoList = new ArrayList<>();
        List<SettingDto> settingDtoList = new ArrayList<>();
        List<AwardDto> awardDtoList = new ArrayList<>();
        List<FormatDto> formatDtoList = new ArrayList<>();
        List<SeriesDto> seriesDtoList = new ArrayList<>();

        String[] row;
        String[] headers;

        List<BookError> bookErrorsList = new ArrayList<>();

        try {
            headers = reader.readNext();

            if (headers == null) {
                return null;
            }

            while ((row = reader.readNext()) != null) {
                BookDto bookDto = new BookDto();
                String value;


                //isbn
                value = value(headers, row, "isbn");
                if (NumberUtils.isCreatable(value)) {
                    bookDto.setIsbn(Long.valueOf(value));
                }

                //title
                value = value(headers, row, "title");
                if (value != null && !value.isBlank()) {
                    bookDto.setTitle(value.trim());
                }

                //bookId
                value = value(headers, row, "bookId");
                if (value != null && !value.isBlank()) {
                    bookDto.setBookId(value.trim());
                }

                //author
                List<AuthorDto> currentAuthorDtoList = new ArrayList<>();

                value = value(headers, row, "author");
                if (value != null && !value.isBlank()) {

                    String[] authors = value.split(",");
                    for (String author : authors) {
                        AuthorDto authorDto = new AuthorDto();

                        int open = author.indexOf('(');
                        int close = author.indexOf(')');

                        if (open != -1 && close != -1 && open < close) {
                            authorDto.setName(author.substring(0, open).trim());
                            authorDto.setRole(author.substring(open + 1, close).trim());
                        }
                        else {
                            authorDto.setName(author.trim());
                        }

                        if (!authorDto.getName().isBlank()) {
                            if (!authorDtoList.contains(authorDto)) {
                                authorDtoList.add(authorDto);
                            }
                            else {
                                authorDto = authorDtoList.get(authorDtoList.indexOf(authorDto));
                            }

                            currentAuthorDtoList.add(authorDto);
                        }
                    }

                    if (!currentAuthorDtoList.isEmpty()) {
                        bookDto.setAuthorList(currentAuthorDtoList);
                    }
                }

                //genre
                List<GenreDto> currentGenreDtoList = new ArrayList<>();

                value = value(headers, row, "genres");
                if (value != null && !value.isBlank()) {

                    int open = value.indexOf('[');
                    int close = value.indexOf(']');

                    if (open != -1 && close != -1 && open < close) {
                        value = value.substring(1, value.length() - 1);
                    }
                    value = value.replace("'", "");

                    String[] genres = value.split(",");

                    for (String genre : genres) {
                        GenreDto genreDto = new GenreDto();
                        genreDto.setGenre(genre.trim());

                        if (!genreDto.getGenre().isEmpty()) {

                            if (!genreDtoList.contains(genreDto)) {
                                genreDtoList.add(genreDto);
                            }
                            else {
                                genreDto = genreDtoList.get(genreDtoList.indexOf(genreDto));
                            }

                            currentGenreDtoList.add(genreDto);
                        }
                    }
                }
                bookDto.setGenreList(currentGenreDtoList);

                //language
                List<LanguageDto> currentLanguageDtoList = new ArrayList<>();

                value = value(headers, row, "language");
                if (value != null && !value.isBlank()) {

                    String[] languages = value.split(";");
                    for (String language : languages) {
                        LanguageDto languageDto = new LanguageDto();

                        String[] languageSplitted = language.split(",");

                        languageDto.setLanguage(languageSplitted[0].trim());
                        if (languageSplitted.length != 1) {
                            languageDto.setAdditional(languageSplitted[1].trim());
                        }

                        if (!languageDto.getLanguage().isBlank()) {
                            if (!languageDtoList.contains(languageDto)) {
                                languageDtoList.add(languageDto);
                            }
                            else {
                                languageDto = languageDtoList.get(languageDtoList.indexOf(languageDto));
                            }

                            currentLanguageDtoList.add(languageDto);
                        }
                    }

                }
                bookDto.setLanguageList(currentLanguageDtoList);


                //publisher
                value = value(headers, row, "publisher");
                if (value != null && !value.isBlank()) {
                    PublisherDto publisherDto = new PublisherDto();
                    publisherDto.setName(value.trim());

                    if (!publisherDtoList.contains(publisherDto)) {
                        publisherDtoList.add(publisherDto);
                    }
                    else {
                        publisherDto = publisherDtoList.get(publisherDtoList.indexOf(publisherDto));
                    }

                    bookDto.setPublisher(publisherDto);
                }

                //characters
                List<CharacterDto> currentCharacterDtoList = new ArrayList<>();

                value = value(headers, row, "characters");
                if (value != null && !value.isBlank()) {
                    int open = value.indexOf('[');
                    int close = value.indexOf(']');

                    if (open != -1 && close != -1 && open < close) {
                        value = value.substring(1, value.length() - 1);
                    }
                    value = value.replace("'", "");

                    String[] characters = value.split(",");

                    for (String character : characters) {
                        CharacterDto characterDto = new CharacterDto();
                        characterDto.setName(character.trim());

                        if (!characterDto.getName().isEmpty()) {

                            if (!characterDtoList.contains(characterDto)) {
                                characterDtoList.add(characterDto);
                            }
                            else {
                                characterDto = characterDtoList.get(characterDtoList.indexOf(characterDto));
                            }

                            currentCharacterDtoList.add(characterDto);
                        }
                    }

                }
                bookDto.setCharacterList(currentCharacterDtoList);



                //setting
                List<SettingDto> currentSettingDtoList = new ArrayList<>();

                value = value(headers, row, "setting");
                if (value != null && !value.isBlank()) {
                    int open = value.indexOf('[');
                    int close = value.indexOf(']');

                    if (open != -1 && close != -1 && open < close) {
                        value = value.substring(1, value.length() - 1);
                    }
                    value = value.replace("'", "");

                    String[] settings = value.split(",");

                    for (String setting : settings) {
                        SettingDto settingDto = new SettingDto();
                        settingDto.setSetting(setting.trim());

                        if (!settingDto.getSetting().isEmpty()) {

                            if (!settingDtoList.contains(settingDto)) {
                                settingDtoList.add(settingDto);
                            }
                            else {
                                settingDto = settingDtoList.get(settingDtoList.indexOf(settingDto));
                            }

                            currentSettingDtoList.add(settingDto);
                        }
                    }

                }
                bookDto.setSettingList(currentSettingDtoList);


                //award
                List<AwardDto> currentAwardDtoList = new ArrayList<>();

                value = value(headers, row, "awards");
                if (value != null && !value.isBlank()) {
                    int open = value.indexOf('[');
                    int close = value.indexOf(']');

                    if (open != -1 && close != -1 && open < close) {
                        value = value.substring(1, value.length() - 1);
                    }
                    value = value.replace("'", "");
                    value = value.replace("\"", "");

                    String[] awards = value.split(",");

                    for (String award : awards) {
                        AwardDto awardDto = new AwardDto();
                        awardDto.setAward(award.trim());

                        int openInner = award.lastIndexOf('(');
                        int closeInner = award.lastIndexOf(')');

                        if (open != -1 && close != -1 && openInner < closeInner && closeInner == award.length() - 1) {
                            String yearString = award.substring(openInner + 1, closeInner).trim();
                            if (NumberUtils.isCreatable(yearString)) {
                                awardDto.setAward(award.substring(0, openInner).trim());
                                awardDto.setYear(Integer.valueOf(yearString));
                            }
                            else {
                                awardDto.setAward(award.trim());
                            }
                        }
                        else {
                            awardDto.setAward(award.trim());
                        }

                        if (!awardDto.getAward().isEmpty()) {

                            if (!awardDtoList.contains(awardDto)) {
                                awardDtoList.add(awardDto);
                            }
                            else {
                                awardDto = awardDtoList.get(awardDtoList.indexOf(awardDto));
                            }

                            currentAwardDtoList.add(awardDto);
                        }
                    }
                }
                bookDto.setAwardList(currentAwardDtoList);


                //format
                List<FormatDto> currentFormatDtoList = new ArrayList<>();

                value = value(headers, row, "bookFormat");
                if (value != null && !value.isBlank()) {

                    String[] formats = value.split(",");

                    for (String format : formats) {
                        FormatDto formatDto = new FormatDto();
                        formatDto.setFormat(format.trim());

                        if (!formatDto.getFormat().isEmpty()) {

                            if (!formatDtoList.contains(formatDto)) {
                                formatDtoList.add(formatDto);
                            }
                            else {
                                formatDto = formatDtoList.get(formatDtoList.indexOf(formatDto));
                            }

                            currentFormatDtoList.add(formatDto);
                        }
                    }
                }
                bookDto.setFormatList(currentFormatDtoList);

                //series
                List<SeriesDto> currentSeriesDtoList = new ArrayList<>();

                value = value(headers, row, "series");
                if (value != null && !value.isBlank()) {

                    String[] series = value.split(",");

                    for (String series1 : series) {
                        SeriesDto seriesDto = new SeriesDto();

                        String[] input = series1.split("#");

                        seriesDto.setSeries(input[0].trim());

                        if (input.length != 1) {
                            seriesDto.setSeriesNumber(input[1].trim());
                        }

                        if (!seriesDto.getSeries().isEmpty()) {

                            if (!seriesDtoList.contains(seriesDto)) {
                                seriesDtoList.add(seriesDto);
                            }
                            else {
                                seriesDto= seriesDtoList.get(seriesDtoList.indexOf(seriesDto));
                            }

                            currentSeriesDtoList.add(seriesDto);
                        }
                    }
                }
                bookDto.setSeriesList(currentSeriesDtoList);

//                //series
//                value = value(headers, row, "series");
//                if(value != null && !value.isBlank()) {
//                    String[] input = value.split("#");
//
//                    bookDto.setSeries(input[0].trim());
//
//                    if (input.length != 1) {
//                        bookDto.setSeriesNumber(input[1].trim());
//                    }
//                }

                //description
                value = value(headers, row, "description");
                if (value != null && !value.isBlank()) {
                    bookDto.setDescription(value.trim());
                }

                //rating
                value = value(headers, row, "rating");
                if (NumberUtils.isCreatable(value)) {
                    bookDto.setRating(Float.valueOf(value));
                }

                //numRatings
                value = value(headers, row, "numRatings");
                if (NumberUtils.isCreatable(value)) {
                    bookDto.setNumRatings(Integer.valueOf(value));
                }


                //edition
                value = value(headers, row, "edition");
                if (value != null && !value.isBlank()) {
                    bookDto.setEdition(value.trim());
                }

                //pages
                value = value(headers, row, "pages");
                if (NumberUtils.isCreatable(value)) {
                    bookDto.setPages(Integer.valueOf(value));
                }

                //price
                value = value(headers, row, "price");
                if (value != null && !value.isBlank()) {
                    bookDto.setPrice(value.trim());
                }

                //publishDate
                value = value(headers, row, "publishDate");
                if (value != null && !value.isBlank()) {
                    bookDto.setPublishDate(value.trim());
                }

                //firstPublishDate
                value = value(headers, row, "firstPublishDate");
                if (value != null && !value.isBlank()) {
                    bookDto.setFirstPublishDate(value.trim());
                }

                //ratingsByStars
                value = value(headers, row, "ratingsByStars");
                if (value != null && !value.isBlank()) {
                    bookDto.setRatingsByStars(value.trim());
                }

                //likedPercent
                value = value(headers, row, "likedPercent");
                if (NumberUtils.isCreatable(value)) {
                    bookDto.setLikedPercent(Integer.valueOf(value));
                }

                //coverImg
                value = value(headers, row, "coverImg");
                if (value != null && !value.isBlank()) {
                    bookDto.setCoverImg(value.trim());
                }

                //bbeScore
                value = value(headers, row, "bbeScore");
                if (NumberUtils.isCreatable(value)) {
                    bookDto.setBbeScore(Integer.valueOf(value));
                }

                //bbeVotes
                value = value(headers, row, "bbeVotes");
                if (NumberUtils.isCreatable(value)) {
                    bookDto.setBbeVotes(Integer.valueOf(value));
                }


                //Validating Dto
                BookError currentBookError = new BookError();

                if(bookDto.getIsbn() == null) {
                    currentBookError.setBookDto(bookDto);
                    currentBookError.setBookErrorState(BookErrorState.NO_OR_INCORRECT_ISBN);
                    bookErrorsList.add(currentBookError);
                    continue;
                }

                if(bookDto.getTitle() == null) {
                    currentBookError.setBookDto(bookDto);
                    currentBookError.setBookErrorState(BookErrorState.NO_TITLE);
                    bookErrorsList.add(currentBookError);
                    continue;
                }

                if(bookDto.getBookId() == null) {
                    currentBookError.setBookDto(bookDto);
                    currentBookError.setBookErrorState(BookErrorState.NO_BOOKID);
                    bookErrorsList.add(currentBookError);
                    continue;
                }

                if(bookDto.getAuthorList() == null) {
                    currentBookError.setBookDto(bookDto);
                    currentBookError.setBookErrorState(BookErrorState.NO_OR_INCORRECT_AUTHORS);
                    bookErrorsList.add(currentBookError);
                    continue;
                }

                if(bookDtoList.contains(bookDto)) {
                    currentBookError.setBookDto(bookDto);
                    currentBookError.setBookErrorState(BookErrorState.DUPLICATE_ISBN);
                    bookErrorsList.add(currentBookError);
                    continue;
                }

                bookDtoList.add(bookDto);
            }
        }
        catch (IOException | CsvValidationException e) {
            return null;
        }

        BookUploadResult result = new BookUploadResult();
        result.setBookDtoList(bookDtoList);
        result.setBookErrorList(bookErrorsList);
        return result;
    }


    private static String value(String[] header,  String[] row, String target) {
        if(!Arrays.asList(header).contains(target)) {
            return null;
        }
        else {
            return row[Arrays.asList(header).indexOf(target)];
        }
    }

}
