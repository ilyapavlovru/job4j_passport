package ru.job4j.passport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.passport.domain.Passport;

@Repository
public interface PassportRepository extends CrudRepository<Passport, Integer> {
}
