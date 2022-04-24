package com.example.alami.repository;

import com.example.alami.model.entity.EntityTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RepositoryTransaction extends JpaRepository<EntityTransaction, Long> {

    List<EntityTransaction> findAllByDateBetween(Date from, Date to);

    List<EntityTransaction> findAllByUserId(Long id);

}
