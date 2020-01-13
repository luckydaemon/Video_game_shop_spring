/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseWork.logic;

import courseWork.utils.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "admins", schema = "shop")
public class Administrator extends User 
{
    public Administrator(){}
    public Administrator (String FirstName, String SurName,int id, String pass, String Email)
        {
             super(FirstName,SurName,id,pass, Email);
        }

    public String getEmail()
    {
        return Email;
    }
    public void setEmail(String Email)
    {
        this.Email = Email;
    }
    public String getFirstName()
            {
                return FirstName;
            }
    public String getSurName()
            {
                return SurName;
            }
    public void setFirstName(String FirstName)
            {
                this.FirstName = FirstName;
            }
     public void setSurName(String SurName)
            {
                this.SurName = SurName;
            }
     public int getID ()
            {
                return id;
            }
    
    public  void setID (int id)
            {
               this.id=id;
            }
    public  String getPass ()
        {
            return password;
        }
    
    public  void setPass (String pass)
        {
            this.password=pass;
        }
    
    public void ConfirmExistanceOfProducts(Order order)
        {   if  (Order_status.AwaitConfirmationOfProductExistion== order.getStatus())
                order.setStatus(Order_status.AwaitConfirmationFromCustomer);
        }
    public void ExchangeProductTesting(Exchange_process exchange)
        { if (Exchange_Status.ProductRecevied == exchange.getStatus())
                exchange.SetStatus(Exchange_Status.ProductTesting);
        }
    public void ExchangeWorkingStatus(Exchange_process exchange, boolean status)
        {   if (Exchange_Status.ProductTesting == exchange.getStatus())                
                    exchange.SetWorkingStatus(status);
        }
    public void ExchangeCategory(Exchange_process exchange, String category)
        { if (exchange.getWorking())
            exchange.SetCategory(category);
        }
    public void ExchangeCost(Exchange_process exchange, float cost)
        {   if (exchange.getWorking())
             exchange.SetCost(cost);
        }
    public void SetAwaitStatus(Exchange_process exchange)
        { if ((Exchange_Status.ProductTesting == exchange.getStatus())&&(exchange.getWorking()== true))  
                exchange.SetStatus(Exchange_Status.AwaitGameExchange);
        }
    
}
