package org.acme;

import lombok.Data;

import java.math.BigDecimal;

@Data
public abstract class Payment{
    String token;
    BigDecimal amount;





}
