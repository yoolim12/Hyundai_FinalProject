package com.hyundai.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.StreamingDTO;
import com.hyundai.project.memberDAO.StreamingDAO;

import lombok.extern.log4j.Log4j2;

/* 작성자 :  문혁
 * 메인 페이지에 관련된 DB 호출을 관리하는 클래스
 */
@Service
@Log4j2
public class StreamingServiceImpl implements StreamingService {

    @Autowired
    private StreamingDAO dao;

    @Override
    public List<StreamingDTO> getList() throws Exception {
        log.info("스트리밍 목록 조회");
        return dao.getList();
    }

    // 메인 페이지 상품 리스트 조회
    @Override
    public void uploadStreaming(StreamingDTO dto) throws Exception {
        log.info("스트리밍 목록 추가");
        dao.insertStreaming(dto);
    }

    ;

    // 메인 페이지 매거진 리스트 조회
    @Override
    public void deleteStreaming(int sno) throws Exception {
        log.info("스트리밍 목록 제거");
        dao.deleteStreaming(sno);
    }

    @Override
    public StreamingDTO getReplay(int sno) throws Exception {
        log.info("다시보기");
        return dao.getReplay(sno);
    }

}//end class
