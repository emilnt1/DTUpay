package org.acme;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
abstract class User {
    String id;
    String firstName;
    String lastName;
    String cpr;
    String accountId;
}
