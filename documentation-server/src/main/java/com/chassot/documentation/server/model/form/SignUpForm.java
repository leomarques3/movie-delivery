package com.chassot.documentation.server.model.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Sign Up Request", description = "Registration information of a new user")
public class SignUpForm {

    @ApiModelProperty(value = "First name", dataType = "string", example = "John")
    private String firstName;

    @ApiModelProperty(value = "Last name", dataType = "string", example = "Doe")
    private String lastName;

    @ApiModelProperty(value = "Username", dataType = "string", example = "john.doe")
    private String username;

    @ApiModelProperty(value = "Email", dataType = "string", example = "john.doe@test.com")
    private String email;

    @ApiModelProperty(value = "Password", dataType = "string", example = "test")
    private String password;

    public SignUpForm() {
    }

    public SignUpForm(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
