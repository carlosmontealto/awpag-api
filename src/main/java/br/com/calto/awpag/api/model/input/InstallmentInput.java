package br.com.calto.awpag.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstallmentInput {

  @NotBlank
  @Size(max = 20)
  private String description;

  @NotNull
  @Positive
  private BigDecimal amount;

  @NotNull
  @Positive
  @Max(12)
  private Integer installmentAmount;

  @Valid
  @NotNull
  private ClientIdInput client;
}
