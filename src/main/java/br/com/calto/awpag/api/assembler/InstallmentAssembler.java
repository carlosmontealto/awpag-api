package br.com.calto.awpag.api.assembler;

import br.com.calto.awpag.api.model.InstallmentModel;
import br.com.calto.awpag.api.model.input.InstallmentInput;
import br.com.calto.awpag.domain.model.Installment;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class InstallmentAssembler {

  private final ModelMapper modelMapper;

  public InstallmentModel toModel(Installment installment) {
    return modelMapper.map(installment, InstallmentModel.class);
  }

  public List<InstallmentModel> toCollectionModel(
    List<Installment> listOfInstallments
  ) {
    return listOfInstallments.stream().map(this::toModel).toList();
  }

  public Installment toEntity(InstallmentInput installmentInput) {
    return modelMapper.map(installmentInput, Installment.class);
  }
}
