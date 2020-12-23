package com.example.petclinic.profilesysout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class DefaultProfileSysOut {

    @Autowired
    public DefaultProfileSysOut(@Value("${spring.profile.message}") String msg,
                                @Value("${spring.profiles.default}") String springProfilesActive) {
        System.out.println("##################################");
        System.out.println("##################################");
        System.out.println("##            default           ##");
        System.out.println("spring.profiles.default: " + springProfilesActive);
        System.out.println(msg);
        System.out.println("##################################");
        System.out.println("##################################");
    }
}
