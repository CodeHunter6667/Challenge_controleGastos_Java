package br.com.alura.controledegastos.controledegasto.services;

import br.com.alura.controledegastos.controledegasto.dtos.ResumoDTO;
import br.com.alura.controledegastos.controledegasto.models.Despesas;
import br.com.alura.controledegastos.controledegasto.models.Receitas;
import br.com.alura.controledegastos.controledegasto.repositories.CategoriasRepository;
import br.com.alura.controledegastos.controledegasto.repositories.DespesasRepository;
import br.com.alura.controledegastos.controledegasto.repositories.ReceitasRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResumoService {

    @Autowired
    private DespesasRepository despesasRepository;
    @Autowired
    private ReceitasRepository receitasRepository;
    @Autowired
    private CategoriasRepository categoriasRepository;

    @Transactional(readOnly = true)
    public ResumoDTO resumoDoMes(Double mes, Double ano){
        Double totalReceitas = 0.0;
        Double totalDespesas = 0.0;
        Gson gson = new Gson();

        List<Receitas> receitas = receitasRepository.searchByDate(mes, ano);
        List<Despesas> despesas = despesasRepository.searchByDate(mes, ano);
        List<String> categorias = categoriasRepository.searchAmount(mes, ano);

        for(Receitas obj : receitas){
            totalReceitas += obj.getValor();
        }
        for(Despesas obj : despesas){
            totalDespesas += obj.getValor();
        }

        Double saldoFinal = totalReceitas - totalDespesas;

        String categoriasJson = gson.toJson(categorias);

        return new ResumoDTO(totalReceitas, totalDespesas, saldoFinal, categoriasJson);
    }
}
