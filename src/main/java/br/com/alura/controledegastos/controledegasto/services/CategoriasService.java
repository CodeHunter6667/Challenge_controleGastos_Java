package br.com.alura.controledegastos.controledegasto.services;

import br.com.alura.controledegastos.controledegasto.dtos.CategoriasDTO;
import br.com.alura.controledegastos.controledegasto.models.Categorias;
import br.com.alura.controledegastos.controledegasto.repositories.CategoriasRepository;
import br.com.alura.controledegastos.controledegasto.services.exceptions.CategoriaNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository repository;

    @Transactional(readOnly = true)
    public List<CategoriasDTO> getAll(){
        var categorias = repository.findAll();
        return categorias.stream().map(CategoriasDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoriasDTO getById(Long id){
        var categoria = repository.findById(id).orElseThrow(
                () -> new CategoriaNotFoundException("Categoria não encontrada")
        );
        return new CategoriasDTO(categoria);
    }

    @Transactional
    public CategoriasDTO insert(CategoriasDTO dto){
        Categorias categoria = new Categorias();
        copyDtoToEntity(dto, categoria);
        repository.save(categoria);
        return new CategoriasDTO(categoria);
    }

    private static void copyDtoToEntity(CategoriasDTO dto, Categorias entity){
        entity.setId(dto.id());
        entity.setNomeCategoria(dto.nome());
    }
}
