/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseWork.logic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User
{   @Column(name = "first_name")
    protected String FirstName;
    @Column(name = "Surname")
    protected String SurName;
    @Id
    @Column(name = "id")
    @GeneratedValue
    protected int id;
    @Column(name = "password")
    protected String password;
    @Column(name = "Email")
    protected String Email;
   public User() {

    }
    public User (String FirstName, String SurName, int id, String password, String Email)
            {
                this.FirstName = FirstName;
                this.SurName = SurName;
                this.id=id;
                this.password=password;
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
    public String getEmail()
    {
        return Email;
    }
    public void setEmail(String Email)
    {
        this.Email = Email;
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
}
