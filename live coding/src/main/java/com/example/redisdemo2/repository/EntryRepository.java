package com.example.redisdemo2.repository;

import com.example.redisdemo2.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {

    List<Entry> findByUserId(Integer userId);
}
