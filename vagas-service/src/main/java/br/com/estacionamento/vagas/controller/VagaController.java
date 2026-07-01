package br.com.estacionamento.vagas.controller;
import br.com.estacionamento.vagas.model.Vaga;
import br.com.estacionamento.vagas.repository.VagaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/vagas")
public class VagaController{
    private final VagaRepository repo;

    public VagaController(VagaRepository repo){
        this.repo=repo;
    }

    @GetMapping
    public List<Vaga> listar(){
        return repo.findAll();
    }

    @GetMapping("/disponiveis") public List<Vaga> disponiveis(){
        return repo.findByOcupadaFalseAndReservadaFalse();
    }

    @PostMapping public Vaga criar(@RequestBody Vaga vaga){
        return repo.save(vaga);
    }

    @PatchMapping("/{id}/reservar")
    public Vaga reservar(@PathVariable("id") Long id){
        Vaga v=repo.findById(id).orElseThrow();
        if(v.ocupada||v.reservada) throw new IllegalStateException("Vaga indisponível");
        v.reservada=true; return repo.save(v);
    }
}
