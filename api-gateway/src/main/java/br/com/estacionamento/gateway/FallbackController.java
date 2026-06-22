package br.com.estacionamento.gateway;
import org.springframework.web.bind.annotation.*;import java.util.Map;
@RestController public class FallbackController{@RequestMapping("/fallback/pagamentos") public Map<String,String> pagamentos(){return Map.of("status","indisponivel","mensagem","Serviço de pagamentos temporariamente indisponível. Tente novamente em instantes.");}}
