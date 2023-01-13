package org.acme;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {
    Queue<Token> tokens;


    public Customer(String id, String firstName, String lastName, String cpr, String accountId, Queue<Token> tokens) {
        super(id, firstName, lastName, cpr, accountId);
        this.tokens = tokens;
    }

    public Token sendToken() throws Exception {
        return tokens.poll();
    }
}
