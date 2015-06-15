/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;


import Entities.DirectMessage;
import Entities.Relation;
import Entities.Tweet;
import Entities.User;
import SessionBeans.HomeSessionBean;
import SessionBeans.LoginSessionBean;
import SessionBeans.MessageSessionBean;
import SessionBeans.RelationSessionBean;
import SessionBeans.UserSessionBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author deep
 */
@ManagedBean
@SessionScoped
public class Mainv2ManagedBean {

    @EJB
    private RelationSessionBean relationSessionBean;
    @EJB
    private MessageSessionBean messageSessionBean;
    @EJB
    private UserSessionBean userSessionBean;
    @EJB
    private LoginSessionBean loginSessionBean;
    @EJB
    private HomeSessionBean homeSessionBean;

    /**
     * Creates a new instance of Mainv2ManagedBean
     */
    /**
     * Creates a new instance of LoginManagedBean
     */

    /**
     * Creates a new instance of MainManagedBean
     */
    //For Login page
    private String userName;
    private String password;
    private int userid;
    private String loginStatus;

    //For Home page
    private List<Tweet> tabledata;
    private String postTweet;
    private String homeStatus;

    //For User Page
    private int viewFriendId; //inputted from Home Page onclick of Button
    private String viewFriendName;
    private List<Tweet> usertabledata;
    private String userStatus;

    //For Message Page
    private List<User> friendUserName;
    private String messageText;
    private String messageStatus;

    //For Relation Page
    private List<Relation> followerList;
    private List<Relation> followingList;
    private String relationStatus;

    public String getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(String relationStatus) {
        this.relationStatus = relationStatus;
    }
    
    public String getHomeStatus() {
        return homeStatus;
    }

