/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author deep
 */
@Entity
@Table(name = "tweet")
@XmlRootElement
//@NamedQuery(name = "Tweet.findAll", query = "SELECT t FROM Tweet t"),
@NamedQueries({
    @NamedQuery(name = "Tweet.findAll", query = "SELECT t FROM Tweet t order by t.tweetId desc"),
    @NamedQuery(name = "Tweet.findByTweetId", query = "SELECT t FROM Tweet t WHERE t.tweetId = :tweetId"),
    @NamedQuery(name = "Tweet.findByTweet", query = "SELECT t FROM Tweet t WHERE t.tweet = :tweet"),
    @NamedQuery(name = "Tweet.findByDateTime", query = "SELECT t FROM Tweet t WHERE t.dateTime = :dateTime"),
    @NamedQuery(name = "Tweet.findByCountReport", query = "SELECT t FROM Tweet t WHERE t.countReport = :countReport"),
    @NamedQuery(name = "Tweet.findByUserId", query = "SELECT t FROM Tweet t WHERE t.userId = :userId order by t.tweetId desc"),
})

public class Tweet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tweet_id")
    private Integer tweetId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "tweet")
    private String tweet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "count_report")
    private int countReport;
    @JoinTable(name = "retweet", joinColumns = {
        @JoinColumn(name = "tweet_id", referencedColumnName = "tweet_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    @ManyToMany
    private Collection<User> userCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;

    public Tweet() {
    }

    public Tweet(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public Tweet(Integer tweetId, String tweet, Date dateTime, int countReport) {
        this.tweetId = tweetId;
        this.tweet = tweet;
        this.dateTime = dateTime;
        this.countReport = countReport;
    }

    public Integer getTweetId() {
        return tweetId;
    }

    public void setTweetId(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getCountReport() {
        return countReport;
    }

    public void setCountReport(int countReport) {
        this.countReport = countReport;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tweetId != null ? tweetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tweet)) {
            return false;
        }
        Tweet other = (Tweet) object;
        if ((this.tweetId == null && other.tweetId != null) || (this.tweetId != null && !this.tweetId.equals(other.tweetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Tweet[ tweetId=" + tweetId + " ]";
    }
    
}
