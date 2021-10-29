package ru.job4j.passport.service;

import org.springframework.stereotype.Service;
import ru.job4j.passport.domain.Passport;
import ru.job4j.passport.repository.PassportRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PassportService {

    private final PassportRepository passportRepository;

    public PassportService(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public List<Passport> findAllPassports() {
        List<Passport> rsl = new ArrayList<>();
        passportRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public Passport savePassport(Passport passport) {
        return passportRepository.save(passport);
    }

    public Optional<Passport> findPassportById(int id) {
        return passportRepository.findById(id);
    }

    public void deletePassportById(int id) {
        passportRepository.deleteById(id);
    }

    public List<Passport> findPassportsBySerial(int serial) {
        return passportRepository.findAllBySerial(serial);
    }

    public List<Passport> findUnavailablePassports() {
        Date date = new Date(System.currentTimeMillis());
        return passportRepository.findAllByExpirationDateAfter(date);
    }

    public List<Passport> findReplaceAblePassports() {
        Date date = Date.from(LocalDate.now().minusMonths(3).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return passportRepository.findAllByExpirationDateAfter(date);
    }
}
