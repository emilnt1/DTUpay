package org.acme;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
abstract class User {
    String id;
    String firstName;
    String lastName;
    String cpr;
    String account;
}
