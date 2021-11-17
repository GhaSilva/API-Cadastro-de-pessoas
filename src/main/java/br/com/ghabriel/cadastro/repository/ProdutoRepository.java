package br.com.ghabriel.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ghabriel.cadastro.modelo.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
