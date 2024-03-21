package br.com.calto.awpag.domain.service;

import br.com.calto.awpag.domain.exception.DomainException;
import br.com.calto.awpag.domain.model.Client;
import br.com.calto.awpag.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClientService {

  private final ClientRepository clientRepository;

  @Transactional
  public Client save(Client client) {
    boolean isEmailUsed = clientRepository
      .findByEmail(client.getEmail())
      .filter(c -> !c.equals(client))
      .isPresent();

    if (isEmailUsed) {
      throw new DomainException("email is already  in use");
    }

    return clientRepository.save(client);
  }

  @SuppressWarnings("null")
  @Transactional
  public void delete(Long clientId) {
    clientRepository.deleteById(clientId);
  }

  @SuppressWarnings("null")
  public Client find(Long clientId) {
    return clientRepository
      .findById(clientId)
      .orElseThrow(() -> new DomainException("client not found"));
  }
}
