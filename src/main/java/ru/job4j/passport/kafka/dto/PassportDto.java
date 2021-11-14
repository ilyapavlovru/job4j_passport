package ru.job4j.passport.kafka.dto;

import java.util.Objects;

public class PassportDto {
    private String fio;
    private String email;

    public PassportDto(String fio, String email) {
        this.fio = fio;
        this.email = email;
    }

    public PassportDto() {

    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassportDto that = (PassportDto) o;
        return Objects.equals(fio, that.fio) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fio, email);
    }

    @Override
    public String toString() {
        return "PassportDto{" +
                "fio='" + fio + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
