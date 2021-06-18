    package uz.developer.cardtransferapp.entity;

    import lombok.Data;

    import javax.persistence.*;

    import java.util.Date;

    @Entity
    @Data

    public class Income {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;


        private int fromCardId;
        private int toCardId;

        private Double amount;

        private Date date;

    }
