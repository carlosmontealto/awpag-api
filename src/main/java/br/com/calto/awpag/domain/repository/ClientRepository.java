package br.com.calto.awpag.domain.repository;

import br.com.calto.awpag.domain.model.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
  List<Client> findByNameContaining(String name);
  Optional<Client> findByEmail(String email);
}
