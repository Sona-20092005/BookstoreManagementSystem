package org.example.springtask1.dto;

import java.util.Objects;

public class SeriesDto {

    private Long id;

    private String series;

    private String seriesNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeriesDto seriesDto = (SeriesDto) o;
        return Objects.equals(series, seriesDto.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(series);
    }

    @Override
    public String toString() {
        return "SeriesDto{" +
                "id=" + id +
                ", series='" + series + '\'' +
                ", seriesNumber='" + seriesNumber + '\'' +
                '}';
    }
}
