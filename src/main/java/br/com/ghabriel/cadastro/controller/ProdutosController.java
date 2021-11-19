package br.com.ghabriel.cadastro.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ghabriel.cadastro.controller.dto.DetalhesProdutoDto;
import br.com.ghabriel.cadastro.controller.dto.ProdutoDto;
import br.com.ghabriel.cadastro.controller.form.AtualizaçãoProdutoForm;
import br.com.ghabriel.cadastro.controller.form.ProdutoForm;
import br.com.ghabriel.cadastro.modelo.Produto;
import br.com.ghabriel.cadastro.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<ProdutoDto> lista(String nomeProduto) {
		if (nomeProduto == null) {
			List<Produto> produtos = produtoRepository.findAll();
			return ProdutoDto.converter(produtos);
		} else {
			List<Produto> produtos = produtoRepository.findByNome(nomeProduto);
			return ProdutoDto.converter(produtos);
		}

	}

	@PostMapping
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm produtoForm,
			UriComponentsBuilder uriBuilder) throws Exception {

		if (produtoForm.getNome() == null || produtoForm.getNome().isEmpty() || produtoForm.getDescricao() == null
				|| produtoForm.getDescricao().isEmpty() || produtoForm.getQuantidade() == null
				|| produtoForm.getQuantidade().isEmpty()) {
			throw new Exception("Campos inválidos");
		}

		Produto produto = produtoForm.converter();
		produtoRepository.save(produto);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}

	@GetMapping("/{id}")
	public DetalhesProdutoDto detalhar(@PathVariable Long id) {
		Produto produto = produtoRepository.getById(id);
		return new DetalhesProdutoDto(produto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody AtualizaçãoProdutoForm form)
			throws Exception {
		if (form.getDescricao() == null || form.getDescricao().isEmpty()
				|| form.getQuantidade() == null | form.getQuantidade().isEmpty()) {
			throw new Exception("Campos inválidos");
		}
		Produto produto = form.atualizar(id, produtoRepository);
		return ResponseEntity.ok(new ProdutoDto(produto));

	}

}
