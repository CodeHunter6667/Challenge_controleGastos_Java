package br.com.alura.controledegastos.controledegasto.repositories;

import br.com.alura.controledegastos.controledegasto.models.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitasRepository extends JpaRepository<Receitas, Long>{

    @Query("SELECT obj FROM Receitas obj WHERE EXTRACT(MONTH FROM obj.data) = :mes " +
            "AND EXTRACT(YEAR FROM obj.data) = :ano")
    List<Receitas> searchByDate(Double mes, Double ano);

    @Query("SELECT obj FROM Receitas obj WHERE UPPER(obj.nome) LIKE UPPER(:nome)")
    List<Receitas> searchByName(String nome);
}
