package com.cognizant.hql.criteria.service;

import com.cognizant.hql.criteria.model.Employee;
import com.cognizant.hql.criteria.search.EmployeeSearchCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeSearchService {

    private final EntityManager entityManager;

    public EmployeeSearchService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public List<Employee> search(EmployeeSearchCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        criteria.minSalary().ifPresent(min ->
                predicates.add(cb.greaterThanOrEqualTo(root.get("salary"), min)));
        criteria.maxSalary().ifPresent(max ->
                predicates.add(cb.lessThanOrEqualTo(root.get("salary"), max)));
        criteria.permanent().ifPresent(p ->
                predicates.add(cb.equal(root.get("permanent"), p)));
        criteria.nameContains().ifPresent(fragment ->
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + fragment.toLowerCase() + "%")));

        if (criteria.departmentName().isPresent()) {
            Join<Object, Object> dept = root.join("department", JoinType.INNER);
            predicates.add(cb.equal(dept.get("name"), criteria.departmentName().get()));
        }

        if (criteria.skillName().isPresent()) {
            Join<Object, Object> skills = root.join("skills", JoinType.INNER);
            predicates.add(cb.equal(skills.get("name"), criteria.skillName().get()));
        }

        cq.select(root).distinct(true).where(predicates.toArray(Predicate[]::new)).orderBy(cb.asc(root.get("name")));

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
