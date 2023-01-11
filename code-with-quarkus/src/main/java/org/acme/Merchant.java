package org.acme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Merchant extends User {

    public Merchant(String id, String firstName, String lastName, String cpr, String account) {
        super(id, firstName, lastName, cpr, account);
    }
}
