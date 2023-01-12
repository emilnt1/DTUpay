package org.acme;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {
    Queue<Token> tokens = new LinkedList<>();


    public Customer(String id, String firstName, String lastName, String cpr, String account) {
        super(id, firstName, lastName, cpr, account);
    }

    public Token sendToken() throws Exception {
        return tokens.poll();
    }
}