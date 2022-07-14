package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.QnaDTO;

public interface QnaService {

    public List<QnaDTO> getQna(String memail) throws Exception;

    public void insertQna(QnaDTO qna) throws Exception;

    public void deleteQna(int qid) throws Exception;

    public QnaDTO getQnaDetail(int qid) throws Exception;

    public void updateQna(QnaDTO qna) throws Exception;

    public int getQnaCount(String memail) throws Exception;

    public List<QnaDTO> getAllQna() throws Exception;

    public void updateQnaReply(QnaDTO qna) throws Exception;

    public List<QnaDTO> getQnaInfo(String memail) throws Exception;

}
