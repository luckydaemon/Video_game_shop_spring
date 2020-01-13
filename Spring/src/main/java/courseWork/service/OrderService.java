package courseWork.service;

import courseWork.logic.Order;
import courseWork.logic.order_product;
import courseWork.storage.OrderProductRepo;
import courseWork.storage.OrderRepo;
import courseWork.utils.Order_status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private OrderRepo orderRepository;
    private OrderProductRepo orderProductRepo;
    @Autowired
    public OrderService(OrderRepo orderRepository,OrderProductRepo orderProductRepo) {
        this.orderRepository = orderRepository;
        this.orderProductRepo =  orderProductRepo;
    }

    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }
    public Order getLastOrder() {
        return this.orderRepository.findLastOrder();
    }
    public Iterable<Order> getOrdersByClientId(int id) {
        return this.orderRepository.findOrdersByClient(id);
    }
    public Optional<Order> getOrderbyId(int id) {
        return this.orderRepository.findByOrderId(id);
    }
    public void createOrder(Integer order_id, Integer client_id, List<Integer> products,float cost) {
        this.orderRepository.putNewOrder(order_id,client_id, Order_status.AwaitConfirmationOfProductExistion.toString(),cost);
        for (int i = 0; i < products.size(); i++) {
            this.orderProductRepo.putNewOrder(order_id,products.get(i));
        }

    }
}