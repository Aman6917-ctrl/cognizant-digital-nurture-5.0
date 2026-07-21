package com.cognizant.springlearn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DateFormatDemoRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateFormatDemoRunner.class);

    @Override
    public void run(String... args) {
        try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("date-format.xml")) {
            SimpleDateFormat df = ctx.getBean("dateFormat", SimpleDateFormat.class);
            Date parsed = df.parse("31/12/2018");
            LOGGER.debug("Parsed date: {}", parsed);
        } catch (ParseException ex) {
            LOGGER.error("Failed to parse date", ex);
        }
    }
}
