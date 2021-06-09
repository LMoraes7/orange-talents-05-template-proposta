package br.com.zup.proposta.dominio.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.proposta.dominio.modelo.proposta.Proposta;
import br.com.zup.proposta.dominio.modelo.proposta.StatusProposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	Set<Proposta> findByStatus(StatusProposta status);
	
	Set<Proposta> findByStatusAndCartaoIsNull(StatusProposta status);
	
}
