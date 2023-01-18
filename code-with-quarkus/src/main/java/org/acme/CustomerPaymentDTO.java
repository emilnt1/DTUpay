package org.acme;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CustomerPaymentDTO {
    String mid;
    BigDecimal amount;
    String token;
}
