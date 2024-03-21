package br.com.calto.awpag.domain.model;

import br.com.calto.awpag.domain.validation.ValidationGroups;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "tb_client")
@NoArgsConstructor
@AllArgsConstructor
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @NotNull(groups = ValidationGroups.ClientId.class)
  private Long id;

  @NotBlank
  @Size(max = 60)
  private String name;

  @NotBlank
  @Size(max = 255)
  @Email
  private String email;

  @NotBlank
  @Size(max = 20)
  private String phone;
}
