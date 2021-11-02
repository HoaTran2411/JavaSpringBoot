package vn.techmaster.employeemanagementdb.model;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
public class UpdateEmployeeReq {

    private MultipartFile avatarImg;

    @Size(min = 3, max = 30, message = "First name must be between 3 and 30 characters")
    @NotBlank(message = "First name is not blank")
    private String firstName;

    @Size(min = 3, max = 30, message = "Last name must be between 3 and 30 characters")
    @NotBlank(message = "Last name is not blank")
    private String lastName;

    @Email (message = "Not valid email")
    @NotBlank(message = "Email is required")
    private String email;
   
    @Size(min = 8, max = 8, message = "Passport Number must be 8 characters")
    private String passportNumber;

    private String base64Img;
    private byte[] avatar;

    public void convertByteArrToBase64() throws UnsupportedEncodingException{
        this.setBase64Img(new String(Base64.getEncoder().encode(this.getAvatar()), "UTF-8"));
    };

}
