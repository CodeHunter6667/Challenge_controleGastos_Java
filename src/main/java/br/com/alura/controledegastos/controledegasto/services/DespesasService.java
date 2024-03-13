package br.com.alura.controledegastos.controledegasto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.controledegastos.controledegasto.dtos.DespesasDTO;
import br.com.alura.controledegastos.controledegasto.models.Despesas;
import br.com.alura.controledegastos.controledegasto.repositories.DespesasRepository;
import br.com.alura.controledegastos.controledegasto.services.exceptions.DespesaNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DespesasService {
    
    @Autowired
    private DespesasRepository repository;

    @Transactional(readOnly = true)
    public List<DespesasDTO> getAll() {
        List<Despesas> despesas = repository.findAll();
        return despesas.stream().map(x -> new DespesasDTO(x)).toList();
    }
    
    @Transactional(readOnly = true)
    public DespesasDTO getById(Long id) {
        var despesa = repository.findById(id).orElseThrow(
                () -> new DespesaNotFoundException("Despesa não encontrada"));
        return new DespesasDTO(despesa);
    }
    
    @Transactional
    public DespesasDTO insert(DespesasDTO dto) {
        var despesa = new Despesas();
        CopyDtoToEntity(dto, despesa);
        repository.save(despesa);
        return new DespesasDTO(despesa);
    }

    @Transactional
    public DespesasDTO update(Long id, DespesasDTO dto) {
        try {
            var despesa = repository.getReferenceById(id);
            CopyDtoToEntity(dto, despesa);
            repository.save(despesa);
            return new DespesasDTO(despesa);
        } catch (EntityNotFoundException e) {
            throw new DespesaNotFoundException("Despesa não encontrada");
        }
    }
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new DespesaNotFoundException("Despesa não encontrada");
        }
        repository.deleteById(id);
    }

    private static void CopyDtoToEntity(DespesasDTO dto, Despesas entity) {
        entity.setId(dto.id());
        entity.setDescricao(dto.descricao());
        entity.setData(dto.data());
        entity.setValor(dto.valor());
    }
}
