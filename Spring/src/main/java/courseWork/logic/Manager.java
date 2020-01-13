/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseWork.logic;



import courseWork.utils.Order_status;
import courseWork.utils.Exchange_Status;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "managers", schema = "shop")
public class Manager extends User {
    
    public Manager(String FirstName, String SurName,int id,String pass,String Email)
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
    
    public void confirm_order (Order order)
        {   if (Order_status.AwaitConfirmationFromCustomer == order.getStatus())
                order.setStatus(Order_status.AwaitMoneyTransfer);
        }
    public void give_away_order (Order order)
        {  if  (Order_status.AwaitMoneyTransfer == order.getStatus())
                order.setStatus(Order_status.ProductGivenAway);
        }
    public void close_order (Order order)
        {   if  (Order_status.ProductGivenAway == order.getStatus())
                order.setStatus(Order_status.Closed);
        }

    public void CloseExchange (Exchange_process exchange)
        {   if (Exchange_Status.AwaitGameExchange == exchange.getStatus())
                exchange.SetStatus(Exchange_Status.Closed);
        }
}
