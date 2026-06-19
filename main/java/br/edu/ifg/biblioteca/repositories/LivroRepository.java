package br.edu.ifg.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifg.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	// Método de busca customizada para atender o endpoint /livros/autor/{autorId}
	List<Livro> findByAutorId(Long autorId);
}