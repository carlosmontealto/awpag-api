package br.com.calto.awpag.domain.service;

import br.com.calto.awpag.domain.exception.DomainException;
import br.com.calto.awpag.domain.model.Client;
import br.com.calto.awpag.domain.model.Installment;
import br.com.calto.awpag.domain.repository.InstallmentRepository;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class InstallmentService {

  private final InstallmentRepository installmentsRepository;

  private final ClientService clientService;

  @Transactional
  public Installment register(Installment installment) {
    if (installment.getId() != null) {
      throw new DomainException("create a installment must not have an ID");
    }

    Client client = clientService.find(installment.getClient().getId());

    installment.setClient(client);
    installment.setCreatedAt(OffsetDateTime.now());

    return installmentsRepository.save(installment);
  }
}
