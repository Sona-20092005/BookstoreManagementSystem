package org.example.springtask1.service.mapper;

import org.example.springtask1.persistence.entity.*;
import org.example.springtask1.dto.AwardDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AwardMapper {

    public AwardDto mapToDto(Award award) {
        if (award == null) {
            return null;
        }

        AwardDto awardDto = new AwardDto();
        awardDto.setId(award.getId());
        awardDto.setAward(award.getAward());

        return awardDto;
    }

    public List<AwardDto> mapToDtos(List<BookAward> awards) {
        List<AwardDto> dtos = new ArrayList<>();

        for (BookAward award : awards) {
            dtos.add(mapToDto(award.getAward()));
        }

        return dtos;
    }

    public Award mapFromDto(AwardDto dto) {
        if (dto == null) {
            return null;
        }

        Award award = new Award();
        award.setId(dto.getId());
        award.setAward(dto.getAward());

        return award;
    }

    public List<Award> mapFromDtos(List<AwardDto> awardList, Book book) {
        List<Award> awards = new ArrayList<>();

        for (AwardDto awardDto : awardList) {
            Award award = mapFromDto(awardDto);
            book.addBookAward(award, awardDto.getYear());
            awards.add(award);

        }

        return awards;
    }
}

