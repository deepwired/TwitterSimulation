/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entities.Tweet;
import Entities.User;
import Entities.Relation;
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
public class UserSessionBean {
    @PersistenceContext(unitName = "SICSRTwitter-ejbPU")
    private EntityManager em;

    
    
    public List<Tweet> GetTweetInfo(int userid) {
        System.out.println("#### HomeSessionBean.GetTweetInfo ---List is being generated");
        User temp=new User();
        temp.setUserId(userid);
        List<Tweet> t = em.createNamedQuery("Tweet.findByUserId").setParameter("userId",temp).getResultList();
        return t;
    }
    
    
    public List<Tweet> GetFriendTweetInfo(int userid) {
        System.out.println("#### HomeSessionBean.GetTweetInfo ---List is being generated");        
        Query query = em.createQuery("SELECT t FROM Tweet t WHERE t.userId.userId in (SELECT r.receiverId FROM Relation r WHERE r.senderId=:userId)");              
        User temp=new User();
        temp.setUserId(userid);
        List<Tweet> t2=query.setParameter("userId",temp).getResultList();      
        return t2;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }
}
