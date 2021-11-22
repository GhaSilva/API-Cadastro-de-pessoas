package br.com.ghabriel.cadastro.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.ghabriel.cadastro.modelo.Produto;

public class ProdutoDto {

	private Long id;
	private String nome;
	private String quantidade;
	private LocalDateTime dataCriacao;
	
	

	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.quantidade = produto.getQuantidade();
		this.dataCriacao = produto.getDataCricao();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public LocalDateTime getDataCricao() {
		return dataCriacao;
	}

	public static Page<ProdutoDto> converter(Page<Produto> produtos) {
		
		return produtos.map(ProdutoDto::new);
	}

}
