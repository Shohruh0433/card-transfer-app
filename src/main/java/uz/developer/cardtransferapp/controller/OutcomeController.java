package uz.developer.cardtransferapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.developer.cardtransferapp.entity.Outcome;
import uz.developer.cardtransferapp.payload.TransferDto;
import uz.developer.cardtransferapp.service.OutcomeService;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {
    @Autowired
    OutcomeService outcomeService;

@GetMapping("/byToCardId/{id}")
    public ResponseEntity<Page<Outcome>> getAll(@PathVariable int id, @RequestParam int page){
    return outcomeService.getByFromCardId(id,page);
}

@GetMapping("/byToCardIdAndDate/{id}")
public ResponseEntity<Page<Outcome>> getAllByDateAndId(
        @PathVariable int id,
        @RequestParam("page") int page,
        @RequestParam("date")Date date
        ){
    return outcomeService.getByToCardIdANdDate(id,page,date);
}

@PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferDto transferDto){
    return outcomeService.transfer(transferDto);

}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }



}
