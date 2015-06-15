/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author deep
 */
@Stateless
@LocalBean
public class LoginSessionBean {
    @PersistenceContext(unitName = "SICSRTwitter-ejbPU")
    private EntityManager em;
    
    public int ValidateUser(String username,String password)
    {
        System.out.println("#### Entered LoginSessionBean.ValidateUser("+username+","+password+")");
        List<User> data=em.createNamedQuery("User.findAll").getResultList();
        System.out.println(data);
        for(int i=0;i<data.size();i++)
        {
            if(data.get(i).getUsername().equals(username))
            {
                if(data.get(i).getPassword().equals(password))
                {
                    if(data.get(i).getStatus().equals("A"))
                    {
                        System.out.println("Valid Credentials");
                        return data.get(i).getUserId();
                    }
                    else
                    {
                        return -1;
                    }
                }
            }
        }        
        System.out.println("Invalid Credentials");
        return 0;
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
