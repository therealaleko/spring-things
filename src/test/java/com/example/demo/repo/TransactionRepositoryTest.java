package com.example.demo.repo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TransactionRepositoryTest {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void searchWithNullText() {
    var u1 = userRepository.findById(1).orElseThrow();
    var u2 = userRepository.findById(2).orElseThrow();
    var u3 = userRepository.findById(3).orElseThrow();
    var t1 = transactionRepository.findById(1).orElseThrow();

    t1.getParticipants().add(u1);
    t1.getParticipants().add(u2);
    t1.getParticipants().add(u3);

    var x = transactionRepository.findAllCustom("test user 1", null, Pageable.ofSize(3));

    Assertions.assertThat(x.getTotalElements()).isEqualTo(1);
  }
}