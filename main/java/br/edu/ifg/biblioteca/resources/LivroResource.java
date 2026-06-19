package br.edu.ifg.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifg.entities.Livro;
import br.edu.ifg.services.LivroService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/livros")
public class LivroResource {

	@Autowired
	private LivroService servico;

	@GetMapping
	public ResponseEntity<List<Livro>> findAll() {
		List<Livro> livros = servico.findAll();
		return ResponseEntity.ok().body(livros);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id) {
		Livro livro = servico.findById(id);
		return ResponseEntity.ok().body(livro);
	}

	// Endpoint customizado exigido no PDF da tarefa
	@GetMapping(value = "/autor/{autorId}")
	public ResponseEntity<List<Livro>> findByAutor(@PathVariable Long autorId) {
		List<Livro> livros = servico.findByAutor(autorId);
		return ResponseEntity.ok().body(livros);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Livro> insert(@RequestBody Livro l) {
		l = servico.insert(l);
		return ResponseEntity.ok().body(l);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		servico.delete(id);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody Livro l) {
		l = servico.update(id, l);
		return ResponseEntity.ok().body(l);
	}
}