package br.com.alura.controledegastos.controledegasto.repositories;

import br.com.alura.controledegastos.controledegasto.models.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
}
