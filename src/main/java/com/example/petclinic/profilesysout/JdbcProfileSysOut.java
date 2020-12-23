package com.example.petclinic.profilesysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("jdbc")
public class JdbcProfileSysOut{

    @Autowired
    public JdbcProfileSysOut(@Value("${spring.profile.message}") String msg,
                           @Value("${spring.profiles.active}") String springProfilesActive) {
        System.out.println("##################################");
        System.out.println("##################################");
        System.out.println("##            jdbc              ##");
        System.out.println("spring.profiles.active: " + springProfilesActive);
        System.out.println(msg);
        System.out.println("##################################");
        System.out.println("##################################");
    }
}
