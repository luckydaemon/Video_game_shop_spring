package courseWork.storage;

import courseWork.logic.OrderProductPK;
import courseWork.logic.order_product;
import courseWork.utils.Order_status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface OrderProductRepo extends JpaRepository<order_product, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO order_products (order_id,product_id) VALUES (:order_id,:product_id)",  nativeQuery = true)
    void putNewOrder(@Param("order_id") Integer order_id, @Param("product_id") Integer product_id);
}
