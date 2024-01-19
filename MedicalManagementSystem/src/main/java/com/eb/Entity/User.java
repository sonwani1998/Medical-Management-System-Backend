package com.eb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "register_User")
public class User {
    @Id
    private String userId;
    private String name;
    private String email;
    private String mobile;
    private String address;

    
}
