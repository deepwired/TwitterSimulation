
package SessionBeans;

import Entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class RegistrationSessionBean 
{
    @PersistenceContext(unitName = "SICSRTwitter-ejbPU")
    private EntityManager em;
    public void insertUserDetails(String userName,String emailId,String password)
    {
        System.out.println("#### Entered RegistrationSessionBean insertUserDetails");
        
        
        em.createNativeQuery(" insert into user values(DEFAULT,'"+userName+"','"+password+"','E','"+emailId+"','A',adddate(curdate(),INTERVAL 3650 DAY));").executeUpdate();
        System.out.println(" insert into user values(DEFAULT,'"+userName+"','"+password+"','E','"+emailId+"','A',adddate(curdate(),INTERVAL 3650 DAY));");
        System.out.println("****");
    }
    
    public int checkUserName(String newUserName,String emailid)
    {
        System.out.println("checkUserName() was called here:"+newUserName+":"+emailid);
        List<User> udata = em.createNamedQuery("User.findAll").getResultList();
        for (User udata1 : udata) {
            if (udata1.getUsername().equals(newUserName)) {
                System.out.println("Returning 1");
                return -1;
            }
            if (udata1.getEmailId().equals(emailid)) {
                System.out.println("Returning 2");
                return -2;
            }
        }
        System.out.println("Returning 0");
        return 0;
    }

    public void persist(Object object) {
        em.persist(object);
    }
 
}
