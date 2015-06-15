/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author deep
 */
@Entity
@Table(name = "relation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relation.findAll", query = "SELECT r FROM Relation r"),
    @NamedQuery(name = "Relation.findByRelationId", query = "SELECT r FROM Relation r WHERE r.relationId = :relationId"),
    @NamedQuery(name = "Relation.findByReceiverId", query = "SELECT r FROM Relation r WHERE r.receiverId = :receiverId"),
    @NamedQuery(name = "Relation.findBySenderId", query = "SELECT r FROM Relation r WHERE r.senderId.userId = :senderId"),
    @NamedQuery(name = "Relation.findOneSenderIdReceiver", query = "SELECT r FROM Relation r WHERE r.senderId.userId = :senderId and r.receiverId=:receiverId"),    
    @NamedQuery(name = "Relation.findByFriendStatus", query = "SELECT r FROM Relation r WHERE r.friendStatus = :friendStatus")})
public class Relation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "relation_id")
    private Integer relationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "receiver_id")
    private int receiverId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "friend_status")
    private String friendStatus;
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User senderId;

    public Relation() {
    }

    public Relation(Integer relationId) {
        this.relationId = relationId;
    }

    public Relation(Integer relationId, int receiverId, String friendStatus) {
        this.relationId = relationId;
        this.receiverId = receiverId;
        this.friendStatus = friendStatus;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(String friendStatus) {
        this.friendStatus = friendStatus;
    }

    public User getSenderId() {
        return senderId;
    }

    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relationId != null ? relationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relation)) {
            return false;
        }
        Relation other = (Relation) object;
        if ((this.relationId == null && other.relationId != null) || (this.relationId != null && !this.relationId.equals(other.relationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Relation[ relationId=" + relationId + " ]";
    }
    
}
