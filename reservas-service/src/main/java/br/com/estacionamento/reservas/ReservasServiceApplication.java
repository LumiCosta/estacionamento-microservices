package br.com.estacionamento.reservas;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableFeignClients
@SpringBootApplication
public class ReservasServiceApplication{
    public static void main(String[] args){SpringApplication.run(ReservasServiceApplication.class,args);
}
}
