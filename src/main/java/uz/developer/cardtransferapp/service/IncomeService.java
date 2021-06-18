package uz.developer.cardtransferapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.developer.cardtransferapp.entity.Income;
import uz.developer.cardtransferapp.repository.IncomeRepository;

import java.util.Date;

@Service
public class IncomeService {
    @Autowired
    IncomeRepository incomeRepository;

  public   ResponseEntity<Page<Income>> getByToCardId(Integer id,int page){
        Pageable pageable= PageRequest.of(page,10);
        return ResponseEntity.ok(incomeRepository.findAllByFromCardId(id,pageable));
    }


    public   ResponseEntity<Page<Income>> getByToCardIdANdDate(Integer id, int page, Date date){
        Pageable pageable= PageRequest.of(page,10);
        return ResponseEntity.ok(incomeRepository.findAllByToCardIdAndDate(id,date,pageable));
    }







}
