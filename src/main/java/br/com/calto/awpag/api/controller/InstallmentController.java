package br.com.calto.awpag.api.controller;

import br.com.calto.awpag.api.assembler.InstallmentAssembler;
import br.com.calto.awpag.api.model.InstallmentModel;
import br.com.calto.awpag.api.model.input.InstallmentInput;
import br.com.calto.awpag.domain.repository.InstallmentRepository;
import br.com.calto.awpag.domain.service.InstallmentService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/installments")
public class InstallmentController {

  private final InstallmentRepository installmentsRepository;
  private final InstallmentService installmentService;
  private final InstallmentAssembler installmentAssembler;

  @GetMapping
  public List<InstallmentModel> listAll() {
    return installmentAssembler.toCollectionModel(
      installmentsRepository.findAll()
    );
  }

  @GetMapping("/{installmentId}")
  public ResponseEntity<InstallmentModel> getInstallment(
    @PathVariable @NonNull Long installmentId
  ) {
    return installmentsRepository
      .findById(installmentId)
      .map(installmentAssembler::toModel)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public InstallmentModel register(
    @RequestBody @Valid InstallmentInput installmentInput
  ) {
    var installment = installmentAssembler.toEntity(installmentInput);
    return installmentAssembler.toModel(
      installmentService.register(installment)
    );
  }
}
