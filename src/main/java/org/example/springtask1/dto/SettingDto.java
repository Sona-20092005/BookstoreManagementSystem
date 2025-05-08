package org.example.springtask1.dto;

import java.util.Objects;

public class SettingDto {

    private Long id;

    private String setting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettingDto that = (SettingDto) o;
        return Objects.equals(setting, that.setting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setting);
    }

    @Override
    public String toString() {
        return "SettingDto{" +
                "id=" + id +
                ", setting='" + setting + '\'' +
                '}';
    }
}
