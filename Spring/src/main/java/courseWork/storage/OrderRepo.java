package courseWork.storage;


import courseWork.utils.Order_status;
import org.springframework.data.jpa.repository.JpaRepository;
import courseWork.logic.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT * FROM orders u where u.client_id = :client_id",  nativeQuery = true)
    List<Order> findOrdersByClient(@Param("client_id") Integer client_id);
    @Query(value = "SELECT * FROM orders ORDER BY order_id DESC LIMIT 1",  nativeQuery = true)
    Order findLastOrder();
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO orders (order_id, client_id, order_status, cost) VALUES (:order_id, :client_id, :order_status, :cost)",  nativeQuery = true)
    void putNewOrder(@Param("order_id") Integer order_id, @Param("client_id") int client_id, @Param("order_status") String order_status, @Param("cost") Float cost);
    @Query(value = "SELECT * FROM orders u where u.order_id = :order_id",  nativeQuery = true)
    Optional<Order> findByOrderId(@Param("order_id") Integer order_id);
}