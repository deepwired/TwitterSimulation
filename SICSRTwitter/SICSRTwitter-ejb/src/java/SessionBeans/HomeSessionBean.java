/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entities.Relation;
import Entities.Tweet;
import Entities.User;
import java.sql.ResultSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

/**
 *
 * @author deep
 */
@Stateless
@LocalBean
public class HomeSessionBean {

    @PersistenceContext(unitName = "SICSRTwitter-ejbPU")
    private EntityManager em;
    private EntityTransaction et;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Tweet> GetTweetInfo() {
        System.out.println("#### HomeSessionBean.GetTweetInfo ---List is being generated");
        List<Tweet> t = em.createNamedQuery("Tweet.findAll").getResultList();
        return t;
    }

    public void postTweet(int userId, String tweet) {
        em.createNativeQuery("insert into tweet values(DEFAULT," + userId + ",'" + tweet + "',DEFAULT,0)").executeUpdate();
        System.out.println("****Success");
    }

    public boolean isFriend(int userid, int checkid) {
        List<Relation> t = em.createNamedQuery("Relation.findAll").getResultList();
        int temp;
        System.out.println("####Entered HomeSessionBean.isFriend(uid,cid):"+userid+":"+checkid);            
            
        for (int i = 0; i < t.size(); i++) {
            temp=t.get(i).getSenderId().getUserId();
            if (temp == userid) 
            {
                if(t.get(i).getReceiverId()==checkid)
                {    
                    return false;
                }
            }
        }
        return true;
    }
    
    public void addFriend(int userid,int addid)
    {
        System.out.println("### add Relation Query" +"insert into relation values(DEFAULT," + userid + "," + addid + ",'A'");
        em.createNativeQuery("insert into relation values(DEFAULT," + userid + "," + addid + ",'A')").executeUpdate();
        System.out.println("****Success");
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
