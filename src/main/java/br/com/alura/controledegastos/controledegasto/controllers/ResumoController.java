package br.com.alura.controledegastos.controledegasto.controllers;

import br.com.alura.controledegastos.controledegasto.dtos.ResumoDTO;
import br.com.alura.controledegastos.controledegasto.services.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resumo")
public class ResumoController {

    @Autowired
    private ResumoService service;

    @GetMapping(value = "/{ano}/{mes}")
    public ResponseEntity<ResumoDTO> getResumo(@PathVariable Double mes,@PathVariable Double ano){
        var dto = service.resumoDoMes(mes, ano);
        return ResponseEntity.ok().body(dto);
    }
}
