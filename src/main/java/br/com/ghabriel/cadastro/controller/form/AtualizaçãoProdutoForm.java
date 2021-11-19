package br.com.ghabriel.cadastro.controller.form;

import br.com.ghabriel.cadastro.modelo.Produto;
import br.com.ghabriel.cadastro.repository.ProdutoRepository;

public class AtualizaçãoProdutoForm {

	private String descricao;
	private String quantidade;

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

	public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
		Produto produto = produtoRepository.getById(id);
		produto.setDescricao(this.descricao);
		produto.setQuantidade(this.quantidade);
		return produto;
	}

}
