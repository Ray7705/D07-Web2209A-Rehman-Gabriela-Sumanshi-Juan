package basharatkaranwalfranco.store.models;

public class User {
   
   private int id;
   private String firstName;
   private String lastName;
   private String username;
   private String passwordHash; //convert to a differentType
   private String emailId;
   

    public User(String firstName, String lastName, String username, String passwordHash, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordHash = passwordHash;
        this.emailId = emailId;
        
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
   
    
    public String getEmailID()
    {
        return emailId;
    }
   
    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }
}
