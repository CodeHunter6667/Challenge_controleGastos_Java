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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.alura.controledegastos.controledegasto.dtos.ReceitasDTO;
import br.com.alura.controledegastos.controledegasto.services.ReceitasService;

@RestController
@RequestMapping(value = "/receitas")
public class ReceitasController {
    
    @Autowired
    private ReceitasService service;

    @GetMapping
    public ResponseEntity<List<ReceitasDTO>> getAll() {
        var receitas = service.getAll();
        return ResponseEntity.ok().body(receitas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReceitasDTO> getById(@RequestParam Long id) {
        var receita = service.getById(id);
        return ResponseEntity.ok().body(receita);
    }

    @PostMapping
    public ResponseEntity<ReceitasDTO> post(@RequestBody ReceitasDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReceitasDTO> put(@PathVariable Long id, @RequestBody ReceitasDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
