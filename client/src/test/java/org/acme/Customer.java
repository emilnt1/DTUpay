package org.acme;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
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
    Queue<String> tokens;


    public String removeToken(){
        return tokens.poll();
    }

    public void recieveTokens(List<String> tokens){
        this.tokens.addAll(tokens);
    }
}