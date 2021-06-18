package uz.developer.cardtransferapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developer.cardtransferapp.entity.Card;
import uz.developer.cardtransferapp.payload.CardDto;
import uz.developer.cardtransferapp.security.JwtFilter;
import uz.developer.cardtransferapp.service.CardService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {


    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    CardService cardService;

    @PostMapping
    public ResponseEntity<String > add(@Valid @RequestBody CardDto cardDto){
        return cardService.add(cardDto);
    }

    @GetMapping("/{number}")
    public ResponseEntity<Card> getByNumber(@PathVariable String  number){
        return cardService.getByNumber(number);
    }

    @GetMapping("/all/{username}")
    public ResponseEntity<List<Card>> getAllByUsername(@PathVariable String username){
        return cardService.getByUserName(username);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String > edit(@RequestBody CardDto cardDto,@PathVariable Integer id){
        return cardService.edit(cardDto,id);
    }


}
