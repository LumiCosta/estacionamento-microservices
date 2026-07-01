package br.com.estacionamento.reservas.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@FeignClient(name="vagas-service")
public interface VagasClient{
    @PatchMapping("/api/vagas/{id}/reservar")
    Map<String,Object> reservar(
            @PathVariable("id") Long id);
}
