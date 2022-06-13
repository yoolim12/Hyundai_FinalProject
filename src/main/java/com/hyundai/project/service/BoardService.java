package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.BoardAttachDTO;
import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.PageDTO;
/* 작성자 :  문혁
 * 게시판에 관련된 DB 호출을 관리하는 인터페이스
 * 글쓰기, 게시판 목록 및 상세 조회, 삭제, 수정, 총 게시글 수 조회, 게시물의 첨부파일 목록 조회
 */
public interface BoardService {
	void insertBoard(BoardDTO boardDTO) throws Exception;
	
	List<BoardDTO> getBoardList(PageDTO pageDTO) throws Exception;
	
	BoardDTO getDetail(long no) throws Exception;
	
	void deleteBoard(BoardDTO boardDTO) throws Exception;
	
	BoardDTO getBoard(long no) throws Exception;
	
	void updateBoard(BoardDTO boardDTO) throws Exception;
	
	long getTotal() throws Exception;
	
	List<BoardAttachDTO> getAttachList(Long bno) throws Exception;

}//end int
