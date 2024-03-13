package br.com.alura.controledegastos.controledegasto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.controledegastos.controledegasto.dtos.ReceitasDTO;
import br.com.alura.controledegastos.controledegasto.models.Receitas;
import br.com.alura.controledegastos.controledegasto.repositories.ReceitasRepository;
import br.com.alura.controledegastos.controledegasto.services.exceptions.ReceitaNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository repository;

    @Transactional(readOnly = true)
    public List<ReceitasDTO> getAll() {
        List<Receitas> receitas = repository.findAll();
        return receitas.stream().map(x -> new ReceitasDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public ReceitasDTO getById(Long id) {
        Receitas receita = repository.findById(id).orElseThrow(
                () -> new ReceitaNotFoundException("Receita não encontrada"));
        return new ReceitasDTO(receita);
    }

    @Transactional
    public ReceitasDTO insert(ReceitasDTO dto) {
        var receita = new Receitas();
        CopyDtoToEntity(dto, receita);
        repository.save(receita);
        return new ReceitasDTO(receita);
    }

    @Transactional
    public ReceitasDTO update(Long id, ReceitasDTO dto) {
        try{
            var receita = repository.getReferenceById(id);
            CopyDtoToEntity(dto, receita);
            repository.save(receita);
            return new ReceitasDTO(receita);
        } catch (EntityNotFoundException e) {
            throw new ReceitaNotFoundException("Receita não encontrada");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ReceitaNotFoundException("Receita não encontrada");
        }
        repository.deleteById(id);
    }

    private static void CopyDtoToEntity(ReceitasDTO dto, Receitas entity) {
        entity.setId(dto.id());
        entity.setDescricao(dto.descricao());
        entity.setData(dto.data());
        entity.setValor(dto.valor());
    }
}
