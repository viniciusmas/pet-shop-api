package br.edu.infnet.petshopapi.model.repository;

import br.edu.infnet.petshopapi.model.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

}