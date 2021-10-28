package ru.job4j.passport.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.passport.domain.Passport;
import ru.job4j.passport.service.PassportService;

import java.util.List;

@RestController
@RequestMapping("/passport")
public class PassportController {

    private final PassportService passportService;

    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Passport>> find() {
        return new ResponseEntity<>(
                passportService.findAllPassports(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        return new ResponseEntity<>(
                passportService.savePassport(passport),
                HttpStatus.CREATED
        );
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Passport> update(@RequestParam int id, @RequestBody Passport passport) {

        Passport foundPassport = passportService.findPassportById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Passport is not found. Please, check passportId"));

        foundPassport.setFio(passport.getFio());
        foundPassport.setSerialNumber(passport.getSerialNumber());
        foundPassport.setExpirationDate(passport.getExpirationDate());

        return new ResponseEntity<>(passportService.savePassport(foundPassport), HttpStatus.OK);
    }
}
