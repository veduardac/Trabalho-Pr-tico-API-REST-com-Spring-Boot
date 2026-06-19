package br.edu.ifg.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifg.entities.Autor;
import br.edu.ifg.repositories.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRep;

	public Autor insert(Autor a) {
		return autorRep.save(a);
	}

	public void delete(Long id) {
		autorRep.deleteById(id);
	}

	public List<Autor> findAll() {
		return autorRep.findAll();
	}

	public Autor findById(Long id) {
		return autorRep.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public Autor update(Long id, Autor autorAlt) {
		return autorRep.findById(id).map(autorDB -> {
			autorDB.setNome(autorAlt.getNome());
			autorDB.setNacionalidade(autorAlt.getNacionalidade());
			autorDB.setDataNascimento(autorAlt.getDataNascimento());
			return autorRep.save(autorDB);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}