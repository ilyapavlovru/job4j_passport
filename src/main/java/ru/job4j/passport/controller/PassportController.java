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

    @GetMapping("/findAll")
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
        foundPassport.setSerial(passport.getSerial());
        foundPassport.setNumber(passport.getNumber());
        foundPassport.setExpirationDate(passport.getExpirationDate());

        return new ResponseEntity<>(passportService.savePassport(foundPassport), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@RequestParam int id) {
        passportService.findPassportById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Passport is not found. Please, check passportId"));
        passportService.deletePassportById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Passport>> findPassportsBySerial(@RequestParam int serial) {
        return new ResponseEntity<>(
                passportService.findPassportsBySerial(serial),
                HttpStatus.OK);
    }

    @GetMapping("/unavailable")
    public ResponseEntity<List<Passport>> findUnavailablePassports() {
        return new ResponseEntity<>(
                passportService.findUnavailablePassports(),
                HttpStatus.OK);
    }

    @GetMapping("/find-replaceable")
    public ResponseEntity<List<Passport>> findReplaceAblePassports() {
        return new ResponseEntity<>(
                passportService.findNeedReplace(3),
                HttpStatus.OK);
    }
}
