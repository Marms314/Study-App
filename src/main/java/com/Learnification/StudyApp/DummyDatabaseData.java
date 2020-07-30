package com.Learnification.StudyApp;

import com.Learnification.StudyApp.models.CardDeck;
import com.Learnification.StudyApp.models.FlashCard;
import com.Learnification.StudyApp.models.Question;
import com.Learnification.StudyApp.models.Quiz;
import com.Learnification.StudyApp.models.data.CardDeckRepository;
import com.Learnification.StudyApp.models.data.FlashCardRepository;
import com.Learnification.StudyApp.models.data.QuestionRepository;
import com.Learnification.StudyApp.models.data.QuizRepository;

import java.util.ArrayList;
import java.util.List;

public class DummyDatabaseData {

    public void addData(QuizRepository quizRepository, CardDeckRepository cardDeckRepository,
                        QuestionRepository questionRepository, FlashCardRepository flashCardRepository) {
        for (int i = 1; i < 11; i++) {
            String name = "Multiples of " + i;
            String description = " your knowledge of multiplication using the number " + i;

            Quiz quiz = new Quiz();
            CardDeck cardDeck = new CardDeck();

            quiz.setName(name);
            quiz.setDescription("Test" + description);
            cardDeck.setName(name);
            cardDeck.setDescription("Improve" + description);

            quizRepository.save(quiz);
            cardDeckRepository.save(cardDeck);

            List<Question> questions = new ArrayList<>();
            List<FlashCard> flashCards = new ArrayList<> ();
            int randomNumber = 1 + (int)(Math.random() * ((5 - 1) + 1));

            for (int j = 0; j < 11; j++) {
                String equation = i + " x " + j + " = ";
                String correctAnswer = String.valueOf(i * j);
                String wrongAnswer = String.valueOf(i * (j + randomNumber));

                Question thisQuestion = new Question(equation, correctAnswer, wrongAnswer, quiz);
                FlashCard thisCard = new FlashCard(equation, correctAnswer, cardDeck);
                flashCards.add(thisCard);
                questions.add(thisQuestion);
            }

            flashCardRepository.saveAll(flashCards);
            questionRepository.saveAll(questions);
        }
    }
}
