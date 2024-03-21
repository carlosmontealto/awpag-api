package br.com.calto.awpag.domain.service;

import br.com.calto.awpag.domain.exception.DomainException;
import br.com.calto.awpag.domain.model.Client;
import br.com.calto.awpag.domain.model.Installment;
import br.com.calto.awpag.domain.repository.InstallmentRepository;
import java.time.LocalDateTime;
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
      throw new DomainException("Create a installment must not have an ID");
    } else if (installment.getClient().getId() == null) {
      throw new DomainException("Client ID must not be null");
    }

    Client client = clientService.find(installment.getClient().getId());

    installment.setClient(client);
    installment.setCreatedAt(LocalDateTime.now());

    return installmentsRepository.save(installment);
  }
}
