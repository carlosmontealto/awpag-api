package br.com.calto.awpag.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstallmentModel {

  private Long id;
  private ClientResumeModel client;
  private String description;
  private BigDecimal amount;
  private Integer installmentAmount;
  private OffsetDateTime createdAt;
}
