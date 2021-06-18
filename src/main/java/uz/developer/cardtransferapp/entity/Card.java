package uz.developer.cardtransferapp.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data

public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String username;

    @Column(nullable = false,unique = true,length = 16)
        private String  number;
    private Double balance;

    private Date expireDate;

    private boolean active;


}
