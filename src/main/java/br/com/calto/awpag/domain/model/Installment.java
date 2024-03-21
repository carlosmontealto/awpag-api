package br.com.calto.awpag.domain.model;

import br.com.calto.awpag.domain.validation.ValidationGroups;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_installment")
public class Installment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Valid
  @ConvertGroup(to = ValidationGroups.ClientId.class)
  @NotNull
  @ManyToOne
  //@JoinColumn(name = "client_id")
  private Client client;

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

  private OffsetDateTime createdAt;
}
