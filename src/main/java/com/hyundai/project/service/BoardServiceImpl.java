package com.hyundai.project.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyundai.project.dao.BoardAttachDAO;
import com.hyundai.project.dao.BoardDAO;
import com.hyundai.project.dto.BoardAttachDTO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.PageDTO;

import lombok.extern.log4j.Log4j2;
/* 작성자 :  문혁
 * 게시판에 관련된 DB 호출을 관리하는 클래스
 */
@Service
@Log4j2
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	@Autowired
	private BoardAttachDAO attachDAO;
	
	// 게시물 입력 시 첨부파일이 존재할 경우 첨부파일도 함께 DB에 추가되어야 하므로 트랜잭션 처리
	@Transactional
	@Override
	public void insertBoard(BoardDTO boardDTO) throws Exception {
		try {
			// Board 테이블에 게시물 데이터 추가
			boardDAO.insertBoard(boardDTO);
			log.info("게시물 입력 성공");
			// 게시물에 첨부파일 없을 시 메서드 종료
			if (boardDTO.getAttachList() == null || boardDTO.getAttachList().size() <= 0) {
				return;
			}
			// 첨부파일 존재할 경우 Board_attach 테이블에 첨부파일 데이터 추가
			boardDTO.getAttachList().forEach(attach -> {
				attach.setBno(boardDTO.getNo());
				log.info(boardDTO.getNo());
				try {
					attachDAO.insert(attach);
					log.info("이미지 파일 업로드 성공");
				} catch (SQLException e) {
					log.info(e.getMessage());
				}
			});
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end insert..
	
	// 게시판 페이지 목록 조회
	@Override
	public List<BoardDTO> getBoardList(PageDTO dto) throws Exception {
		try {
			
			return boardDAO.getBoardList(dto);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end getAList...
	
	// 게시판 목록에서 게시글 제목 클릭시 게시글 상세 조회
	@Override
	public BoardDTO getDetail(long no) throws Exception {
		try {
			BoardDTO boardDTO = boardDAO.getDetail(no);
			if (boardDTO == null) {
				throw new RuntimeException("없는 게시물이거나 접근 권한이 없습니다.");
			}
			log.info("파일목록 넣기전"+boardDTO); 
			boardDTO.setAttachList(attachDAO.findByBno(no));
			log.info(boardDTO);
			return boardDTO;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end getDe..
	
	// 게시글 삭제 시 게시글에 포함된 첨부파일도 DB에서 삭제되어야 하므로 트랜잭션 처리
	@Transactional
	@Override
	public void deleteBoard(BoardDTO boardDTO) throws Exception {
		try {
			// FK 제약으로 인해 첨부파일 데이터 먼저 삭제
			attachDAO.delete(boardDTO.getNo());
			// 게시글 데이터 삭제
			if (boardDAO.deleteBoard(boardDTO) == 0) {
				throw new RuntimeException("비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end delete..
	
	@Override
	public BoardDTO getBoard(long no) throws Exception {
		try {
			BoardDTO boardDTO = boardDAO.getDetail(no);
			if (boardDTO == null) {
				throw new RuntimeException("없는 게시물이거나 접근 권한이 없습니다.");
			}
			return boardDTO;
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end getArt..
	
	// 게시글 수정
	@Override
	public void updateBoard(BoardDTO boardDTO) throws Exception {
		try {
			if (boardDAO.updateBoard(boardDTO) == 0) {
				throw new RuntimeException("게시물이 존재하지 않거나 비밀번호가 일치하지 않습니다.");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end updateA..
	
	// 게시글 총 개수 조회
	@Override
	public long getTotal() throws Exception {
		try {
			return boardDAO.getTotal();
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end getTotal
	
	// 해당 게시글의 첨부파일 목록 조회
	@Override
	public List<BoardAttachDTO> getAttachList(Long bno) throws Exception {
		try {
			return attachDAO.findByBno(bno);
		} catch (Exception e) {
			log.info(e.getMessage());
			throw e;
		} // end try
	}// end getAttachList

}// end class
