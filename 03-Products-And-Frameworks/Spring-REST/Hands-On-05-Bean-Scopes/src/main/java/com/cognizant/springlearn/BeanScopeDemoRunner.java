package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class BeanScopeDemoRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanScopeDemoRunner.class);

    @Override
    public void run(String... args) {
        try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("scope-beans.xml")) {
            ScopeDemoBean singletonFirst = ctx.getBean("singletonBean", ScopeDemoBean.class);
            ScopeDemoBean singletonSecond = ctx.getBean("singletonBean", ScopeDemoBean.class);

            ScopeDemoBean prototypeFirst = ctx.getBean("prototypeBean", ScopeDemoBean.class);
            ScopeDemoBean prototypeSecond = ctx.getBean("prototypeBean", ScopeDemoBean.class);

            LOGGER.debug("Singleton same reference: {}", singletonFirst == singletonSecond);
            LOGGER.debug("Prototype same reference: {}", prototypeFirst == prototypeSecond);
            LOGGER.debug("Total ScopeDemoBean constructions: {}", ScopeDemoBean.getConstructionCount());
        }
    }
}
