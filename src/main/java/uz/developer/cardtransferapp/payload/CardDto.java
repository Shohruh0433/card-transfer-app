package uz.developer.cardtransferapp.payload;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class CardDto {
    
    @NotNull(message = "username kiritilmagan")
    private String username;
    @NotNull(message = "karta raqami kiritilmagan")
    @Size(min = 15, message = "karta raqami 16 ta bo'lishi kerak")
    private String  number;
    @NotNull(message = "kartadagi pul miqdori kiritilmagan")

    private double balance;
    @NotNull(message = "kartaning amal qilish muddati kiritilmagan")
    private Date expireDate;
    
    
}
