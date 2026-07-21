package com.cognizant.hql.intro.demo;

import com.cognizant.hql.intro.model.Employee;
import com.cognizant.hql.intro.service.IntroQueryService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class IntroDemoRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(IntroDemoRunner.class);

    private final IntroQueryService introQueryService;

    public IntroDemoRunner(IntroQueryService introQueryService) {
        this.introQueryService = introQueryService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 01: JPQL / HQL introduction ===");

        System.out.println("\n-- JPQL SELECT: salary >= 85000 --");
        introQueryService.highEarners(new BigDecimal("85000")).forEach(System.out::println);

        System.out.println("\n-- JPQL SELECT: department = Engineering --");
        introQueryService.byDepartment("Engineering").forEach(System.out::println);

        System.out.println("\n-- HQL SELECT via EntityManager: permanent employees --");
        introQueryService.hqlPermanentEmployees().forEach(System.out::println);

        System.out.println("\n-- JPQL UPDATE: 5% raise in Engineering (rolled back in demo by re-seed on restart) --");
        int updated = introQueryService.applyRaise("Engineering", new BigDecimal("1.05"));
        System.out.println("Rows updated: " + updated);

        System.out.println("\n-- JPQL DELETE: remove non-permanent employees --");
        int deleted = introQueryService.removeTemporaryStaff();
        System.out.println("Rows deleted: " + deleted);

        log.info("=== Hands-On 01 complete ===");
    }
}
