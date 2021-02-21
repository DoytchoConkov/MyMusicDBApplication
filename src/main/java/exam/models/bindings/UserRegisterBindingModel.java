package exam.models.bindings;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {
    private String username;
    private String fullName;
    private String password;
    private String confirmPassword;
    private String email;

    public UserRegisterBindingModel() {
    }

    @NotBlank(message = "Username can not be empty.")
    @Size(min = 3, max = 20, message = "Username length must be between three and  twenty characters.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "Fullname can not be empty.")
    @Size(min = 3, max = 20, message = "Fullname length must be between three and  twenty characters.")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @NotBlank(message = "Password can not be empty.")
    @Size(min = 5, max = 20, message = "Password length must be between five and  twenty characters.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotBlank(message = "Password can not be empty.")
    @Size(min = 5, max = 20, message = "Password length must be between five and  twenty characters.")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Email(message = "Email must be valid!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
