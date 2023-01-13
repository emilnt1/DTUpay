package org.acme;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MerchantDTO {
    String id;
    String firstName;
    String lastName;
    String cpr;
    String account;
}
