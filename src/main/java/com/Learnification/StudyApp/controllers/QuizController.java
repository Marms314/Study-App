package com.Learnification.StudyApp.controllers;

import com.Learnification.StudyApp.models.CardDeck;
import com.Learnification.StudyApp.models.Question;
import com.Learnification.StudyApp.models.Quiz;
import com.Learnification.StudyApp.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @RequestMapping(value={"", "/index"})
    public String index(Model model) {

        model.addAttribute("title", "Are you ready?");
        model.addAttribute("quizzes", quizRepository.findAll());

        return "quiz/index";
    }


    @RequestMapping("view/{quizId}")
    public String renderQuiz(Model model, @PathVariable int quizId) {

        Optional optQuiz = quizRepository.findById(quizId);
        if (optQuiz.isPresent()) {
            Quiz quiz = (Quiz) optQuiz.get();
            model.addAttribute("title", quiz.getName());
            model.addAttribute("questions", quiz.getQuestions());
            return "quiz/view";
        }

        return "quiz/index";
    }


    @GetMapping("random")
    public String chooseRandomQuiz(Model model) {
        List<Quiz> allQuizzes = (ArrayList<Quiz>) quizRepository.findAll();
        int randomIndex = (int) (Math.random() * (allQuizzes.size() - 1));
        Quiz chosenQuiz = allQuizzes.get(randomIndex);
        return "redirect:view/" + chosenQuiz.getId();
    }


    @GetMapping("create")
    public String renderCreateQuizForm(Model model) {

        model.addAttribute("title", "New Quiz");
        model.addAttribute(new Quiz());

        return "quiz/create";
    }

    @PostMapping("create")
    public String processCreateQuizForm(@ModelAttribute @Valid Quiz quiz, BindingResult bindingResult,
                                        Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "New Quiz");
            return "quiz/create";
        }

        Enumeration requestParams = request.getParameterNames();
        Map<String, String> questionParams = new HashMap<>();
        while (requestParams.hasMoreElements()){
            String parameterName = (String) requestParams.nextElement();
            if (parameterName.contains("question")) {
                questionParams.put(parameterName, request.getParameter(parameterName));
            }
        }

        if (questionParams.containsValue("") || questionParams.containsValue(null)) {
            model.addAttribute("title", "New Quiz");
            model.addAttribute("questionError", true);
            return "quiz/create";
        }

        quizRepository.save(quiz);

        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < (questionParams.size() / 3); i++) {
            String questionName = questionParams.get("questionName" + i);
            String correctAnswer = questionParams.get("questionCorrectAnswer" + i);
            String wrongAnswer = questionParams.get("questionWrongAnswer" + i);
            Question thisQuestion = new Question(questionName, correctAnswer, wrongAnswer, quiz);
            questions.add(thisQuestion);
        }

        questionRepository.saveAll(questions);
        return "redirect:index";
    }

    @GetMapping("manage")
    public String renderManageQuizForm(Model model) {

        boolean quizzesExist = quizRepository.count() != 0;
        model.addAttribute("quizzesExist", quizzesExist);
        model.addAttribute("title", "Manage Quizzes");
        model.addAttribute("quizzes", quizRepository.findAll());

        return "quiz/manage";
    }

    @PostMapping("manage")
    public String processManageQuizForm(@RequestParam(required = false) int[] quizIds, Model model) {

        if (quizIds != null) {
            ArrayList<Question> questions = (ArrayList<Question>) questionRepository.findAll();

            for (int id : quizIds) {
                Quiz currentQuiz = quizRepository.findById(id).get();
                for (Question question : questions) {
                    Quiz questionQuiz = question.getQuiz();
                    if (questionQuiz.equals(currentQuiz)) {
                        questionRepository.delete(question);
                    }
                }
                quizRepository.delete(currentQuiz);
            }

            boolean quizzesExist = quizRepository.count() != 0;
            model.addAttribute("quizzesExist", quizzesExist);
            model.addAttribute("quizWasDeleted", true);
            model.addAttribute("title", "Manage Quizzes");
            model.addAttribute("quizzes", quizRepository.findAll());
            return "quiz/manage";
        }

        boolean quizzesExist = quizRepository.count() != 0;
        model.addAttribute("quizzesExist", quizzesExist);
        model.addAttribute("quizzes", quizRepository.findAll());
        model.addAttribute("title", "Manage Quizzes");
        model.addAttribute("noIdsSelected", true);
        return "quiz/manage";
    }

}
