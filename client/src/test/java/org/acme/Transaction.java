package org.acme;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    String cid, mid;
    BigDecimal amount;
    String token;
}
