package org.acme;

import io.cucumber.java.mk_latn.No;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TokenDTO {
    List<String> tokens = new ArrayList<>();
}
