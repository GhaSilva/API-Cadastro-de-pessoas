package br.com.ghabriel.cadastro.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.ghabriel.cadastro.modelo.Produto;

public class ProdutoForm {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String descricao;
	@NotNull @NotEmpty
	private String quantidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public Produto converter() {
		return new Produto(nome, descricao, quantidade);
	}

}
