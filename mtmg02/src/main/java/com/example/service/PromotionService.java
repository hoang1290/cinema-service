package com.example.service;

import com.example.entities.Promotion;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface PromotionService {

    List<Promotion> findAll();

    Promotion createPromotion(MultipartFile file, Promotion promotion);

    Promotion findById(Integer id);

    void delete(Integer id);

    Promotion update(MultipartFile file , Promotion promotion);

}
