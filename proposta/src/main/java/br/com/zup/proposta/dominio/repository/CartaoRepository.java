package br.com.zup.proposta.dominio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	@Query("SELECT c FROM Cartao c JOIN FETCH c.proposta p LEFT JOIN FETCH c.carteiras ca WHERE c.id = :id")
	Optional<Cartao> findByIdJoinPropostaAndCarteiras(@Param("id") Long id);

}
