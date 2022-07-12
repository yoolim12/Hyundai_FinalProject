package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.CartUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.memberDAO.CartDAO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;

    @Override
    public List<CartDTO> getCart(String memail) throws Exception {
        return cartDAO.getCart(memail);
    }

    @Override
    public void insertCart(String memail, CartDTO cart) throws Exception {
        cartDAO.insertCart(memail, cart);
    }

    @Override
    public void deleteCart(String memail, CartDTO cart) throws Exception {
        log.info(memail);
        log.info(cart);
        cartDAO.deleteCart(memail, cart);
    }

    @Override
    public void deleteAllCart(String memail) throws Exception {
        cartDAO.deleteAllCart(memail);
    }

    @Override
    public void updateCart(String memail, CartUpdateDTO cart) throws Exception {
        cartDAO.updateCart(memail, cart);
    }

    @Override
    public int getCartTotal(String memail) throws Exception {
        return cartDAO.getCartTotal(memail);
    }

}
