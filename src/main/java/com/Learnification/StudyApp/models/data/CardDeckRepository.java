package com.Learnification.StudyApp.models.data;

import com.Learnification.StudyApp.models.CardDeck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDeckRepository extends CrudRepository<CardDeck, Integer> {
}
