package br.com.ghabriel.cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ghabriel.cadastro.modelo.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


	List<Produto> findByNome(String nomeProduto);

}
