package com.hyundai.project.controller;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hyundai.project.dto.AttachFileDTO;
import com.hyundai.project.dto.BoardAttachDTO;
import com.hyundai.project.service.BoardService;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
/* 
* 작성자 : 문혁
* 
* 게시판 글쓰기 시 파일 업로드 기능에 관한 REST 컨트롤러
* 파일 업로드, 파일 조회, 파일 삭제
*/
@RestController
@Log4j2
public class UploadController {
	// application.properties 변수
	@Value("${com.hyundai.upload.path}")
	private String uploadPath;

	@Autowired
	private BoardService service;
	
	// 이름이 중복된 파일 방지를 위해 날짜 폴더 생성
	// 폴더 이름 만드는 메서드
	private String makeFolder() {
		// 파일 첨부 시 해당 날짜 생성패턴
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// 날짜 구분
		String folderPath = str.replace("/", File.separator);
		// 폴더 생성
		File uploadPathFolder = new File(uploadPath, folderPath);
		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		}
		log.info(folderPath);
		return folderPath;
	}// end make..
	
	// 파일 업로드 시 post 방식으로 첨부 파일의 uuid, 폴더 경로, 파일 이름, 첨부파일이 등록된 게시글 번호 정보를 받아
	// Board_attach 테이블에 첨부파일 데이터 추가
	@PostMapping("/uploadAjax")
	public ResponseEntity<List<AttachFileDTO>> uploadFile(MultipartFile[] uploadFile) {
		// JSON으로 보낸 객체 저장
		List<AttachFileDTO> list = new ArrayList<>();
		// 하나 이상의 파일이 업로드될 수 있어 for 문을 통해 하나씩 추가
		for (MultipartFile i : uploadFile) {
			// 이미지 파일 검사
			if (i.getContentType().startsWith("image") == false) {
				log.warn("this file is not image type"); // 이미지 파일이 아니라면 403에러
				return new ResponseEntity<>(HttpStatus.FORBIDDEN); // for문 나가기;
			} // end if

			String originalName = i.getOriginalFilename();
			log.info("fileName :" + originalName);
			// 폴더 생성
			String folderPath = makeFolder();
			log.info("folderPath :" + folderPath);
			// 랜덤 uuid 생성
			String uuid = UUID.randomUUID().toString();
			// 파일 이름 완성
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + originalName;
			log.info(saveName);
			// 경로 저장
			Path savePath = Paths.get(saveName);
			try { // 실제 저장
				// 원본 이미지 파일 저장
				i.transferTo(savePath);
				String thumnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_"
						+ originalName;
				File thumbailFile = new File(thumnailSaveName);
				// 섬네일 파일 생성 100 X 100 생성 input,output, 가로, 세로
				Thumbnailator.createThumbnail(savePath.toFile(), thumbailFile, 100, 100);
				// 각 파일 정보 리스트에 클래스로 저장
				list.add(new AttachFileDTO(originalName, folderPath, uuid));
			} catch (Exception e) {
				e.printStackTrace();
			} // end try
		} // end for
		// JSON 으로 반환 파일 정보 담은 리스트
		log.info(list); // JSON으로 보낼값
		return new ResponseEntity<>(list, HttpStatus.OK);
	}// end void
	
	// 파일 업로드 시와 상세 조회 시 첨부된 파일을 화면에 출력할 수 있도록 하는 매핑
	@GetMapping("/board/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		ResponseEntity<byte[]> result = null;
		try {
			// 브라우저에서 파일이름이 오기때문에 디코딩
			String srcFileName = URLDecoder.decode(fileName, "UTF-8");
			log.info(srcFileName);
			File file = new File(uploadPath + File.separator + srcFileName);
			log.info(file);
			// 헤더 생성 브라우져에 보내야 하므로
			HttpHeaders headers = new HttpHeaders();
			// 헤더에 콘텐츠 타입 설정
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			//
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// 500번 에러 보냄
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} // end try

		return result;
	}// end getfile..
	
	// 글쓰기 폼에서 파일 옆 X(삭제)버튼 누를 시 post 방식으로 해당 첨부파일 삭제
	// 파일이 업로드되는 로컬 폴더에서도 해당 파일 삭제
	@PostMapping("/removeFile")
	public ResponseEntity<Boolean> removeFile(String fileName) {
		String srcFileName = null;
		log.info("removeFile-----");

		try {
			srcFileName = URLDecoder.decode(fileName, "UTF-8");
			log.info("srcFileName: " + srcFileName);
			// 원본 파일 삭제
			File file = new File(uploadPath + File.separator + srcFileName);
			boolean result = file.delete();
			log.info("파일이름");

			log.info(file.toString());

			// 섬네일 파일 삭제 s_ +원본파일
			File thumnailfile = new File(file.getParent(), "s_" + file.getName());
			log.info("thumfile: " + "s_" + file.getName());
			result = thumnailfile.delete();
			// 파일 삭제 결과와 200번 코드
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();

			// 500번 에러 반환
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		} // end try

	}// end remove..
	
	// 게시글 삭제 시 해당 게시글에 속해 있는 모든 이미지 파일 삭제
	// 파일이 업로드되는 로컬 폴더에서도 해당 파일들 모두 삭제
	@PostMapping("/removeAll")
	public String removeAll(long bno) {
		try {
			List<BoardAttachDTO> fileList = service.getAttachList(bno);
			if (fileList == null) {
				return "해당 게시글에 파일이 존재하지 않습니다.";
			} else {
				for (int i = 0; i < fileList.size(); i++) {
					String fileName = fileList.get(i).getFolderPath() + "\\" + fileList.get(i).getUuid() + "_"
							+ fileList.get(i).getFileName();
					log.info("removeFile-----" + fileName);
					log.info(uploadPath);

					File file = new File(uploadPath + File.separator + fileName);
					file.delete();
					// 섬네일 파일 삭제 s_ +원본파일
					File thumnailfile = new File(file.getParent(), "s_" + file.getName());
					thumnailfile.delete();
					log.info(file.toString());

				}
				return "해당 게시글의 모든 파일을 삭제했습니다.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "파일 삭제 중 오류 발생";
		}

	}// end remove..
}
// end class
