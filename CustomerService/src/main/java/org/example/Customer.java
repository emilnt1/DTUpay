package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Customer {

    private String id;
    private String firstName;
    private String lastName;
    private String cpr;
    private String accountId;

}
