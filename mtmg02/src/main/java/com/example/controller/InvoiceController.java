package com.example.controller;

import com.example.auth.CustomUserDetails;
import com.example.entities.Account;
import com.example.entities.Invoice;
import com.example.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/point-history")
    public String getPointHistory(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {

            Account account = userDetails.getAccount();
            model.addAttribute("invoices", invoiceService.findByAccount(account.getId()));
        }
        return "pointHistory";
    }

    @GetMapping("/booking-ticket")
    public String getBookTicket(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {

            Account account = userDetails.getAccount();
            model.addAttribute("invoices", invoiceService.findByAccount(account.getId()));
        }
        return "bookedTicket";
    }

    @GetMapping("/userConfirmTicket/{mo}/{schtime}")
    public String getUserConfirmTicket(
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {

            Account account = userDetails.getAccount();
            model.addAttribute("account", account);
        }


        return "userConfirmTicket";
    }

    @PostMapping("/userConfirmTicket")
    public String getTicket() {

        return "redirect:bookingList";
    }

    @GetMapping("/bookingList")
    public String getBookingList(Model model) {
        List<Invoice> invoices = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            Account account = userDetails.getAccount();
            model.addAttribute("account", account);

            invoices = invoiceService.findByAccount(account.getId());
            model.addAttribute("invoiceAccountList", invoices);
        }
        return "bookingList";
    }

//    @PostMapping("/bookingList")
//    public String bookingListPost(
//            Model model,
//            @RequestParam("selectChoice") String selectChoice,
//            @ModelAttribute Invoice invoice
//    ) {
//        if (selectChoice.equals("agree")) {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
//                Account account = userDetails.getAccount();
//                model.addAttribute("account", account);
//
//                Invoice invoice1 = invoiceService.findById(account, invoice.getInvoiceId());
//                invoice1.setStatus(true);
//                invoiceService.saveInvoice(invoice1);
//            }
//
//            return "bookingList";
//        } else if (selectChoice.equals("disagree")) {
//            return "bookingList";
//        }
//        return "bookingList";
//    }

//    @GetMapping("/ticketInformation/{invoiceId}")
//    public String editProfile(
//            @PathVariable("invoiceId") Integer id,
//            Model model
//    ) {
//        List<Invoice> invoiceList = new ArrayList<>();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
//            Account account = userDetails.getAccount();
//            model.addAttribute("account", account);
//            invoiceList = bookedTicketService.getById(account);
//            model.addAttribute("invoiceAccList", invoiceList);
//            Invoice invoice = bookedTicketService.getById(id);
//            model.addAttribute("invoice", invoice);
//        }
//        return "ticketInformation";
//    }


}
