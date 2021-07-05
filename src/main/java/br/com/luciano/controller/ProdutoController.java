package br.com.luciano.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luciano.model.Produto;
import br.com.luciano.repository.ProdutoRepository;


@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository; 
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Produto> buscarProduto(@PathVariable(value = "id") Long id){
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(!produto.isPresent()) {
			
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Produto>> buscarProdutos(){
		
		List<Produto> produtos = (List<Produto>) produtoRepository.findAll();
		
		if(produtos.isEmpty()) {
			
			return new ResponseEntity<List<Produto>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Produto>> criarProduto(@RequestBody Produto produto){
		
		produtoRepository.save(produto);
		
		List<Produto> produtos = (List<Produto>) produtoRepository.findAll();
		
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> deletarProduto(@PathVariable(value = "id") Long id){
		
		if(!produtoRepository.existsById(id)) {
			
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Produto>(HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Produto> alterarUsuario(@RequestBody Produto produto){
		
		produtoRepository.save(produto);
				
		return new ResponseEntity<Produto>(produto ,HttpStatus.CREATED);
	}
	
}
