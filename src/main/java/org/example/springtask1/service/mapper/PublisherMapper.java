package org.example.springtask1.service.mapper;

import org.example.springtask1.persistence.entity.Publisher;
import org.example.springtask1.dto.PublisherDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublisherMapper {

    public PublisherDto mapToDto(Publisher publisher) {
        if (publisher == null) {
            return null;
        }

        PublisherDto publisherDto = new PublisherDto();
        publisherDto.setId(publisher.getId());
        publisherDto.setName(publisher.getName());

        return publisherDto;
    }

    public List<PublisherDto> mapToDtos(List<Publisher> publishers) {
        List<PublisherDto> dtos = new ArrayList<>();

        for (Publisher publisher : publishers) {
            dtos.add(mapToDto(publisher));
        }

        return dtos;
    }

    public Publisher mapFromDto(PublisherDto dto) {
        if (dto == null) {
            return null;
        }

        Publisher publisher = new Publisher();
        publisher.setId(dto.getId());
        publisher.setName(dto.getName());

        return publisher;
    }



}

