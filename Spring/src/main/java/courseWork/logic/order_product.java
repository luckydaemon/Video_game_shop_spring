package courseWork.logic;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "order_products")
public class order_product implements Serializable {
    @EmbeddedId
    @JsonIgnore
    private OrderProductPK pk;

    public order_product(){
        super();
    }
    public order_product(Order order_id, Product product_id, int id)
    {

        this.pk = new OrderProductPK();
        pk.setOrder(order_id);
        pk.setProduct(product_id);
    }
    public OrderProductPK getPk() {
        return pk;
    }

    public void setPk(OrderProductPK pk) {
        this.pk = pk;
    }
    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }

    @Transient
    public float PriceOfOrder() {
        return getProduct().getProductPrice();
    }
}
