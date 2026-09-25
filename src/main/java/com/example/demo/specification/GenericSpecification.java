package com.example.demo.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;
import java.util.ArrayList;

public class GenericSpecification {

  public static <T> Specification<T> searchByColumn(String keyword, List<String> columns) {

    return (root, query, criteriaBuilder) -> {
      if (keyword == null || keyword.trim().isEmpty() || columns == null || columns.isEmpty()) {
        return criteriaBuilder.conjunction();
      }

      String likeKeyword = "%" + keyword.toLowerCase() + "%";
      List<Predicate> predicates = new ArrayList<>();

      for (String col : columns) {
        Predicate predicate = criteriaBuilder.like(
          criteriaBuilder.lower(root.get(col).as(String.class)),
          likeKeyword);

        predicates.add(predicate);
      }

      return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    };
  }
}
