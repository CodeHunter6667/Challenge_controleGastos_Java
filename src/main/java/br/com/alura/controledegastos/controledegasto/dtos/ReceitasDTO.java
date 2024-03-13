package br.com.alura.controledegastos.controledegasto.dtos;

import java.time.LocalDate;

import br.com.alura.controledegastos.controledegasto.models.Receitas;

public record ReceitasDTO(Long id, String descricao, Double valor, LocalDate data) {
    public ReceitasDTO(Receitas entity) {
        this(entity.getId(), entity.getDescricao(), entity.getValor(), entity.getData());
    }
}
