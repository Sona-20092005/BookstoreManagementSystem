package org.example.springtask1.service.mapper;

import org.example.springtask1.persistence.entity.Book;
import org.example.springtask1.persistence.entity.BookFormat;
import org.example.springtask1.persistence.entity.Format;
import org.example.springtask1.dto.FormatDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FormatMapper {

    public FormatDto mapToDto(Format format) {
        if (format == null) {
            return null;
        }

        FormatDto formatDto = new FormatDto();
        formatDto.setId(format.getId());
        formatDto.setFormat(formatDto.getFormat());

        return formatDto;
    }

    public List<FormatDto> mapToDtos(List<BookFormat> formats) {
        List<FormatDto> dtos = new ArrayList<>();

        for (BookFormat format : formats) {
            dtos.add(mapToDto(format.getFormat()));
        }

        return dtos;
    }

    public Format mapFromDto(FormatDto dto) {
        if (dto == null) {
            return null;
        }

        Format format = new Format();
        format.setId(dto.getId());
        format.setFormat(dto.getFormat());

        return format;
    }

    public List<Format> mapFromDtos(List<FormatDto> formatList, Book book) {
        List<Format> formats = new ArrayList<>();

        for (FormatDto formatDto : formatList) {
            Format format = mapFromDto(formatDto);
            book.addBookFormat(format);
            formats.add(format);

        }

        return formats;
    }

}

