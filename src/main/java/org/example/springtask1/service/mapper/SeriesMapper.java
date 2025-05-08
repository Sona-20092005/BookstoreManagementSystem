package org.example.springtask1.service.mapper;

import org.example.springtask1.persistence.entity.*;
import org.example.springtask1.dto.SeriesDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeriesMapper {

    public SeriesDto mapToDto(Series series) {
        if (series == null) {
            return null;
        }

        SeriesDto seriesDto = new SeriesDto();
        seriesDto.setId(seriesDto.getId());
        seriesDto.setSeries(series.getSeries());

        return seriesDto;
    }

    public List<SeriesDto> mapToDtos(List<BookSeries> series) {
        List<SeriesDto> dtos = new ArrayList<>();

        for (BookSeries series1 : series) {
            dtos.add(mapToDto(series1.getSeries()));
        }

        return dtos;
    }

    public Series mapFromDto(SeriesDto dto) {
        if (dto == null) {
            return null;
        }
        Series series = new Series();
        series.setId(dto.getId());
        series.setSeries(dto.getSeries());

        return series;
    }

    public List<Series> mapFromDtos(List<SeriesDto> seriesList, Book book) {
        List<Series> seriesListNew = new ArrayList<>();

        for (SeriesDto seriesDto : seriesList) {
            Series series = mapFromDto(seriesDto);
            book.addBookSeries(series, seriesDto.getSeriesNumber());
            seriesListNew.add(series);

        }

        return seriesListNew;
    }



}

