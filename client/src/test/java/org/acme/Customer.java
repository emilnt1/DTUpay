package org.acme;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.LinkedList;
import java.util.Queue;

@XmlRootElement // Needed for XML serialization and deserialization
@Getter(AccessLevel.PROTECTED)
@Setter // Automatic getter and setters and equals etc
@NoArgsConstructor // Needed for JSON deserialization and XML serialization and deserialization
@AllArgsConstructor
public class Customer extends User {
    Queue<Token> tokens = new LinkedList<>();


    public Customer(String id, String firstName, String lastName, String cpr, String accountId) {
        super(id, firstName, lastName, cpr, accountId);
    }

    public Token sendToken() throws Exception {
        return tokens.poll();
    }
}