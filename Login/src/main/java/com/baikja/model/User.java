package com.baikja.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "email")
//    @Email(message = "*Please provide a valid Email")
//    @NotEmpty(message = "*Please provide an email")
    @Pattern(regexp="^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", message="Email형식으로 입력해야 합니다.")
    @NotNull(message="Email은 필수항목입니다.")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "비밀번호는 적어도 5자 이상이어야 합니다.")
//    @NotEmpty(message = "*Please provide your password")
    @NotNull(message="비밀번호는 필수항목입니다.")
    @Transient
    private String password;
    @Column(name = "name")
//    @NotEmpty(message = "*Please provide your name")
    @NotNull(message="이름은 필수항목입니다.")
    private String name;
    @Column(name = "last_name")
//    @NotEmpty(message = "*Please provide your last name")
    @NotNull(message="성은 필수항목입니다.")
    private String lastName;
    @Column(name = "active")
    private int active;
    
    @Column(name = "role")
    @NotNull(message="성별을 선택해주세요.")
    @Pattern(regexp="\\S", message="성별을 선택해주세요.")
    private String role;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
