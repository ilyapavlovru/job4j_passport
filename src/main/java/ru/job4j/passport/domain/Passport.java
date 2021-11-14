package ru.job4j.passport.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fio;

    private String email;

    @Column(name = "passport_serial")
    private int serial;

    @Column(name = "passport_number")
    private int number;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "expiration")
    private LocalDateTime expirationDate;

    public Passport() {
    }

    public Passport(int id, String fio, String email, int serial, int number,
                    LocalDateTime birthDate, LocalDateTime expirationDate) {
        this.id = id;
        this.fio = fio;
        this.email = email;
        this.serial = serial;
        this.number = number;
        this.birthDate = birthDate;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return id == passport.id &&
                serial == passport.serial &&
                number == passport.number &&
                Objects.equals(fio, passport.fio) &&
                Objects.equals(email, passport.email) &&
                Objects.equals(birthDate, passport.birthDate) &&
                Objects.equals(expirationDate, passport.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, email, serial, number, birthDate, expirationDate);
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", email='" + email + '\'' +
                ", serial=" + serial +
                ", number=" + number +
                ", birthDate=" + birthDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
