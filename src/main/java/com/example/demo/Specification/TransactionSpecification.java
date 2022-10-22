package com.example.demo.Specification;

import com.example.demo.FilterParam.TransactionFilterParam;
import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class TransactionSpecification {

    public static Specification<Transaction> getFilteredTransaction(TransactionFilterParam params) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.getCustomerID() != null) {
                predicates.add(criteriaBuilder.equal(root.get("customerID"), params.getCustomerID()));
            }

            if (params.getDescription() != null) {
                predicates.add(criteriaBuilder.equal(root.get("description"), params.getDescription()));
            }

            if (params.getAccountNumber() != null) {
                predicates.add(criteriaBuilder.equal(root.get("accountNumber"), params.getAccountNumber()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
