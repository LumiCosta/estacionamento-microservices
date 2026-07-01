package br.com.estacionamento.pagamentos.repository;
import br.com.estacionamento.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PagamentoRepository extends JpaRepository<Pagamento,Long>{}
