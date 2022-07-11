package com.hyundai.project.memberDAO;

import com.hyundai.project.dto.CartDTO;
import com.hyundai.project.dto.CartUpdateDTO;
import com.hyundai.project.dto.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaDAO {

	public List<QnaDTO> getQna(String memail) throws Exception;

	public void insertQna(QnaDTO qna) throws Exception;

	public void deleteQna(int qid) throws Exception;

	public QnaDTO getQnaDetail(int qid) throws Exception;

	public void updateQna(QnaDTO qna) throws Exception;

	public int getQnaCount(String memail) throws Exception;

	public List<QnaDTO> getAllQna() throws Exception;

	public void updateQnaReply(QnaDTO qna) throws Exception;
}
