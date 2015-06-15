/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBeans;

import Entities.Relation;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author deep
 */
@Stateless
@LocalBean
public class RelationSessionBean {
    @PersistenceContext(unitName = "SICSRTwitter-ejbPU")
    private EntityManager em;

    public List<Relation> getFollowers(int userid)
    {
        List<Relation> t = em.createNamedQuery("Relation.findByReceiverId").setParameter("receiverId",userid).getResultList();
        return t;
    }
    public List<Relation> getFollowing(int userid)
    {       
        List<Relation> t = em.createNamedQuery("Relation.findBySenderId").setParameter("senderId",userid).getResultList();
        return t;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void removeFriend(int userid, int removeId) {
        
        System.out.println("### Removing using persistance!!");
        Relation t = (Relation)em.createNamedQuery("Relation.findOneSenderIdReceiver").setParameter("senderId",userid).setParameter("receiverId",removeId).getSingleResult();
        em.remove(t);
        System.out.println("****Success fulle removed using persistance!!");
    }
    
    public void persist(Object object) {
        em.persist(object);
    }

}
