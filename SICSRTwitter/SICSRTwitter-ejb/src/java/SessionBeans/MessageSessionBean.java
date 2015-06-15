/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entities.DirectMessage;
import Entities.Tweet;
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
public class MessageSessionBean {
    @PersistenceContext(unitName = "SICSRTwitter-ejbPU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<User> getUserInfo(int userid)
    {
        System.out.println("#### MessageSessionBean.getUserInfo---User List is being generated");
        Query query=em.createQuery("SELECT u FROM User u WHERE u.userId in (SELECT r.receiverId FROM Relation r WHERE r.senderId=:userId)");
        User temp=new User();
        temp.setUserId(userid);
        List<User> t2=query.setParameter("userId",temp).getResultList();  
        return t2;
    }
    
    
    public List<DirectMessage> getUserMessage(int userid,int friendid) {
        System.out.println("#### HomeSessionBean.GetTweetInfo ---List is being generated");    
        User a=new User();
        a.setUserId(userid);
        User b=new User();
        b.setUserId(friendid);
        
        List<DirectMessage> m = em.createNamedQuery("DirectMessage.findMessagePerUser").setParameter("a",a).setParameter("b", b).getResultList();                 
        return m;
    }
    
    public String getUserName(int id)
    {
        String name="";
        User t = (User)em.createNamedQuery("User.findByUserId").setParameter("userId",id).getSingleResult();
        System.out.println("********This is the derived user Name"+id+":"+t.getUsername());
        return t.getUsername();
    }
    
    
    public void sendMessage(String messageText,int userid,int reid) {
       
        em.createNativeQuery("insert into direct_message values(DEFAULT,"+userid+","+reid+",'"+messageText+"',DEFAULT)").executeUpdate();
        System.out.println("***");
    }
    public void persist(Object object) {
        em.persist(object);
    }


}
