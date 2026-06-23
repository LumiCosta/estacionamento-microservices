package br.com.estacionamento.ocupacao.controller;
import br.com.estacionamento.ocupacao.repository.MetricaOcupacaoRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/ocupacao")
public class OcupacaoController{
    private final MetricaOcupacaoRepository repo;
    public OcupacaoController(MetricaOcupacaoRepository repo){
        this.repo=repo;
    }
    @GetMapping("/resumo")
    public Map<String,Object> resumo(){
        long total=repo.count();
        long ocupadas=repo.countByOcupadaTrue();
        return Map.of("eventos",total,"leiturasOcupadas",ocupadas,"taxaOcupacaoEventos",total==0?0:(ocupadas*100.0/total));
    }
}
