package br.com.alura.controledegastos.controledegasto.controllers;

import br.com.alura.controledegastos.controledegasto.dtos.CategoriasDTO;
import br.com.alura.controledegastos.controledegasto.services.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService service;

    @GetMapping
    public ResponseEntity<List<CategoriasDTO>> getAll(){
        List<CategoriasDTO> dto = service.getAll();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriasDTO> getById(@PathVariable Long id){
        CategoriasDTO dto = service.getById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CategoriasDTO> post(@RequestBody CategoriasDTO dto){
        CategoriasDTO categoria = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(categoria.id()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }
}
