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
@RequestMapping("/cart/*")
@RestController
public class CartRestController {
    @Autowired
    private CartService service;

    @GetMapping(value = "/{memail}")
    public ResponseEntity<List<CartDTO>> getCart(@PathVariable("memail") String memail) {
        ResponseEntity<List<CartDTO>> entry = null;

        try {
            entry = new ResponseEntity<List<CartDTO>>(service.getCart(memail), HttpStatus.OK);
            log.info(entry);
        } catch (Exception e) {
            e.printStackTrace();
            entry = new ResponseEntity<List<CartDTO>>(HttpStatus.BAD_REQUEST);
        } // end try
        return entry;
    }// end list

    @PostMapping("/{memail}")
    public String insertCart(@PathVariable("memail") String memail, @RequestBody CartDTO cart) throws Exception {

        try {
            service.insertCart(memail, cart);
        } catch (Exception e) {
            e.printStackTrace();
        } // end try
        return "success";
    } // end findid

    @DeleteMapping("/{memail}")
    public String deleteCart(@PathVariable("memail") String memail, @RequestBody CartDTO cart) throws Exception {

        try {
            log.info(memail, cart);
            service.deleteCart(memail, cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @DeleteMapping("/all/{memail}")
    public String deleteALLCart(@PathVariable("memail") String memail) throws Exception {

        try {
            service.deleteAllCart(memail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PutMapping("/{memail}")
    public String updateCart(@PathVariable("memail") String memail, @RequestBody CartUpdateDTO cart) {
        try {
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