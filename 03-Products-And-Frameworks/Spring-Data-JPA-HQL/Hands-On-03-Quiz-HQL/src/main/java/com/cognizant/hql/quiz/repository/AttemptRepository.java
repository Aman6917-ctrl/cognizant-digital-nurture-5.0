package com.cognizant.hql.quiz.repository;

import com.cognizant.hql.quiz.model.Attempt;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {

    @Query("""
            SELECT DISTINCT a FROM Attempt a
            JOIN FETCH a.user
            JOIN FETCH a.attemptQuestions aq
            JOIN FETCH aq.question q
            JOIN FETCH q.options
            LEFT JOIN FETCH aq.answer ans
            LEFT JOIN FETCH ans.selectedOption
            WHERE a.id = :id
            """)
    Optional<Attempt> findFullyLoadedById(@Param("id") Long id);
}
