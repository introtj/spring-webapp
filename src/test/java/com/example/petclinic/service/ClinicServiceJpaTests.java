package com.example.petclinic.service;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"jpa"})
@TestPropertySource(locations = {"classpath:application.properties", "classpath:data-access.properties"})
@SpringJUnitConfig(locations = {"classpath:spring/root-context.xml"})
public class ClinicServiceJpaTests extends AbstractClinicServiceTests {
}
