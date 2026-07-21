package com.cognizant.relationships.service;

import com.cognizant.relationships.model.Skill;
import com.cognizant.relationships.repository.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Transactional(readOnly = true)
    public Skill get(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Skill not found: " + id));
    }

    @Transactional
    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }
}
