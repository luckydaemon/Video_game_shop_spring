/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseWork.logic;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "shop")
public class Product {

    @Column(name = "product_name")
    private String Product_Name;
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;
    @Column(name = "price")
    private float price;
    @Column(name = "category")
    private String category;
    @Column(name = "quantity")
    private int quantity;
    public Product (){}
    public Product (String Name,String category, int id, float price,int quantity)
        {
            this.Product_Name = Name;
            this.id = id;
            this.price = price;
            this.category = category;
            this.quantity = quantity;
        }
            
    public String getProductName()
        {
            return Product_Name;
        }
    public void setProductName(String Product_Name)
        {
            this.Product_Name = Product_Name;
        }
    public int getProductID()
        {
            return id;
        }
    public void setProductID(int id)
        {
            this.id = id;
        }
    public float getProductPrice()
        {
            return price;
        }
    public void setProductPrice(float price)
        {
            this.price = price;
        }
    public int getProductQuantity()
        {
            return quantity;
        }
    public void setProductQuantity(int quantity)
        {
            this.quantity = quantity;
        }
    public String getCategory()
        {
            return category;
        }
    public void setCategory(String category)
        {
            this.category = category;
        }
    
}
