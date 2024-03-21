package br.com.calto.awpag.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientIdInput {

  @NotNull
  private Long id;
}
