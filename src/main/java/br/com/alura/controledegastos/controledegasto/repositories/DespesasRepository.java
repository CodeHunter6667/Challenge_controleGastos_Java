package br.com.alura.controledegastos.controledegasto.repositories;

import br.com.alura.controledegastos.controledegasto.models.Despesas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long>{

    @Query("SELECT obj FROM Despesas obj WHERE EXTRACT(MONTH FROM obj.data) = :mes " +
            "AND EXTRACT(YEAR FROM obj.data) = :ano")
    List<Despesas> searchByDate(Double mes, Double ano);
}
