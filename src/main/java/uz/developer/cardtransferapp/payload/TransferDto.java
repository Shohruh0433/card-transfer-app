package uz.developer.cardtransferapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransferDto {

    @NotNull(message = "karta raqami kiritilmagan")
    private String fromCardNumber;
    @NotNull(message = "siz pul o'tykazmoqchi bolgan karta raqami kiritilmagan")
    private String toCardNumber;
    @NotNull(message = "summa kiritilmagan")
    private  Double amount;

}
