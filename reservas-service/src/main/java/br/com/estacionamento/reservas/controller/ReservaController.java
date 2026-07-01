package br.com.estacionamento.reservas.controller;
import br.com.estacionamento.reservas.client.VagasClient;
import br.com.estacionamento.reservas.model.Reserva;
import br.com.estacionamento.reservas.repository.ReservaRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
@RestController
@RequestMapping("/api/reservas")
public class ReservaController{private final ReservaRepository repo;
    private final VagasClient vagas;
    public ReservaController(ReservaRepository repo,VagasClient vagas){this.repo=repo;this.vagas=vagas;}

    @GetMapping public List<Reserva> listar()
    {return repo.findAll();}

    @PostMapping
    public Reserva criar(@RequestBody Reserva r){
        vagas.reservar(r.vagaId);r.status="CONFIRMADA";if(r.inicio==null)r.inicio=LocalDateTime.now();if(r.fim==null)r.fim=r.inicio.plusHours(2);return repo.save(r);}
}
