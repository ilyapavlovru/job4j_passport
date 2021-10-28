package ru.job4j.passport.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fio;

    @Column(name = "serial")
    private String serialNumber;

    @Column(name = "expiration")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expirationDate;

    public Passport() {
    }

    public Passport(int id, String fio, String serialNumber, Date expirationDate) {
        this.id = id;
        this.fio = fio;
        this.serialNumber = serialNumber;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return id == passport.id &&
                Objects.equals(fio, passport.fio) &&
                Objects.equals(serialNumber, passport.serialNumber) &&
                Objects.equals(expirationDate, passport.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fio, serialNumber, expirationDate);
    }
}
