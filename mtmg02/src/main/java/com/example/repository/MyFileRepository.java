package com.example.repository;

import com.example.filestorage.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFileRepository extends JpaRepository<MyFile,Integer> {
}
