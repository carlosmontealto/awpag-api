package br.com.calto.awpag.api.controller;

import br.com.calto.awpag.domain.exception.DomainException;
import br.com.calto.awpag.domain.model.Installment;
import br.com.calto.awpag.domain.repository.InstallmentRepository;
import br.com.calto.awpag.domain.service.InstallmentService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

  @GetMapping
  public List<Installment> listAll() {
    return installmentsRepository.findAll();
  }

  @GetMapping("/{installmentId}")
  public ResponseEntity<Installment> getInstallment(
    @PathVariable @NonNull Long installmentId
  ) {
    return installmentsRepository
      .findById(installmentId)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Installment register(@RequestBody @Valid Installment installment) {
    return installmentService.register(installment);
  }

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<String> capture(DomainException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }
}