    public void setHomeStatus(String homeStatus) {
        this.homeStatus = homeStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
        
    
    
    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public List<Relation> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(List<Relation> followerList) {
        this.followerList = followerList;
    }

    public List<Relation> getFollowingList() {
        return followingList;
    }

    public void setFollowingList(List<Relation> followingList) {
        this.followingList = followingList;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public List<User> getFriendUserName() {
        return friendUserName;
    }

    public void setFriendUserName(List<User> friendUserName) {
        this.friendUserName = friendUserName;
    }

    public Mainv2ManagedBean() {

    }

    public String getViewFriendName() {
        return viewFriendName;
    }

    public void setViewFriendName(String viewFriendName) {
        this.viewFriendName = viewFriendName;
    }

    public List<Tweet> getUsertabledata() {
        return usertabledata;
    }

    public void setUsertabledata(List<Tweet> usertabledata) {
        this.usertabledata = usertabledata;
    }

    public List<Tweet> getTabledata() {
        return tabledata;
    }

    public void setTabledata(List<Tweet> Tabledata) {
        this.tabledata = Tabledata;
    }

    public String getPostTweet() {
        return postTweet;
    }

    public void setPostTweet(String postTweet) {
        this.postTweet = postTweet;
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

    public int getViewFriendId() {
        return viewFriendId;
    }

    public void setViewFriendId(int viewFriendId) {
        this.viewFriendId = viewFriendId;
    }

    public String onClickSubmit(int i)
    {
        System.out.println("#### Entered MainManagedBean.onClickSubmit()");
        userid = loginSessionBean.ValidateUser(userName, password);
        loginStatus="";
        messageStatus = "";
        homeStatus = "";
        relationStatus="";
        userStatus="";
        if(i==1)
            loginStatus = "Sorry, Invalid Credentials !! please try again";
        
        
        if ( userid > 0) {
            generateHomeTweetList();
            return "case1";
        } else if(userid == 0) {
            System.out.println("###### Entered Else, changing value for status Message");
            System.out.println("###### value of status message" + loginStatus);
        }
        else if(userid == -1)
        {
            loginStatus = "Click the link sent to your emailid to verify your account";
        }
        return "";
    }

    @PostConstruct
    public void generateHomeTweetList() {
        System.out.println("#### Entered MainManagedBean.generateHomeTweetList()");
        tabledata = homeSessionBean.GetTweetInfo();
        usertabledata = userSessionBean.GetTweetInfo(viewFriendId);
        generateUserMessages();
        generateConnections();
    }

    public void generateUserMessages() {
        friendUserName = messageSessionBean.getUserInfo(userid);
    }

    public void onClickPostTweet(int i) 
    {
        loginStatus="";
        homeStatus="";
        userStatus="";
        relationStatus="";
        messageStatus="";
        
            if (postTweet.equals("")) 
            {
                if(i==2)
                homeStatus = "There is nothing in the tweet box, and no tweet has nothing";
                else if(i==3)
                userStatus =  "There is nothing in the tweet box, and no tweet has nothing";
            } 
            else
            {
                System.out.println("#### Entered MainManagedBean onClickPost :" + postTweet);
                homeSessionBean.postTweet(userid, postTweet);
                if(i==2)
                homeStatus = "Tweet has been Posted Successfully !!";
                else if(i==3)
                userStatus = "Tweet has been Posted Successfully !!";    
                postTweet = "";
                generateHomeTweetList();
            }
        }

    public void onClickRetweet(String tweet,int i) 
    {
        loginStatus="";
        homeStatus="";
        userStatus="";
        relationStatus="";
        messageStatus="";
        
        System.out.println("#### Entered MainManagedBean onClickRetweet :" + tweet);
        homeSessionBean.postTweet(userid, tweet);
        if(i==2)
        homeStatus = "Tweet has been re-Tweeted Successfully !!";
        else if(i==3)
        userStatus = "Tweet has been re-Tweeted Successfully !!";
        generateHomeTweetList();
    }

    public String onClickViewUser(int uid, String un) {
        viewFriendId = uid;
        viewFriendName = un;
        usertabledata = userSessionBean.GetTweetInfo(viewFriendId);
        System.out.println("#### Entered MainManagedBean.onClickUserName(int uid) is : " + uid);
        return "case2";
    }

    public String onClickProfile() {
        usertabledata = userSessionBean.GetTweetInfo(userid);
        viewFriendName = userName;
        viewFriendId = userid;
        return "user.xhtml";
    }

    public boolean isFollowing(int checkid) {
        System.out.println("#### Entered MainManagedBean.isFollowing(int checkid) is : " + checkid);
        if (checkid == userid) {
            return false;
        } else {
            return homeSessionBean.isFriend(userid, checkid);
        }
    }

    public boolean isUserFriendSame() {
        System.out.println("#### MainManagedBean Entered isUserFriendSame ,returning :" + (viewFriendId == userid));
        return viewFriendId == userid;
    }

    public void onClickFollow(int addId,int i) 
    {
        loginStatus="";
        homeStatus="";
        userStatus="";
        relationStatus="";
        messageStatus="";
        
        System.out.println("#### Entered MainManagedBean.onClickFollow(int addId) is : " + addId);
        homeSessionBean.addFriend(userid, addId);
        if(i==2)
        homeStatus = "Following Status established Successfully !!";
        else if(i==3)
        userStatus = "Following Status established Successfully !!";
    
        generateConnections();

    }

    public String onClickUnFollow(int removeId, boolean state,int i)
    {   
        loginStatus="";
        homeStatus="";
        userStatus="";
        relationStatus="";
        messageStatus="";
        
        System.out.println("#### Entered MainManagedBean.onClickFollow(int addId) is : " + removeId);
        
        if (state) {
            relationSessionBean.removeFriend(removeId, userid);
        } else {
            relationSessionBean.removeFriend(userid, removeId);
        }
        generateConnections();
        if(i==4)
        relationStatus = "removed Connection Successfully !!";
        return "relation";
    }

    public String onClickSeeConnections() {
        System.out.println("#### Entered MainManagedBean.onClickSeeConnections");
        generateConnections();
        return "case4";
    }

    public void onClickFriendTweets() {
        System.out.println("#### Entered MainManagedBean.onClickFriendTweets");
        usertabledata = userSessionBean.GetFriendTweetInfo(userid);

    }

    public String onClickSeeMessages() {
        System.out.println("#### Entered MainManagedBean.onClickSeeMessages");
        usertabledata = userSessionBean.GetFriendTweetInfo(userid);
        generateUserMessages();
        return "message.xhtml";

    }

    public List<DirectMessage> getMessages(int friendid) {
        return messageSessionBean.getUserMessage(userid, friendid);
    }

    public String getUserNameFromId(int id) {
        //return messageSessionBean.getUserName(int id);
        System.out.println("Entered getUserNameFromID:" + id);
        return messageSessionBean.getUserName(id);
    }

    public String onClickSendMessage(int rid)
    {
        loginStatus="";
        homeStatus="";
        userStatus="";
        relationStatus="";
        messageStatus="";
        
        System.out.println("Entered onClickSendMessage:" + messageText);
        messageSessionBean.sendMessage(messageText, userid, rid);
        messageStatus = "Your message was sent Successfully!!!!";
        messageText="";
        return "case1";
    }

    public void generateConnections() {
        followerList = relationSessionBean.getFollowers(userid);
        followingList = relationSessionBean.getFollowing(userid);
    }
    //  System.out.println("###### Last Tweet Recorded in TweetInfo :=:"+temp.tweetid+": which is :"+temp.tweet+": at :"+temp.time);
}
