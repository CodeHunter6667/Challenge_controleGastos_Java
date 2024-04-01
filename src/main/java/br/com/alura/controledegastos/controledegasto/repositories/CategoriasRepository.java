package br.com.alura.controledegastos.controledegasto.repositories;

import br.com.alura.controledegastos.controledegasto.models.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {

    @Query("SELECT categ.nomeCategoria, SUM(desp.valor) total_despesas FROM Categorias categ, Despesas desp " +
            "WHERE categ.id = desp.categoria.id " +
            "AND EXTRACT(MONTH FROM desp.data) = :mes " +
            "AND EXTRACT(YEAR FROM desp.data) = :ano " +
            "GROUP BY categ.nomeCategoria " +
            "ORDER BY total_despesas")
    List<String> searchAmount(Double mes, Double ano);
}
