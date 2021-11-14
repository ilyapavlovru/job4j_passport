package ru.job4j.passport.service;

import org.springframework.stereotype.Service;
import ru.job4j.passport.domain.Passport;
import ru.job4j.passport.repository.PassportRepository;

import java.time.*;
import java.util.ArrayList;
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
        LocalDateTime localDateTime = LocalDateTime.now();
        return passportRepository.findAllByExpirationDateBefore(localDateTime.toLocalDate().atTime(0, 0));
    }

    /** Паспорта, которые небходимо заменить в ближайшие months месяцев */
    public List<Passport> findNeedReplace(int months) {
        // находим дату через months месяцев
        LocalDateTime localDateTime = LocalDateTime.now().plus(Period.ofMonths(months));
        return passportRepository.findAllByExpirationDateBetween(
                localDateTime.toLocalDate().atTime(0, 0),
                localDateTime.toLocalDate().atTime(0, 0).plusDays(1));
    }
}
