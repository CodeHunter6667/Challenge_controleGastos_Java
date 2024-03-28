package br.com.alura.controledegastos.controledegasto.controllers;

import br.com.alura.controledegastos.controledegasto.dtos.DespesasDTO;
import br.com.alura.controledegastos.controledegasto.services.DespesasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/despesas")
public class DespesasController {
    
    @Autowired
    private DespesasService service;

    @GetMapping
    public ResponseEntity<List<DespesasDTO>> getAll() {
        var despesas = service.getAll();
        return ResponseEntity.ok().body(despesas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DespesasDTO> getById(@PathVariable Long id) {
        var despesa = service.getById(id);
        return ResponseEntity.ok().body(despesa);
    }

    @GetMapping(value = "/{ano}/{mes}")
    public ResponseEntity<List<DespesasDTO>> getByDate(Double mes, Double ano){
        List<DespesasDTO> dto = service.searchByDate(mes, ano);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<DespesasDTO>> getByName(@RequestParam(name="nome", defaultValue = "") String nome){
        List<DespesasDTO> dto = service.getByName(nome);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<DespesasDTO> post(@Valid @RequestBody DespesasDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<DespesasDTO> put(@PathVariable Long id, @Valid @RequestBody DespesasDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
