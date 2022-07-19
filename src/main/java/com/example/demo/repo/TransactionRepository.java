package com.example.demo.repo;

import com.example.demo.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
  @Query("select distinct t from User u " +
      "join u.transaction t " +
      "where u.name = :name " +
      "and ( :searchText is null OR lower(u.street) like %:searchText% )"
  )
  Page<Transaction> findAllCustom(
      @Param("name") String name,
      @Param("searchText") String searchText,
      Pageable pageable);
}