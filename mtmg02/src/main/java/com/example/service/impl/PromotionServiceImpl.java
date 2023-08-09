package com.example.service.impl;

import com.example.entities.Promotion;
import com.example.filestorage.MyFile;
import com.example.repository.PromotionRepository;
import com.example.service.MyFileService;
import com.example.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    private MyFileService myFileService;

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion createPromotion(MultipartFile file, Promotion promotion) {
        MyFile myFile = new MyFile();
        myFile.setContentType(file.getContentType());
        myFile.setFileName(file.getOriginalFilename());
        try {
            myFile.setContent(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        promotion.setImage(myFileService.save(myFile));

        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion findById(Integer id) {
        return promotionRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        promotionRepository.delete(Objects.requireNonNull(promotionRepository.findById(id).orElse(null)));
    }

    @Override
    public Promotion update(MultipartFile file, Promotion promotion) {
        Promotion promotionDb = promotionRepository.findById(promotion.getId()).orElse(null);

        MyFile myFile = new MyFile();
        myFile.setId(promotionDb.getImage().getId());
        myFile.setContentType(file.getContentType());
        myFile.setFileName(file.getOriginalFilename());
        try {
            myFile.setContent(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        promotion.setImage(myFileService.save(myFile));
        return promotionRepository.save(promotion);
    }

}
