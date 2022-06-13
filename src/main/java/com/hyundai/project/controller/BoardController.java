package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hyundai.project.dto.BoardDTO;
import com.hyundai.project.dto.ClubAuthMemberDTO;
import com.hyundai.project.dto.PageDTO;
import com.hyundai.project.service.BoardService;

import lombok.extern.log4j.Log4j2;
/* 
* 작성자 : 문혁
* 
* 게시판 관련된 기능을 담당하는 컨트롤러
* 글쓰기, 수정, 삭제, 조회
*/
@Controller
@Log4j2
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;

	// 글쓰기 버튼 누를 시 매핑되는 게시판 입력 폼
	@GetMapping("/insert")
	public String insert(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO, Model model) {
		model.addAttribute("user", clubAuthMemberDTO);
		return "board/insert";
	}// end insert
	
	// 게시판 글쓰기 시 폼 양식 제출 시 post 방식으로 Board 테이블에 데이터 추가
	@PostMapping("/insert")
	public String insert(@ModelAttribute BoardDTO boardDTO, Model model) {
		log.info(boardDTO.toString());
		
		try {
			service.insertBoard(boardDTO);
			return "redirect:list?page=1";
		} catch (Exception e) {
			model.addAttribute("msg", "게시물 등록 오류입니다.");
			model.addAttribute("url", "javascript:history.back();");
			return "board/result";
		} // end try
	}// end insert..
	
	// 게시판 진입 시 게시글 목록 조회 페이지 매핑
	@GetMapping("/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int page) throws Exception {
		try {
			if(page == 0) {
				page = 1;
			}
			// 게시판 페이징에 필요한 데이터 구하는 로직
			int curPage = page;
			int startNum = 1 + (page - 1) * 10;
			int endNum = page * 10;
			long total = service.getTotal();
			PageDTO dto = new PageDTO(curPage, startNum, endNum, total);
			
			List<BoardDTO> list = service.getBoardList(dto);
			model.addAttribute("list", list);
			// 게시물 총 개수 조회 및 전달
			log.info(total);
			model.addAttribute("Paging",dto);
			log.info(dto.toString());
			return "board/list";
		} catch (Exception e) {
			throw e;
		} // end try
	}// end list
	
	// 작성자 추가 - 김승환
	// 게시글 제목 클릭시 게시글 상세 정보 조회 페이지 매핑
	// 게시판 수정, 삭제 기능 권한 체크에 시큐리티 연동
	@GetMapping("/detail")
	public String detail(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO, @RequestParam(defaultValue = "0") long no, Model model) throws Exception {
		try {
			BoardDTO boardDTO = service.getDetail(no);
			model.addAttribute("boardDTO", boardDTO);
			model.addAttribute("user", clubAuthMemberDTO);
			System.out.println(clubAuthMemberDTO.getEmail());
			clubAuthMemberDTO.setRole(clubAuthMemberDTO.getAuthorities().toString());
			System.out.println(clubAuthMemberDTO.getAuthorities());
			System.out.println(clubAuthMemberDTO.getRole());
			return "board/detail";
		} catch (Exception e) {
			model.addAttribute("msg", "게시판을 이용하려면 로그인해야 합니다.");
			model.addAttribute("url", "list");
			return "board/result";
		} // end try
	}// end detell..
	
	// 게시글 상세 정보 페이지에서 삭제 버튼 누를 시 이동하는 삭제 폼 매핑
	@GetMapping("/delete")
	public String delete(@RequestParam long no, Model model) {
		try {
			model.addAttribute("no", no);
			return "board/delete";
		} catch (Exception e) {
			throw e;
		} // end try
	}// end delete
	
	// 게시글 삭제 폼 양식 제출 시 post 방식으로 해당 게시글 데이터 삭제
	@PostMapping("/delete")
	public String delete(@ModelAttribute BoardDTO boardDTO, Model model) {
		log.info(boardDTO.toString());
		try {
			service.deleteBoard(boardDTO);
			model.addAttribute("msg", boardDTO.getNo() + "번 글이 삭제 되었습니다.");
			model.addAttribute("url", "list");
			return "board/result";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "board/result";
		} // end try
	}// end delete..
	
	// 게시글 상세 정보 페이지에서 수정 버튼 누를 시 이동하는 수정 폼 매핑
	@GetMapping("/update")
	public String update(@RequestParam long no, Model model) {
		try {
			BoardDTO boardDTO = service.getBoard(no);
			model.addAttribute("boardDTO", boardDTO);
			return "board/update";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "board/result";
		} // end try
	}// end update

	// 게시글 수정 폼 양식 제출 시 post 방식으로 해당 게시글 데이터 수정
	@PostMapping("/update")
	public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
		try {
			service.updateBoard(boardDTO);
			return "redirect:detail?no=" + boardDTO.getNo();
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "board/result";
		} // end try
	}// end update

}// end class
