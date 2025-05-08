package org.example.springtask1.dto;

import java.util.Objects;

public class AwardDto {

    private Long id;

    private String award;
    private Integer year;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardDto awardDto = (AwardDto) o;
        return Objects.equals(award, awardDto.award) && Objects.equals(year, awardDto.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(award, year);
    }

    @Override
    public String toString() {
        return "AwardDto{" +
                "id=" + id +
                ", award='" + award + '\'' +
                ", year=" + year +
                '}';
    }
}
