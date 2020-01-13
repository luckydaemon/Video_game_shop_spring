/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseWork.logic;

import javax.persistence.*;

@Entity
@Table(name = "clients", schema = "shop")
public class Client extends User 
{
    @Column(name = "telenumber")
    protected String TeleNumber;
    public Client() {}
    public Client (String FirstName, String SurName,int id, String TeleNumber, String Email, String password)
           {    
              super(FirstName,SurName,id,password, Email);
              this.TeleNumber = TeleNumber;
           }
    public String getTNumber()
            {
                return TeleNumber;
            }
    public void setTNumber(String TNumber)
            {
                this.TeleNumber = TNumber;
            }

}
