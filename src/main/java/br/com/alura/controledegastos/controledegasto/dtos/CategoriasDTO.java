package br.com.alura.controledegastos.controledegasto.dtos;

import br.com.alura.controledegastos.controledegasto.models.Categorias;

public record CategoriasDTO(Long id, String nome) {
    public CategoriasDTO(Categorias entity){
        this(entity.getId(), entity.getNomeCategoria());
    }
}
