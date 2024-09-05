package com.kemal.spring.web.dto;

import com.kemal.spring.customAnnotations.PasswordMatches;
import com.kemal.spring.customAnnotations.ValidEmail;
import com.kemal.spring.domain.Role;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Created by Keno&Kemo on 22.10.2017..
 */
@PasswordMatches
public class UserDto {

    private Long id;

    @NotBlank (message = "Name is required")
    private String name;

    @NotBlank (message = "Surname is required")
    private String surname;

    @NotBlank (message = "Employeecode is required")
    private String empcode;

    @NotBlank (message = "Department is required")
	private String empdepartment;

    @NotBlank (message = "Branch is required")
	private String empbranch;

    @NotBlank (message = "Contact is required")
	private String empcontactno;

    private String username;
    
    @ValidEmail
    @NotBlank (message = "Email is required")
    private String email;

    private String password;
    
    private String matchingPassword;
    
    private String userrole;

    private boolean enabled;

    public UserDto(Long id, String name, String surname, String empcode, String email, String password, String
            matchingPassword, Set<Role> roles, boolean enabled,String empdepartment,String empbranch,String empcontactno,String username,String userrole) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.empcode = empcode;
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.enabled = enabled;
        this.empbranch=empbranch;
        this.empdepartment=empdepartment;
        this.empcontactno=empcontactno;
        this.username=username;
        this.userrole=userrole;
    }

    public UserDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

       public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}

	public String getEmpdepartment() {
		return empdepartment;
	}

	public void setEmpdepartment(String empdepartment) {
		this.empdepartment = empdepartment;
	}

	public String getEmpbranch() {
		return empbranch;
	}

	public void setEmpbranch(String empbranch) {
		this.empbranch = empbranch;
	}

	public String getEmpcontactno() {
		return empcontactno;
	}

	public void setEmpcontactno(String empcontactno) {
		this.empcontactno = empcontactno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
    
    
}
