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

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<Passport>> find() {
        return new ResponseEntity<>(
                passportService.findAllPassports(),
                HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Passport> save(@RequestBody Passport passport) {
        return new ResponseEntity<>(
                passportService.savePassport(passport),
                HttpStatus.CREATED
        );
    }

}
