package org.acme;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
abstract class User {
    String id;
    String firstName;
    String lastName;
    String cpr;
    String account;
}
