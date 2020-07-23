package com.Learnification.StudyApp.controllers;

import com.Learnification.StudyApp.models.Question;
import com.Learnification.StudyApp.models.Quiz;
import com.Learnification.StudyApp.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value={"", "/index"})
    public String index(Model model) {

        model.addAttribute("title", "Are you ready?");
        model.addAttribute("quizzes", quizRepository.findAll());

        return "quiz/index";
    }

    @GetMapping("create")
    public String renderCreateQuizForm(Model model) {

        model.addAttribute("title", "New Quiz");
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute(new Quiz());

        return "quiz/create";
    }

    @PostMapping("create")
    public String processCreateQuizForm(@ModelAttribute @Valid Quiz quiz, BindingResult bindingResult,
                                        Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "New Quiz");
            model.addAttribute("categories", categoryRepository.findAll());
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
            model.addAttribute("categories", categoryRepository.findAll());
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

        model.addAttribute("title", "New Quiz");

        return "quiz/manage";
    }

    @PostMapping("manage")
    public String processManageQuizForm(Model model) {


        return "redirect:../";
    }

}
