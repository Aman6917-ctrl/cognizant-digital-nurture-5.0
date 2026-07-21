package com.cognizant.hql.quiz.service;

import com.cognizant.hql.quiz.model.Attempt;
import com.cognizant.hql.quiz.model.AttemptQuestion;
import com.cognizant.hql.quiz.model.QuizOption;
import com.cognizant.hql.quiz.repository.AttemptRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttemptService {

    private final AttemptRepository attemptRepository;

    public AttemptService(AttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    @Transactional(readOnly = true)
    public String buildAttemptReport(Long attemptId) {
        Attempt attempt = attemptRepository
                .findFullyLoadedById(attemptId)
                .orElseThrow(() -> new IllegalArgumentException("Attempt not found: " + attemptId));

        StringBuilder report = new StringBuilder();
        report.append("========================================\n");
        report.append("       QUIZ ATTEMPT REPORT\n");
        report.append("========================================\n");
        report.append("User        : ").append(attempt.getUser().getUsername()).append('\n');
        report.append("Attempt Id  : ").append(attempt.getId()).append('\n');
        report.append("Score       : ").append(attempt.getScore()).append('\n');
        report.append("----------------------------------------\n");

        for (AttemptQuestion attemptQuestion : attempt.getAttemptQuestions()) {
            report.append("Question ")
                    .append(attemptQuestion.getDisplayOrder())
                    .append(" : ")
                    .append(attemptQuestion.getQuestion().getPrompt())
                    .append('\n');

            for (QuizOption option : attemptQuestion.getQuestion().getOptions()) {
                report.append("  ")
                        .append(option.getLabel())
                        .append(") ")
                        .append(option.getOptionText());
                if (option.isCorrect()) {
                    report.append(" (Correct)");
                }
                report.append('\n');
            }

            if (attemptQuestion.getAnswer() != null && attemptQuestion.getAnswer().getSelectedOption() != null) {
                QuizOption selected = attemptQuestion.getAnswer().getSelectedOption();
                report.append("Selected    : ")
                        .append(selected.getLabel())
                        .append(") ")
                        .append(selected.getOptionText())
                        .append('\n');
            } else {
                report.append("Selected    : (none)\n");
            }
            report.append('\n');
        }

        report.append("========================================\n");
        return report.toString();
    }
}
