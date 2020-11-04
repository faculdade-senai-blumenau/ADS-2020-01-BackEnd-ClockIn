package br.com.senai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.stream.LongStream;

@SpringBootApplication
public class BackEndAplication {
    public static void main(String[] args) {
        SpringApplication.run(BackEndAplication.class, args);

    }
}
