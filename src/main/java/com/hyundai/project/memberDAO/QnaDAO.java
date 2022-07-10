package com.hyundai.project.memberDAO;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.CartUpdateDTO;
import com.hyundai.project.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaDAO {

	//public List<CartDTO> getCart(String memail) throws Exception;

	public void insertQna(QnaDTO qna) throws Exception;

//	//public void deleteCart(String memail, String pid, String ccolorcode, String ssize) throws Exception;
//
//	public void deleteCart(String memail, CartDTO cart) throws Exception;
//
//	public void deleteAllCart(String memail) throws Exception;
//
//	public void updateCart(String memail, CartUpdateDTO cart) throws Exception;
//
//	public int getCartTotal(String memail) throws Exception;
}
