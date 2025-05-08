package org.example.springtask1.service.mapper;

import org.example.springtask1.persistence.entity.*;
import org.example.springtask1.persistence.entity.Setting;
import org.example.springtask1.dto.SettingDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SettingMapper {

    public SettingDto mapToDto(Setting setting) {
        if (setting == null) {
            return null;
        }

        SettingDto settingDto = new SettingDto();
        settingDto.setId(setting.getId());
        settingDto.setSetting(setting.getSetting());

        return settingDto;
    }

    public List<SettingDto> mapToDtos(List<BookSetting> settings) {
        List<SettingDto> dtos = new ArrayList<>();

        for (BookSetting setting : settings) {
            dtos.add(mapToDto(setting.getSetting()));
        }

        return dtos;
    }

    public Setting mapFromDto(SettingDto dto) {
        if (dto == null) {
            return null;
        }
        Setting setting = new Setting();
        setting.setId(dto.getId());
        setting.setSetting(dto.getSetting());

        return setting;
    }

    public List<Setting> mapFromDtos(List<SettingDto> settingList, Book book) {
        List<Setting> settings = new ArrayList<>();

        for (SettingDto settingDto : settingList) {
            Setting setting = mapFromDto(settingDto);
            book.addBookSetting(setting);
            settings.add(setting);

        }

        return settings;
    }



}

