package br.com.alura.controledegastos.controledegasto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.controledegastos.controledegasto.models.Despesas;

@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long>{
}
