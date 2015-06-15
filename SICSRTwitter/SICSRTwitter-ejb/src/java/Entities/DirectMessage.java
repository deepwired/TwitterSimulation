/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 *
 */
@Entity
@Table(name = "direct_message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DirectMessage.findAll", query = "SELECT d FROM DirectMessage d"),
    @NamedQuery(name = "DirectMessage.findByMessageId", query = "SELECT d FROM DirectMessage d WHERE d.messageId = :messageId"),
    @NamedQuery(name = "DirectMessage.findByMessage", query = "SELECT d FROM DirectMessage d WHERE d.message = :message"),
    @NamedQuery(name = "DirectMessage.findByTimestamp", query = "SELECT d FROM DirectMessage d WHERE d.timestamp = :timestamp"),
    @NamedQuery(name = "DirectMessage.findMessagePerUser", query = "SELECT d FROM DirectMessage d WHERE (d.senderId=:a OR d.senderId=:b) AND (d.recevierId=:a OR d.recevierId=:b)")
})
public class DirectMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "message_id")
    private Integer messageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @JoinColumn(name = "recevier_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User recevierId;
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User senderId;

    public DirectMessage() {
    }

    public DirectMessage(Integer messageId) {
        this.messageId = messageId;
    }

    public DirectMessage(Integer messageId, String message, Date timestamp) {
        this.messageId = messageId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getRecevierId() {
        return recevierId;
    }

    public void setRecevierId(User recevierId) {
        this.recevierId = recevierId;
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
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DirectMessage)) {
            return false;
        }
        DirectMessage other = (DirectMessage) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.DirectMessage[ messageId=" + messageId + " ]";
    }
    
}
