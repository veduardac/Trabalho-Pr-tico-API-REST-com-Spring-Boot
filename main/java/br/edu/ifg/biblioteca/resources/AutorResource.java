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

import br.edu.ifg.entities.Autor;
import br.edu.ifg.services.AutorService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/autores")
public class AutorResource {

	@Autowired
	private AutorService servico;

	@GetMapping
	public ResponseEntity<List<Autor>> findAll() {
		List<Autor> autores = servico.findAll();
		return ResponseEntity.ok().body(autores);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Autor> findById(@PathVariable Long id) {
		Autor autor = servico.findById(id);
		return ResponseEntity.ok().body(autor);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Autor> insert(@RequestBody Autor a) {
		a = servico.insert(a);
		return ResponseEntity.ok().body(a);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		servico.delete(id);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Autor> update(@PathVariable Long id, @RequestBody Autor a) {
		a = servico.update(id, a);
		return ResponseEntity.ok().body(a);
	}
}