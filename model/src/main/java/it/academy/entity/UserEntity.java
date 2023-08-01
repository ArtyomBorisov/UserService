package it.academy.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 40)
    private String lastName;

    @Column(name = "middle_name", nullable = false, length = 40)
    private String middleName;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @ManyToOne
    @JoinColumn(name = "role", nullable = false)
    private RoleEntity roleEntity;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public static class Builder {
        private final UserEntity entity;

        private Builder() {
            entity = new UserEntity();
        }

        public Builder setFirstName(String firstName) {
            entity.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            entity.lastName = lastName;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            entity.middleName = middleName;
            return this;
        }

        public Builder setEmail(String email) {
            entity.email = email;
            return this;
        }

        public Builder setRoleEntity(RoleEntity roleEntity) {
            entity.roleEntity = roleEntity;
            return this;
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public UserEntity build() {
            return entity;
        }
    }
}
