package br.edu.ifg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifg.entities.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}