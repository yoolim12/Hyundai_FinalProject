package com.hyundai.project.service;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.CartUpdateDTO;
import com.hyundai.project.dto.QnaDTO;

import java.util.List;

public interface QnaService {
	
	public List<QnaDTO> getQna(String memail) throws Exception;
	
	public void insertQna(QnaDTO qna) throws Exception;

	public void deleteQna(int qid) throws Exception;

}
