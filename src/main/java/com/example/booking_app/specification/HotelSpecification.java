package com.example.booking_app.specification;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.example.booking_app.entity.Hotel;

public class HotelSpecification {
    public static Specification<Hotel> hasSimilarName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nameHotel")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Hotel> hasSimilarAddress(String address) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%");
    }

    public static Specification<Hotel> hasSimilarNameAndAddress(String name, String address) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("nameHotel")), "%" + name.toLowerCase() + "%"));
            }
            if (address != null) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
