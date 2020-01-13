package courseWork.service;

import courseWork.storage.OrderProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderProductService{

    private OrderProductRepo orderProductRepository;

    public OrderProductService(OrderProductRepo orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }
}
