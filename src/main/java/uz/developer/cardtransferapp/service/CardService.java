package uz.developer.cardtransferapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.developer.cardtransferapp.entity.Card;
import uz.developer.cardtransferapp.payload.CardDto;
import uz.developer.cardtransferapp.repository.CardRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    public ResponseEntity<String > add(CardDto cardDto){
        boolean b = cardRepository.existsByNumber(cardDto.getNumber());
        if (b) return ResponseEntity.ok("bunday card raqami mavjud");
        Card card=new Card();
        card.setActive(true);
        card.setBalance(cardDto.getBalance());
        card.setExpireDate(cardDto.getExpireDate());
        card.setNumber(cardDto.getNumber());
        card.setUsername(cardDto.getUsername());
        cardRepository.save(card);
        return ResponseEntity.ok("muvaffaqiyatli qo'shildi");
    }

    public ResponseEntity<String > edit(CardDto cardDto,Integer id){
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent())
            return ResponseEntity.ok("karta topilmadi");

        boolean b = cardRepository.existsByNumber(cardDto.getNumber());
        if (b) return ResponseEntity.ok("bunday card raqami mavjud");
        Card card=optionalCard.get();
        card.setActive(true);
        card.setBalance(cardDto.getBalance());
        card.setExpireDate(cardDto.getExpireDate());
        card.setNumber(cardDto.getNumber());
        card.setUsername(cardDto.getUsername());
        cardRepository.save(card);
        return ResponseEntity.ok("muvaffaqiyatli o'zgartirildi");
    }

    public ResponseEntity<String > delete(Integer id){
        boolean b = cardRepository.existsById(id);
        if (!b) ResponseEntity.status(401).body("karta topilmadi");
        cardRepository.deleteById(id);
       return ResponseEntity.status(200).body("muvaffaqiyatli o'chirildi");
    }

    public ResponseEntity<List<Card>> getByUserName(String username){
        List<Card> allByUsername = cardRepository.findAllByUsername(username);
        if (allByUsername.isEmpty()) return ResponseEntity.ok(null);
        return ResponseEntity.ok(allByUsername);
    }

    public  ResponseEntity<Card> getByNumber(String  number){
        return ResponseEntity.ok(cardRepository.findByNumber(number));
    }



}
