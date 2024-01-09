package org.learning.springlamiapizzeriacrud.repository;

import org.learning.springlamiapizzeriacrud.model.Pizzeria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizzeria, Integer> {
}
