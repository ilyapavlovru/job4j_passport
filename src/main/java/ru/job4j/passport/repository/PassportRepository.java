package ru.job4j.passport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.passport.domain.Passport;

import java.util.Date;
import java.util.List;

@Repository
public interface PassportRepository extends CrudRepository<Passport, Integer> {
    List<Passport> findAllBySerial(int serial);
    List<Passport> findAllByExpirationDateAfter(Date date);
}
