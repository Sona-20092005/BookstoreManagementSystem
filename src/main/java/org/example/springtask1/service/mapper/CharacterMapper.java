package org.example.springtask1.service.mapper;

import org.example.springtask1.persistence.entity.*;
import org.example.springtask1.persistence.entity.Character;
import org.example.springtask1.dto.CharacterDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    public CharacterDto mapToDto(Character character) {
        if (character == null) {
            return null;
        }

        CharacterDto characterDto = new CharacterDto();
        characterDto.setId(character.getId());
        characterDto.setName(character.getName());

        return characterDto;
    }

    public List<CharacterDto> mapToDtos(List<BookCharacter> characters) {
        List<CharacterDto> dtos = new ArrayList<>();

        for (BookCharacter character : characters) {
            dtos.add(mapToDto(character.getCharacter()));
        }

        return dtos;
    }

    public Character mapFromDto(CharacterDto dto) {
        if (dto == null) {
            return null;
        }

        Character character = new Character();
        character.setId(dto.getId());
        character.setName(dto.getName());

        return character;
    }

    public List<Character> mapFromDtos(List<CharacterDto> characterList, Book book) {
        List<Character> characters = new ArrayList<>();

        for (CharacterDto characterDto : characterList) {
            Character character = mapFromDto(characterDto);
            book.addBookCharacter(character);
            characters.add(character);

        }

        return characters;
    }



}

