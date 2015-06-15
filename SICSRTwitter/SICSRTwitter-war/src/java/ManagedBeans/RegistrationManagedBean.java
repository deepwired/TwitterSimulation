/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.net.Authenticator;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.PasswordAuthentication;

import SessionBeans.RegistrationSessionBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author deep
 */
@ManagedBean
@SessionScoped
public class RegistrationManagedBean {

    @EJB
    private RegistrationSessionBean registrationSessionBean;

    public RegistrationManagedBean() {

    }

    private String newUserName;
    private String emailid;
    private String password;
    private String cpassword;
    private String errorMessage;
    //for image
    private static final int BUFFER_SIZE = 6124;
    private String folderToUpload;
    private boolean uploadChoice=false;

    public String getFolderToUpload() {
        return folderToUpload;
    }

    public void setFolderToUpload(String folderToUpload) {
        this.folderToUpload = folderToUpload;
    }

    public boolean getUploadChoice() {
        return uploadChoice;
    }

    public void setUploadChoice(boolean uploadChoice) {
        this.uploadChoice = uploadChoice;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public String isPasswordMatch() {
        errorMessage = "";
        System.out.println("#### RegistrationManagedBean.isPasswordMatch was called");
        int flag = registrationSessionBean.checkUserName(newUserName, emailid);
        if (flag == -1) {
            errorMessage = "User Name already exists,kindly change!";
        } else if (flag == -2) {
            errorMessage = "User Email id already registered!! ,kindly change!";
        } else {
            if (sendMain(emailid, newUserName)) {
                registrationSessionBean.insertUserDetails(newUserName, emailid, password);
                newUserName = "";
                emailid = "";
                return "case1";
            } else {
                errorMessage = "There is some error in the EmailId, please enter a valid one!";
            }
        }
        return "";
    }

    public void handleFileUpload(FileUploadEvent event) {

        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        System.out.println("******" + event.getFile().getFileName());

        System.out.println("This is the new User Name" + newUserName);
        File result = new File(extContext.getRealPath("/resources/UserProfilePictures/"), newUserName+".jpg");

//System.out.println(extContext.getRealPath("/resources/UserProfilePictures")+event.getFile().getFileName());
        System.out.println(result.getAbsolutePath());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);

            byte[] buffer = new byte[BUFFER_SIZE];

            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                //fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            FacesMessage msg = new FacesMessage("File Description", "file name: "
                    + event.getFile().getFileName()
                    + "file size: "
                    + event.getFile().getSize() / 1024
                    + " Kb content type: "
                    + event.getFile().getContentType()
                    + "The file was uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            e.printStackTrace();

            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "The files were not uploaded!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }

    public boolean sendMain(String email, String username) {

        MailSender ms = new MailSender();
        return ms.sendMail(email, username);
    }
    
    public String onClickYesPicture()
    {
        uploadChoice=true;
        return "case2";
    }

}
