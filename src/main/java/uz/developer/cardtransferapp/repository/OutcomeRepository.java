package uz.developer.cardtransferapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.cardtransferapp.entity.Outcome;

import java.util.Date;

public interface OutcomeRepository extends JpaRepository<Outcome,Integer> {


    Page<Outcome> findAllByFromCardId(int fromCardId, Pageable pageable);

    Page<Outcome> findAllByToCardId(int toCardId, Pageable pageable);
    Page<Outcome> findAllByFromCardIdAndDate(int fromCardId, Date date, Pageable pageable);
    Page<Outcome> findAllByToCardIdAndDate(int toCardId, Date date, Pageable pageable);


}
