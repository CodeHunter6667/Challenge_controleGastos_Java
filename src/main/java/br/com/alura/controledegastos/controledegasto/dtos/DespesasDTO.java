package br.com.alura.controledegastos.controledegasto.dtos;

import java.time.LocalDate;

import br.com.alura.controledegastos.controledegasto.models.Despesas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record DespesasDTO(
        Long id,
        @NotBlank(message = "Descricao é obrigatória")
        @Size(min = 5, max = 20, message = "Descricao deve conter entre 5 e 20 caracteres")
        String descricao,
        @Positive(message = "Valor não pode ser negativo ou zero")
        @NotBlank(message = "Valor é obrigatório")
        Double valor,
        @NotBlank(message = "Data é obrigatória")
        LocalDate data) {
    public DespesasDTO(Despesas entity) {
        this(entity.getId(), entity.getDescricao(), entity.getValor(), entity.getData());
    }   
}
