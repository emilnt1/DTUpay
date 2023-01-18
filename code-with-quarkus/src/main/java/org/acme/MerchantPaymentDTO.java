package org.acme;

import io.cucumber.java.mk_latn.No;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MerchantPaymentDTO {
    String token;
    BigDecimal amount;
}
