package com.hyundai.project.controller;

import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.ProductDTO;
import com.hyundai.project.dto.ProductInsertDTO;
import com.hyundai.project.memberDAO.MemberDAO;
import com.hyundai.project.productDAO.ProductDAO;
import com.hyundai.project.service.MemberService;
import com.hyundai.project.service.ProductService;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/back")
public class BackProductRestController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping("/productSearch")
	public ResponseEntity<List<ProductDTO>> productSearch(@RequestBody HashMap<String, String> map, Model model) {
		String pname = map.get("pname");

		ResponseEntity<List<ProductDTO>> mem = null;
		try {
			List<ProductDTO> list = productService.productSearch(pname);
			model.addAttribute("productList", list);
			mem = new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);
			log.info(mem);
		} catch (Exception e) {
			e.printStackTrace();
			mem = new ResponseEntity<List<ProductDTO>>(HttpStatus.BAD_REQUEST);
		} // end try
		return mem;
	}

	@RequestMapping("/delProduct")
	public void delProduct(@RequestParam("pid") String pid) {
		productService.delColor(pid);
		productService.delProduct(pid);
	}

	@PutMapping("/mainproduct")
	public void mainProduct(@RequestBody HashMap<String, String> map) throws Exception {
		String pid = map.get("pid");
		int pstatus = Integer.parseInt(map.get("pstatus"));
		log.info("pid : " + pid + " " + "pstatus : " + pstatus);
		productService.updateMain(pid, pstatus);
	}

	@PutMapping("/product")
	public void updateProdut(@RequestBody ProductDTO product) throws Exception {
		log.info(product);
		productService.updateProduct(product);
	}

	@PostMapping("/product")
	public void insertProduct(@RequestBody ProductInsertDTO product) throws Exception {
		log.info(product);
		productService.insertProduct(product);
	}
}
