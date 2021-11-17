package br.com.ghabriel.cadastro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ghabriel.cadastro.controller.dto.ProdutoDto;
import br.com.ghabriel.cadastro.modelo.Produto;
import br.com.ghabriel.cadastro.repository.ProdutoRepository;

@RestController
public class ProdutosController {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@RequestMapping("/topicos")
	public List<ProdutoDto> lista(String nomeProduto) {
		if(nomeProduto == null) {
			List<Produto> produtos = produtoRepository.findAll();
			return ProdutoDto.converter(produtos);
		}else {
			List<Produto> produtos = produtoRepository.findByNome(nomeProduto);
			return ProdutoDto.converter(produtos);
		}
	
	}
}
