package com.example.petclinic.profilesysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("jpa")
public class JpaProfileSysOut {

    @Autowired
    public JpaProfileSysOut(@Value("${spring.profile.message}") String msg,
                            @Value("${spring.profiles.active:jpa}") String springProfilesActive) {
        System.out.println("##################################");
        System.out.println("##################################");
        System.out.println("##             jpa              ##");
        System.out.println("spring.profiles.active: " + springProfilesActive);
        System.out.println(msg);
        System.out.println("##################################");
        System.out.println("##################################");
    }
}
