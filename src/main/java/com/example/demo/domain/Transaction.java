package com.example.demo.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "xxx_transaction")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int amount;

  @OneToMany(
      mappedBy = "transaction",
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER, // fetch = FetchType.LAZY,
      orphanRemoval = true
  )
  @Transient
  private List<User> participants = new ArrayList<>();

}
