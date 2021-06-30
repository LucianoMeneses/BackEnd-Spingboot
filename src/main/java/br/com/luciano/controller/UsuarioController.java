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

import br.com.luciano.model.Usuario;
import br.com.luciano.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable(value = "id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isEmpty()) {

			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);

	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> BuscarUsuarios() {

		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();

		if (usuarios.isEmpty()) {

			return new ResponseEntity<List<Usuario>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);

	}

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> CriarUsuario(@RequestBody Usuario usuario) {

		usuarioRepository.save(usuario);

		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {

		usuarioRepository.deleteById(id);

		return new ResponseEntity<String>("Usuário deletado com sucesso", HttpStatus.OK);
	}

	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> AlterarUsuario(@RequestBody Usuario usuario) {

		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}

}
