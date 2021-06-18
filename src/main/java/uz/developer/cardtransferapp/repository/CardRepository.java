package uz.developer.cardtransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developer.cardtransferapp.entity.Card;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Integer> {
    boolean existsByNumber(String  number);

    List<Card> findAllByUsername(String username);

    Card findByNumber(String number);
}
