package br.com.ghabriel.cadastro.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Page<ProdutoDto> lista(@RequestParam(required = false) String nomeProduto,
			@PageableDefault(sort = "id", direction = Direction.ASC) Pageable paginacao) {
		// Programar é uma arte
		// Você não entender faz parte
		if (nomeProduto == null) {
			Page<Produto> produtos = produtoRepository.findAll(paginacao);
			return ProdutoDto.converter(produtos);
		} else {
			Page<Produto> produtos = produtoRepository.findByNome(nomeProduto, paginacao);
			return ProdutoDto.converter(produtos);
		}

	}

	@PostMapping
	@Transactional
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
	public ResponseEntity<DetalhesProdutoDto> detalhar(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok(new DetalhesProdutoDto(produto.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody AtualizaçãoProdutoForm form)
			throws Exception {
		if (form.getDescricao() == null || form.getDescricao().isEmpty()
				|| form.getQuantidade() == null | form.getQuantidade().isEmpty()) {
			throw new Exception("Campos inválidos");
		}

		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			Produto produto = form.atualizar(id, produtoRepository);
			return ResponseEntity.ok(new ProdutoDto(produto));
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {

		Optional<Produto> optional = produtoRepository.findById(id);
		if (optional.isPresent()) {
			produtoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}

}
