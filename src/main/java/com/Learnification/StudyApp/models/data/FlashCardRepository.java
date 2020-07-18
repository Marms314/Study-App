package com.Learnification.StudyApp.models.data;

import com.Learnification.StudyApp.models.FlashCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashCardRepository extends CrudRepository<FlashCard, Integer> {
}
