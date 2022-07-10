package com.hyundai.project.service;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.CartUpdateDTO;
import com.hyundai.project.dto.QnaDTO;
import com.hyundai.project.memberDAO.CartDAO;
import com.hyundai.project.memberDAO.QnaDAO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaDAO qnaDAO;

	@Override
	public List<QnaDTO> getQna(String memail) throws Exception {
		return qnaDAO.getQna(memail);
	}

	@Override
	public void insertQna(QnaDTO qna) throws Exception {
		qnaDAO.insertQna(qna);
	}

	@Override
	public void deleteQna(int qid) throws Exception {
		qnaDAO.deleteQna(qid);
	}
}
