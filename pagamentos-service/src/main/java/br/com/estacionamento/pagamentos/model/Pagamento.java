package br.com.estacionamento.pagamentos.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
public class Pagamento{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;
    public Long reservaId;
    public BigDecimal valor;
    public String status;
    public String transacaoGateway;
}
