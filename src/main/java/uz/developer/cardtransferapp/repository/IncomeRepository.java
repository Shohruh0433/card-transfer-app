package uz.developer.cardtransferapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.cardtransferapp.entity.Income;

import java.util.Date;

public interface IncomeRepository extends JpaRepository<Income,Integer> {


    Page<Income> findAllByToCardId(int toCardId, Pageable pageable);
    Page<Income> findAllByFromCardId(int fromCardId, Pageable pageable);
    Page<Income> findAllByToCardIdAndDate(int toCardId, Date date, Pageable pageable);
    Page<Income> findAllByFromCardIdAndDate(int fromCardId, Date date, Pageable pageable);



}
