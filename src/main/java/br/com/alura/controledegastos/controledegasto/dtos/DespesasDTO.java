package br.com.alura.controledegastos.controledegasto.dtos;

import java.time.LocalDate;

import br.com.alura.controledegastos.controledegasto.models.Despesas;

public record DespesasDTO(Long id, String descricao, Double valor, LocalDate data) {
    public DespesasDTO(Despesas entity) {
        this(entity.getId(), entity.getDescricao(), entity.getValor(), entity.getData());
    }   
}
