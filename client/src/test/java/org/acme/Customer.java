package org.acme;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.LinkedList;
import java.util.Queue;

@XmlRootElement // Needed for XML serialization and deserialization
@Getter
@Setter // Automatic getter and setters and equals etc
@NoArgsConstructor // Needed for JSON deserialization and XML serialization and deserialization
@AllArgsConstructor
@Builder
public class Customer {
    String id;
    String firstName;
    String lastName;
    String cpr;
    String account;
    Queue<Token> tokens;


    public Token sendToken() throws Exception {
        return tokens.poll();
    }
}