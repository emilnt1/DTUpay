package org.acme;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Payment{
    String token;
    BigDecimal amount;
}
