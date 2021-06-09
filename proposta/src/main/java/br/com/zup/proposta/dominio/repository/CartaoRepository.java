package br.com.zup.proposta.dominio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.proposta.dominio.modelo.cartao.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long>{

}
