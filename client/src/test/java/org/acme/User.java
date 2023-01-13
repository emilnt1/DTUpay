package org.acme;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
abstract class User {
    String id;
    String firstName;
    String lastName;
    String cpr;
    String account;
}
