package com.example.memopractice2.repository;

import com.example.memopractice2.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository <Memo, Long> {
}
