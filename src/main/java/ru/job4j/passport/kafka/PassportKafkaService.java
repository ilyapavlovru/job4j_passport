package ru.job4j.passport.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.passport.domain.Passport;
import ru.job4j.passport.kafka.dto.PassportDto;
import ru.job4j.passport.kafka.mapper.DomainToKafka;
import ru.job4j.passport.service.PassportService;

import java.util.List;

@Service
public class PassportKafkaService {

    @Autowired
    private KafkaTemplate<String, PassportDto> kafkaTemplate;

    @Autowired
    private PassportService passportService;

    @Autowired
    private DomainToKafka toKafka;

    @Value(value = "${app.topic.name}")
    private String topicName;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedRateString = "10000")
    public void sendMessage() {

        List<Passport> passports = passportService.findUnavailablePassports();
        List<PassportDto> passportDtos = toKafka.toKafkaPassportDtos(passports);

        passportDtos.forEach(passportDto -> {
            logger.info("send message = {}", passportDto);
            Message<PassportDto> message = MessageBuilder
                    .withPayload(passportDto)
                    .setHeader(KafkaHeaders.TOPIC, topicName)
                    .build();
            kafkaTemplate.send(message);
        });
    }

}
