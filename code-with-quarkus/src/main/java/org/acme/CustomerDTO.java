package org.acme;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Queue;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    String id;
    String firstName;
    String lastName;
    String cpr;
    String account;
    Queue<Token> tokens;

}
