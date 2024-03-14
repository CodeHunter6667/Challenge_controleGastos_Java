package br.com.alura.controledegastos.controledegasto.dtos;

import java.time.LocalDate;

import br.com.alura.controledegastos.controledegasto.models.Receitas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ReceitasDTO(
        Long id,
        @NotBlank(message = "Descricao é obrigatória")
        @Size(min = 5, max = 20, message = "Descricao deve ter entre 5 e 20 caracteres")
        String descricao,
        @Positive(message = "Valor não pode ser zero ou negativo")
        @NotNull(message = "Valor é obrigatório")
        Double valor,
        @NotNull(message = "Data é obrigatória")
        LocalDate data) {
    public ReceitasDTO(Receitas entity) {
        this(entity.getId(), entity.getDescricao(), entity.getValor(), entity.getData());
    }
}
