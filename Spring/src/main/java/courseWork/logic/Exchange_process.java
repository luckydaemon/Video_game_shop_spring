/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseWork.logic;
import courseWork.utils.Exchange_Status;

public class Exchange_process {
    
    int id;
    Exchange_Status Status;
    float cost;
    boolean product_working;
    String category;
    public Exchange_process (int id)
        {
            this.id = id;
            this.cost = 0;
            this.Status = Exchange_Status.ProductRecevied;
            this.product_working= false;
            this.category = " ";
        }
    public void SetStatus(Exchange_Status status)
        {
            this.Status = status;
        }
    public void SetWorkingStatus(boolean status)
        {
            this.product_working = status;
        }
  
    public boolean getWorking()
        {
            return this.product_working;
        }
     public int getId()
        {
            return this.id;
        } 
      public Exchange_Status getStatus()
        {
            return this.Status;
        } 
       public float getCost()
        {
            return this.cost;
        } 
       public String getCategory()
        {
            return this.category;
        } 
        public void SetCost(float cost)
        {
            this.cost = cost;
        } 
         public void SetCategory(String category)
        {
            this.category = category;
        } 
       
}
