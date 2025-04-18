package hn.springcloud.msvc.libs.commons.entities;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_users")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(name = "last_login")
    @CreationTimestamp
    private LocalDateTime lastLogin;

    @Column(name = "failed_login_attempts", columnDefinition = "int default 0")
    private Integer failedLoginAttempts;

    @Column(columnDefinition = "boolean default true")
    private Boolean status;

    @ManyToAny
    @JoinTable(
        name = "tbl_user_roles", 
        joinColumns = { @JoinColumn(name = "id_user") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_role") }, 
        uniqueConstraints = { @UniqueConstraint(columnNames = { "id_user", "id_role" }) })
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Set<Role> roles;
}
