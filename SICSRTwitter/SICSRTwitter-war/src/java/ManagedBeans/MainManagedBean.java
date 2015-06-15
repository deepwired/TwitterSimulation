package ManagedBeans;

import Entities.Tweet;
import SessionBeans.HomeSessionBean;
import SessionBeans.LoginSessionBean;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MainManagedBean {

    @EJB
    private HomeSessionBean homeSessionBean;
    @EJB
    private LoginSessionBean loginSessionBean;
    /**
     * Creates a new instance of LoginManagedBean
     */

    /**
     * Creates a new instance of MainManagedBean
     */
    private String userName;
    private String password;
    private int userid;
    private List<Tweet> tabledata;
    private int getID;
    private StatusManagedBean sbm = new StatusManagedBean();

    public int getGetID() {
        return getID;
    }

    public void setGetID(int getID) {
        this.getID = getID;
    }

    public List<Tweet> getTabledata() {
        return tabledata;
    }

    public void setTabledata(List<Tweet> Tabledata) {
        this.tabledata = Tabledata;
    }

    public MainManagedBean() 
    {
        
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String submitClick() {
        return "case1";
    }

    public String registerClick() {
        return "case1";
    }

    public String onClickSubmit() {
        System.out.println("#### Entered MainManagedBean.onClickSubmit()");

        if ((userid=loginSessionBean.ValidateUser(userName, password))!=0) {
            generateHomeTweetList();
            return "case1";
        } else {
            System.out.println("###### Entered Else, changing value for status Message");
            sbm.setStatusMessage("Sorry, Invalid Credentials !! please try again");
            System.out.println("###### value of status message" + sbm.getStatusMessage());
        }
        return "";
    }
    
    @PostConstruct
    public void generateHomeTweetList() {

        System.out.println("#### Entered MainManagedBean.generateHomeTweetList()");
        tabledata = homeSessionBean.GetTweetInfo();
    }
    
    public void onClickRetweet(String tweet)
    {
        System.out.println("#### Entered MainManagedBean onClickRetweet :"+tweet);
        userid=loginSessionBean.ValidateUser(userName, password);
        homeSessionBean.postTweet(userid, tweet);
    }
    
    
    //  System.out.println("###### Last Tweet Recorded in TweetInfo :=:"+temp.tweetid+": which is :"+temp.tweet+": at :"+temp.time);
}
