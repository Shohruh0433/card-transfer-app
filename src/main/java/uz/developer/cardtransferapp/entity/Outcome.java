package uz.developer.cardtransferapp.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int fromCardId;
    @Column(nullable = false)
    private int toCardId;

    @Column(nullable = false)
    private Double amount;

    private Date date;

    private double commissionAmount;


}
