package vn.techmaster.employeemanagementdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
public class EmployeeEntity {
    @Transient
    private String base64Img;

    @Column(name = "employee_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "avatar")
    @Lob
    private byte[] avatar;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;
    
    @Column(name = "passport_number")
    private String passportNumber;

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public boolean matchWithKeyWord(String keyword) {
        String lowerName = getFullName().toLowerCase();
        String lowerEmail = email.toLowerCase();
        String lowerKeyWord = keyword.toLowerCase();
        return lowerName.contains(lowerKeyWord) || lowerEmail.contains(lowerKeyWord);
    }

    public void convertByteArrToBase64() throws UnsupportedEncodingException{
        this.setBase64Img(new String(Base64.getEncoder().encode(this.getAvatar()), "UTF-8"));
    };
}
