package br.com.calto.awpag.api.controller;

import br.com.calto.awpag.domain.model.Client;
import br.com.calto.awpag.domain.repository.ClientRepository;
import br.com.calto.awpag.domain.service.ClientService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

  private final ClientRepository clientRepository;

  private final ClientService clientService;

  @GetMapping
  public List<Client> listAll() {
    return clientRepository.findAll();
  }

  @GetMapping("/{clientId}")
  public ResponseEntity<Client> findById(@NonNull @PathVariable Long clientId) {
    var client = clientRepository.findById(clientId);

    if (client.isPresent()) {
      return ResponseEntity.ok(client.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public Client registerClient(@Valid @RequestBody Client client) {
    return clientService.save(client);
  }

  @PutMapping("/{clientId}")
  public ResponseEntity<Client> update(
    @PathVariable @NonNull Long clientId,
    @RequestBody @Valid Client client
  ) {
    if (!clientRepository.existsById(clientId)) {
      return ResponseEntity.notFound().build();
    }
    client.setId(clientId);
    return ResponseEntity.ok(clientService.save(client));
  }

  @DeleteMapping("/{clientId}")
  public ResponseEntity<Void> delete(@NonNull @PathVariable Long clientId) {
    if (!clientRepository.existsById(clientId)) {
      return ResponseEntity.notFound().build();
    }
    clientService.delete(clientId);
    return ResponseEntity.noContent().build();
  }
}
