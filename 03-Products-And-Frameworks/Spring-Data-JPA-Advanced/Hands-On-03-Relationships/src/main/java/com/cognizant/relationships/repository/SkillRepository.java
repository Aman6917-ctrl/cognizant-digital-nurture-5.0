package com.cognizant.relationships.repository;

import com.cognizant.relationships.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
