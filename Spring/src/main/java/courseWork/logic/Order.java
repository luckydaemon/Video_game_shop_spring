/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseWork.logic;
import java.util.ArrayList;
import java.util.List;

import courseWork.utils.Order_status;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;


@Entity
@Table(name = "orders", schema = "shop")
public class Order 
{
    @Id
    @Column(name = "order_id")
    @GeneratedValue
    private int id;
    @Column(name = "client_id")
    private int client_id;
    @OneToMany(mappedBy = "pk.order",cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<order_product> Product = new ArrayList<order_product>();
    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private Order_status Status;
    private double cost;
    public Order (){}
    public Order (int id,int client_id, String status, List<order_product> list){
        this.client_id=client_id;
        this.id=id;
        this.Product = list;
        this.Status = Order_status.valueOf(status);
    }
    public Order (int id, int client_id, String status, List<order_product> list, float Cost){
        this.client_id=client_id;
        this.id=id;
        this.Product = list;
        this.Status = Order_status.valueOf(status);
        this.cost =Cost;
    }
    public Order (int id, List<order_product> list, int client_id)
        {
            this.client_id=client_id;
            this.id=id;
            this.Product = list;
            this.cost =  TotalOrderPrice();
            this.Status = Order_status.AwaitConfirmationOfProductExistion;
        }

    public int getOrderId()
        {
            return id;
        }
    public int getClientId()
    {
        return client_id;
    }

    public void setStatus(Order_status status)
{
    Status = status;
}
    public Order_status getStatus()
    {
        return this.Status;
    }
    public void setcost(double cost)
    {
        this.cost = cost;
    }
    public double getcost()
    {
        return this.cost;
    }
    @OneToMany(mappedBy = "pk.order_id", fetch = FetchType.LAZY)
    public  List<order_product> getOrdersProducts() {
        return Product;
    }
    @Transient
    public float TotalOrderPrice() {
        float sum = 0;
        List<order_product> orderProducts =  getOrdersProducts();
        for (order_product op : orderProducts) {
            sum += op.PriceOfOrder();
        }
        return sum;
    }

}
