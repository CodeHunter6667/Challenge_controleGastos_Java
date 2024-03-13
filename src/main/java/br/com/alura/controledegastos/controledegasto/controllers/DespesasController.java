package br.com.alura.controledegastos.controledegasto.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alura.controledegastos.controledegasto.dtos.DespesasDTO;
import br.com.alura.controledegastos.controledegasto.services.DespesasService;

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

    @PostMapping
    public ResponseEntity<DespesasDTO> post(@RequestBody DespesasDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    @PutMapping
    public ResponseEntity<DespesasDTO> put(@PathVariable Long id, @RequestBody DespesasDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
