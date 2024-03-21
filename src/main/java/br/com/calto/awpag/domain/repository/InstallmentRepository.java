package br.com.calto.awpag.domain.repository;

import br.com.calto.awpag.domain.model.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallmentRepository
  extends JpaRepository<Installment, Long> {}
