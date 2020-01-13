/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseWork.logic;
import java.util.ArrayList;



public class Order_check {
    
    private int order_id;
    private float Cost;
    private ArrayList<Product> ProductList = new ArrayList<Product>();   
    public Order_check(int id)
        {
            this.order_id=id;
        }
    
}
