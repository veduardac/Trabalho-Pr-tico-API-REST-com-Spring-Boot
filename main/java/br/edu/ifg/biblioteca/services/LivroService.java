package br.edu.ifg.services;

import java.time.Year;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifg.entities.Livro;
import br.edu.ifg.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRep;

	public Livro insert(Livro l) {
		validarRegrasNegocio(l);
		return livroRep.save(l);
	}

	public void delete(Long id) {
		livroRep.deleteById(id);
	}

	public List<Livro> findAll() {
		return livroRep.findAll();
	}

	public Livro findById(Long id) {
		return livroRep.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public List<Livro> findByAutor(Long autorId) {
		return livroRep.findByAutorId(autorId);
	}

	public Livro update(Long id, Livro livroAlt) {
		validarRegrasNegocio(livroAlt);
		return livroRep.findById(id).map(livroDB -> {
			livroDB.setTitulo(livroAlt.getTitulo());
			livroDB.setIsbn(livroAlt.getIsbn());
			livroDB.setAnoPublicacao(livroAlt.getAnoPublicacao());
			livroDB.setNumeroPaginas(livroAlt.getNumeroPaginas());
			livroDB.setAutor(livroAlt.getAutor());
			return livroRep.save(livroDB);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	private void validarRegrasNegocio(Livro l) {
		int anoAtual = Year.now().getValue();
		if (l.getAnoPublicacao() != null && l.getAnoPublicacao() > anoAtual) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ano de publicacao nao pode ser maior que o ano atual.");
		}
		if (l.getNumeroPaginas() != null && l.getNumeroPaginas() <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Numero de paginas deve ser maior que zero.");
		}
	}
}