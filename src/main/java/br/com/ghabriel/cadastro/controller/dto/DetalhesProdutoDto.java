package br.com.ghabriel.cadastro.controller.dto;

import java.time.LocalDateTime;

import br.com.ghabriel.cadastro.modelo.Produto;

public class DetalhesProdutoDto {
	private String nome;
	private String descricao;
	private String quantidade;
	private LocalDateTime dataCriacao;


	

	public DetalhesProdutoDto(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.quantidade = produto.getQuantidade();
		this.dataCriacao = produto.getDataCricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

}
