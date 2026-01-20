package org.svarsik.funsiesspr.repository;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.svarsik.funsiesspr.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {



}
