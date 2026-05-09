package tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tacos.model.TacoOrder;


public interface OrderRepository
        extends JpaRepository<TacoOrder, Long> {
}
