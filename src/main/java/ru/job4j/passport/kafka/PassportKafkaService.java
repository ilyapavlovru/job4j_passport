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
import ru.job4j.passport.kafka.dto.PassportDto;

@Service
public class PassportKafkaService {

    @Autowired
    private KafkaTemplate<String, PassportDto> kafkaTemplate;

    @Value(value = "${app.topic.name}")
    private String topicName;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Scheduled(fixedRateString = "10000")
    public void sendMessage() {



        PassportDto passportDto1 = new PassportDto("Ilya","ilya@mail.ru");
//        PassportDto passportDto2 = new PassportDto("Petr","petr@mail.ru");
//        List<PassportDto> passportDtos = Arrays.asList(passportDto1, passportDto2);

        logger.info("send message = {}", passportDto1);
        Message<PassportDto> message = MessageBuilder
                .withPayload(passportDto1)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(topicName, passportDto1);

    }

}
