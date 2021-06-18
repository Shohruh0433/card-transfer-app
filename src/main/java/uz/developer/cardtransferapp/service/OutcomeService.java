package uz.developer.cardtransferapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.developer.cardtransferapp.entity.Card;
import uz.developer.cardtransferapp.entity.Income;
import uz.developer.cardtransferapp.entity.Outcome;
import uz.developer.cardtransferapp.payload.TransferDto;
import uz.developer.cardtransferapp.repository.CardRepository;
import uz.developer.cardtransferapp.repository.IncomeRepository;
import uz.developer.cardtransferapp.repository.OutcomeRepository;

import java.util.Date;

@Service
public class OutcomeService {

    @Autowired
    OutcomeRepository outcomeRepository;

    @Autowired
    IncomeRepository incomeRepository;

    @Autowired
    CardRepository cardRepository;

    public ResponseEntity<Page<Outcome>> getByFromCardId(Integer id, int page){
        Pageable pageable= PageRequest.of(page,10);
        return ResponseEntity.ok(outcomeRepository.findAllByFromCardId(id,pageable));
    }


    public   ResponseEntity<Page<Outcome>> getByToCardIdANdDate(Integer id, int page, Date date){
        Pageable pageable= PageRequest.of(page,10);
        return ResponseEntity.ok(outcomeRepository.findAllByFromCardIdAndDate(id,date,pageable));
    }

    public ResponseEntity<String > transfer(TransferDto transferDto){
        Card card = cardRepository.findByNumber(transferDto.getFromCardNumber());
        Card toCard = cardRepository.findByNumber(transferDto.getToCardNumber());
        if (card==null || toCard==null)
          return   ResponseEntity.status(401).body("karta topilmadi");
        if (!card.isActive()) {
         return    ResponseEntity.ok("Sizning kartangiz aktiv emas");

        }
        if (!toCard.isActive()){
           return ResponseEntity.ok("Mablag' qabul qiluvchining kartasi aktiv emas");

        }
        if ((card.getBalance()<(transferDto.getAmount()*1.01)))
         return    ResponseEntity.ok("mablag'ingiz yetarli emas.Kamroq mablag' o'tkazishhingiz mumkin");

        card.setBalance(card.getBalance()-(transferDto.getAmount()*1.01));
        toCard.setBalance(toCard.getBalance()+ transferDto.getAmount());
        cardRepository.save(card);
        cardRepository.save(toCard);
        Outcome outcome=new Outcome();
        outcome.setFromCardId(card.getId());
        outcome.setToCardId(toCard.getId());
        outcome.setAmount(transferDto.getAmount());
        outcome.setCommissionAmount(transferDto.getAmount()*0.01);
        outcome.setDate(new Date());
        outcomeRepository.save(outcome);

      Income income=new Income();
        income.setAmount(transferDto.getAmount());
        income.setDate(new Date());
        income.setFromCardId(card.getId());
        income.setToCardId(toCard.getId());
        incomeRepository.save(income);

      return   ResponseEntity.ok("Pul o'tkazmasi muvaffaqiyatli amalga oshdi");

    }



}
