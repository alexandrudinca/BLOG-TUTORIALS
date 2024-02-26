package com.example.demo.runner;

import com.example.demo.entity.Record;
import com.example.demo.repository.RecordRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final RecordRepository repository;

    @Override
    public void run(String... args) throws Exception {
        int numberOfRecords = 30;
        final Faker faker = new Faker(Locale.FRANCE);

        while (numberOfRecords > 0) {
            var employee = Record.builder()
                .fullName(faker.name().fullName())
                .address(faker.address().fullAddress())
                .age(getRandomNumberBetween(26, 40))
                .matricule(faker.business().creditCardNumber())
                .salary(generateRandomSalary())
                .build();

            repository.save(employee);
            numberOfRecords--;
        }
    }

    private static BigDecimal generateRandomSalary() {
        return BigDecimal.valueOf(getRandomNumberBetween(40, 120) * 1000L);
    }

    private static int getRandomNumberBetween(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
