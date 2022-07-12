package com.hyundai.project.memberDAO;

import java.util.List;

import com.hyundai.project.dto.CartUpdateDTO;
import org.apache.ibatis.annotations.Mapper;

import com.hyundai.project.dto.CartDTO;

@Mapper
public interface CartDAO {

    public List<CartDTO> getCart(String memail) throws Exception;

    public void insertCart(String memail, CartDTO cart) throws Exception;

    //public void deleteCart(String memail, String pid, String ccolorcode, String ssize) throws Exception;

    public void deleteCart(String memail, CartDTO cart) throws Exception;

    public void deleteAllCart(String memail) throws Exception;

    public void updateCart(String memail, CartUpdateDTO cart) throws Exception;

    public int getCartTotal(String memail) throws Exception;
}
