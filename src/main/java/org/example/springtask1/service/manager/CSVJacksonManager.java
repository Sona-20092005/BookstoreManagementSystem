package org.example.springtask1.service.manager;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.lang3.math.NumberUtils;
import org.example.springtask1.dto.*;
import org.example.springtask1.dto.bookdto.BookCsvModel;
import org.example.springtask1.dto.bookdto.BookDto;
import org.example.springtask1.service.additional.BookError;
import org.example.springtask1.service.additional.BookErrorState;
import org.example.springtask1.service.additional.BookUploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVJacksonManager {

    private static List<BookCsvModel> parseBooksToDataList(MultipartFile file) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        MappingIterator<BookCsvModel> mappingIterator = null;
        try {
            mappingIterator = mapper
                    .readerFor(BookCsvModel.class)
                    .with(schema)
                    .readValues(file.getInputStream());
            return mappingIterator.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BookUploadResult getBookList(MultipartFile file) {


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


        long start = System.currentTimeMillis();
        System.out.println(">>>>>>>>>>>>>>>>>>>> book csv parsing started");
        List<BookCsvModel> bookCsvModelList = parseBooksToDataList(file);
        System.out.println(">>>>>>>>>>>>>>>>>>>> book parsing ended in: %s".formatted(System.currentTimeMillis() - start) );

        List<BookError> bookErrorsList = new ArrayList<>();

        for(BookCsvModel csvModel: bookCsvModelList){
            long iteratorStart = System.currentTimeMillis();
            System.out.println(">>>>>>>>>>>>>>>>>>>> single book mapping started");

            BookDto bookDto = new BookDto();
            String value;

            //isbn
            value = csvModel.getIsbn();
            if (checkIfNumber(value)) {
                bookDto.setIsbn(Long.valueOf(value));
            }

            //title
            value = csvModel.getTitle();
            if (check(value)) {
                bookDto.setTitle(value.trim());
            }

            //bookId
            value = csvModel.getBookId();
            if (check(value)) {
                bookDto.setBookId(value.trim());
            }

            //author
            List<AuthorDto> currentAuthorDtoList = new ArrayList<>();

            value = csvModel.getAuthorList();
            if (check(value)) {

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

            value = csvModel.getGenreList();
            if (check(value)) {

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

            value = csvModel.getLanguageList();
            if (check(value)) {

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
            value = csvModel.getPublisher();
            if (check(value)) {
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

            value = csvModel.getCharacterList();
            if (check(value)) {
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

            value = csvModel.getSettingList();
            if (check(value)) {
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

            value = csvModel.getAwardList();
            if (check(value)) {
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

            value = csvModel.getFormatList();
            if (check(value)) {

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

            value = csvModel.getSeriesList();
            if (check(value)) {

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


            //description
            value = csvModel.getDescription();
            if (check(value)) {
                bookDto.setDescription(value.trim());
            }

            //rating
            value = csvModel.getRating();
            if (checkIfNumber(value)) {
                bookDto.setRating(Float.valueOf(value));
            }

            //numRatings
            value = csvModel.getNumRatings();
            if (checkIfNumber(value)) {
                bookDto.setNumRatings(Integer.valueOf(value));
            }


            //edition
            value = csvModel.getEdition();
            if (check(value)) {
                bookDto.setEdition(value.trim());
            }

            //pages
            value = csvModel.getPages();
            if (checkIfNumber(value)) {
                bookDto.setPages(Integer.valueOf(value));
            }

            //price
            value = csvModel.getPrice();
            if (check(value)) {
                bookDto.setPrice(value.trim());
            }

            //publishDate
            value = csvModel.getPublishDate();
            if (check(value)) {
                bookDto.setPublishDate(value.trim());
            }

            //firstPublishDate
            value = csvModel.getFirstPublishDate();
            if (check(value)) {
                bookDto.setFirstPublishDate(value.trim());
            }

            //ratingsByStars
            value = csvModel.getRatingsByStars();
            if (check(value)) {
                bookDto.setRatingsByStars(value.trim());
            }

            //likedPercent
            value = csvModel.getLikedPercent();
            if (checkIfNumber(value)) {
                bookDto.setLikedPercent(Integer.valueOf(value));
            }

            //coverImg
            value = csvModel.getCoverImg();
            if (check(value)) {
                bookDto.setCoverImg(value.trim());
            }

            //bbeScore
            value = csvModel.getBbeScore();
            if (checkIfNumber(value)) {
                bookDto.setBbeScore(Integer.valueOf(value));
            }

            //bbeVotes
            value = csvModel.getBbeVotes();
            if (checkIfNumber(value)) {
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

            System.out.println(">>>>>>>>>>>>>>>>>>>> Single book parsing ended: %s".formatted(System.currentTimeMillis() - iteratorStart) );
        }

        BookUploadResult result = new BookUploadResult();
        result.setBookDtoList(bookDtoList);
        result.setBookErrorList(bookErrorsList);
        return result;
    }

    private static String[] divideStringSquare(String str) {
        int open = str.indexOf('[');
        int close = str.indexOf(']');

        if (open != -1 && close != -1 && open < close) {
            str = str.substring(1, str.length() - 1);
        }
        str = str.replace("'", "");
        str = str.replace("\"", "");

        return str.split(",");
    }

    private static boolean checkIfNumber(String string) {
        return NumberUtils.isCreatable(string);
    }

    private static boolean check(String string) {
        return string != null && !string.isBlank();
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
