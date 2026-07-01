package br.com.estacionamento.vagas.consumer;
import br.com.estacionamento.vagas.model.Vaga;
import br.com.estacionamento.vagas.repository.VagaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component public class SensorConsumer{
    private final VagaRepository repo;
    public SensorConsumer(VagaRepository repo){
        this.repo=repo;} @RabbitListener(queues="sensor.vagas")
    public void receber(Map<String,Object> evento){
        Long id=Long.valueOf(evento.get("vagaId").toString());
        boolean ocupada=Boolean.parseBoolean(evento.get("ocupada").toString());
        Vaga v=repo.findById(id).orElseThrow(); v.ocupada=ocupada; if(ocupada) v.reservada=false; repo.save(v);
    }
}
