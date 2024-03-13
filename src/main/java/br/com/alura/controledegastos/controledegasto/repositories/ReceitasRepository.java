package br.com.alura.controledegastos.controledegasto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.controledegastos.controledegasto.models.Receitas;

@Repository
public interface ReceitasRepository extends JpaRepository<Receitas, Long>{
}
