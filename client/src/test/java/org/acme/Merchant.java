package org.acme;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@XmlRootElement // Needed for XML serialization and deserialization
@Getter
@Setter// Automatic getter and setters and equals etc
@NoArgsConstructor// Needed for JSON deserialization and XML serialization and deserialization
@AllArgsConstructor
@Builder

public class Merchant {
    String id;
    String firstName;
    String lastName;
    String cpr;
    String account;
}