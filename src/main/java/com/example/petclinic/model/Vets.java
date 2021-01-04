package com.example.petclinic.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Vets {

    private List<Vet> vetList;

    @XmlElement(name = "vet")
    public List<Vet> getVetList() {
        if (vetList == null) {
            vetList = new ArrayList<>();
        }
        return vetList;
    }

}
