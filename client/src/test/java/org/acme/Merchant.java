package org.acme;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@XmlRootElement // Needed for XML serialization and deserialization
@Getter
@Setter// Automatic getter and setters and equals etc

public class Merchant extends User {

    public Merchant(String id, String firstName, String lastName, String cpr, String account) {
        super(id, firstName, lastName, cpr, account);
    }
}