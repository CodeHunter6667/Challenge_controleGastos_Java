package br.com.alura.controledegastos.controledegasto.services;

import br.com.alura.controledegastos.controledegasto.dtos.ReceitasDTO;
import br.com.alura.controledegastos.controledegasto.models.Receitas;
import br.com.alura.controledegastos.controledegasto.repositories.ReceitasRepository;
import br.com.alura.controledegastos.controledegasto.services.exceptions.ReceitaNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReceitasService {

    @Autowired
    private ReceitasRepository repository;

    @Transactional(readOnly = true)
    public List<ReceitasDTO> getAll(String descricao) {
        List<Receitas> receitas = repository.searchByName(descricao);
        return receitas.stream().map(ReceitasDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ReceitasDTO getById(Long id) {
        Receitas receita = repository.findById(id).orElseThrow(
                () -> new ReceitaNotFoundException("Receita não encontrada"));
        return new ReceitasDTO(receita);
    }

    @Transactional(readOnly = true)
    public List<ReceitasDTO> searchByDate(Double mes, Double ano){
        List<Receitas> receitas = repository.searchByDate(mes, ano);
        return receitas.stream().map(ReceitasDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ReceitasDTO> getByName(String descricao){
        List<Receitas> receitas = repository.searchByName(descricao);
        return receitas.stream().map(ReceitasDTO::new).toList();
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
