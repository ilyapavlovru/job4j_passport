package ru.job4j.passport.kafka.mapper;

import org.springframework.stereotype.Component;
import ru.job4j.passport.domain.Passport;
import ru.job4j.passport.kafka.dto.PassportDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DomainToKafka {

    public PassportDto toKafkaPassportDto(Passport passport) {
        return new PassportDto(passport.getFio(), passport.getEmail());
    }

    public List<PassportDto> toKafkaPassportDtos(List<Passport> passports) {
        return passports.stream().map(
                this::toKafkaPassportDto)
                .collect(Collectors.toList());
    }
}
