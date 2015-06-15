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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByPrivacy", query = "SELECT u FROM User u WHERE u.privacy = :privacy"),
    @NamedQuery(name = "User.findByEmailId", query = "SELECT u FROM User u WHERE u.emailId = :emailId"),
    @NamedQuery(name = "User.findByStatus", query = "SELECT u FROM User u WHERE u.status = :status"),
    @NamedQuery(name = "User.findByDateDeact", query = "SELECT u FROM User u WHERE u.dateDeact = :dateDeact")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "privacy")
    private String privacy;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email_id")
    private String emailId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_deact")
    @Temporal(TemporalType.DATE)
    private Date dateDeact;
    @ManyToMany(mappedBy = "userCollection")
    private Collection<Tweet> tweetCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderId")
    private Collection<Relation> relationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Tweet> tweetCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recevierId")
    private Collection<DirectMessage> directMessageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderId")
    private Collection<DirectMessage> directMessageCollection1;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String username, String password, String privacy, String emailId, String status, Date dateDeact) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.privacy = privacy;
        this.emailId = emailId;
        this.status = status;
        this.dateDeact = dateDeact;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateDeact() {
        return dateDeact;
    }

    public void setDateDeact(Date dateDeact) {
        this.dateDeact = dateDeact;
    }

    @XmlTransient
    public Collection<Tweet> getTweetCollection() {
        return tweetCollection;
    }

    public void setTweetCollection(Collection<Tweet> tweetCollection) {
        this.tweetCollection = tweetCollection;
    }

    @XmlTransient
    public Collection<Relation> getRelationCollection() {
        return relationCollection;
    }

    public void setRelationCollection(Collection<Relation> relationCollection) {
        this.relationCollection = relationCollection;
    }

    @XmlTransient
    public Collection<Tweet> getTweetCollection1() {
        return tweetCollection1;
    }

    public void setTweetCollection1(Collection<Tweet> tweetCollection1) {
        this.tweetCollection1 = tweetCollection1;
    }

    @XmlTransient
    public Collection<DirectMessage> getDirectMessageCollection() {
        return directMessageCollection;
    }

    public void setDirectMessageCollection(Collection<DirectMessage> directMessageCollection) {
        this.directMessageCollection = directMessageCollection;
    }

    @XmlTransient
    public Collection<DirectMessage> getDirectMessageCollection1() {
        return directMessageCollection1;
    }

    public void setDirectMessageCollection1(Collection<DirectMessage> directMessageCollection1) {
        this.directMessageCollection1 = directMessageCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.User[ userId=" + userId + " ]";
    }
    
}
