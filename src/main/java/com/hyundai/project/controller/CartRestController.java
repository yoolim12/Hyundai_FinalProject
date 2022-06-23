package com.hyundai.project.controller;

import java.util.List;
import java.util.Map;

import com.hyundai.project.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.service.CartService;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@RequestMapping("/cart")
@RestController
public class CartRestController {
    @Autowired
    private CartService service;

    @GetMapping("/list")
    public ResponseEntity<List<CartDTO>> getCart(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO) {
        ResponseEntity<List<CartDTO>> entry = null;
        String memail;
        try {
            if (oauthMemberDTO == null) {
                memail = authMemberDTO.getMemail();
            }
            else {
                memail = oauthMemberDTO.getEmail();
            }
            entry = new ResponseEntity<List<CartDTO>>(service.getCart(memail), HttpStatus.OK);
            log.info(entry);
        } catch (Exception e) {
            e.printStackTrace();
            entry = new ResponseEntity<List<CartDTO>>(HttpStatus.BAD_REQUEST);
        } // end try
        return entry;
    }// end list

    @PostMapping
    public String insertCart(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, @RequestBody CartDTO cart) throws Exception {
        String memail;
        try {
            if (oauthMemberDTO == null) {
                memail = authMemberDTO.getMemail();
            }
            else {
                memail = oauthMemberDTO.getEmail();
            }
            service.insertCart(memail, cart);
        } catch (Exception e) {
            e.printStackTrace();
        } // end try
        return "success";
    } // end findid

    @DeleteMapping
    public String deleteCart(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, @RequestBody CartDTO cart) throws Exception {
        String memail;
        try {
            if (oauthMemberDTO == null) {
                memail = authMemberDTO.getMemail();
            }
            else {
                memail = oauthMemberDTO.getEmail();
            }
            log.info(memail, cart);
            service.deleteCart(memail, cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @DeleteMapping("/all")
    public String deleteALLCart(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO) throws Exception {
        String memail;
        try {
            if (oauthMemberDTO == null) {
                memail = authMemberDTO.getMemail();
            }
            else {
                memail = oauthMemberDTO.getEmail();
            }
            service.deleteAllCart(memail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PutMapping
    public String updateCart(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO, @RequestBody CartUpdateDTO cart) {
        String memail;
        try {
            if (oauthMemberDTO == null) {
                memail = authMemberDTO.getMemail();
            }
            else {
                memail = oauthMemberDTO.getEmail();
            }
            log.info(cart);
            service.updateCart(memail, cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PostMapping("/station")
    public OrderStationDTO orderStation(@RequestBody OrderStationDTO station) {
        return station;
    }

    @GetMapping("/total")
    public int getCartTotal(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, @AuthenticationPrincipal ClubAuthMemberDTO oauthMemberDTO) throws Exception {
        String memail;
        if(authMemberDTO == null && oauthMemberDTO == null) return 0;
        if (oauthMemberDTO == null) {
            memail = authMemberDTO.getMemail();
        }
        else {
            memail = oauthMemberDTO.getEmail();
        }
        return service.getCartTotal(memail);
    }

} // end class