package uz.developer.cardtransferapp.controller;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developer.cardtransferapp.entity.Income;
import uz.developer.cardtransferapp.service.IncomeService;

import java.awt.print.Pageable;
import java.util.Date;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
    @Autowired
    IncomeService incomeService;

@GetMapping("byToCardId/{id}")
    public ResponseEntity<Page<Income>> getAll(@PathVariable int id, @RequestParam int page){
    return incomeService.getByToCardId(id,page);
}

@GetMapping("byToCardIdAndDate/{id}")
public ResponseEntity<Page<Income>> getAllByDateAndId(
        @PathVariable int id,
        @RequestParam("page") int page,
        @RequestParam("date")Date date
        ){
    return incomeService.getByToCardId(id,page);
}




}
