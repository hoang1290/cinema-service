package com.example.controller;

import com.example.entities.CinemaRoom;
import com.example.entities.Promotion;
import com.example.service.impl.PromotionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PromotionController {
    @Autowired
    PromotionServiceImpl promotionService;

    @GetMapping("/promotionList")
    public String getPromotionList(Model model) {
        model.addAttribute("promotions", promotionService.findAll());
        return "promotionList";
    }

    @GetMapping("/addPromotion")
    public String showAddPromotionForm(Model model) {
        model.addAttribute("addPromotion", new Promotion());
        return "addPromotion";
    }

    @PostMapping("/addPromotion")
    public String createPromotion(
            Model model,
            @ModelAttribute("promotion") Promotion promotion,
            MultipartFile banner
    ) {
        promotionService.createPromotion(banner, promotion);
        model.addAttribute("promotions", promotionService.findAll());
        return "promotionList";
    }

    @GetMapping("/editPromotion/{id}")
    public String editPromotion(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("promotion",promotionService.findById(id));
        return "editPromotion";
    }

    @PostMapping("/updatePromotion")
    public String updatePromotion(Model model,
                                  @RequestParam("banner") MultipartFile bannerFile,
                                  @ModelAttribute Promotion promotion
    ) {
        promotionService.update(bannerFile, promotion);
        model.addAttribute("promotions", promotionService.findAll());
        return "promotionList"; //Trả về list Promotion
    }

    @GetMapping("/deletePromotion/{id}")
    public String deletePromotion(
            Model model,
            @PathVariable("id") Integer id
    ) {
        promotionService.delete(id);
        model.addAttribute("promotions", promotionService.findAll());
        return "promotionList"; //Trả về list Promotion
    }

}
