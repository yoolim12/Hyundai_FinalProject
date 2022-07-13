package com.hyundai.project.controller;

import com.hyundai.project.config.AwsS3Config;
import com.hyundai.project.dto.*;
import com.hyundai.project.service.AwsS3Service;
import com.hyundai.project.dto.MemberDTO;
import com.hyundai.project.dto.ProductDTO;
import com.hyundai.project.dto.ProductBackDTO;
import com.hyundai.project.memberDAO.MemberDAO;
import com.hyundai.project.productDAO.ProductDAO;
import com.hyundai.project.service.MemberService;
import com.hyundai.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/back")
public class BackProductRestController {

    @Autowired
    private ProductService productService;

    private final AwsS3Service awsS3Service;

    @PostMapping("/productSearch")
	  public ResponseEntity<List<ProductBackDTO>> productSearch(@RequestBody HashMap<String, String> map) {
		String pname = map.get("pname");

		ResponseEntity<List<ProductBackDTO>> mem = null;
		try {
			List<ProductBackDTO> list = productService.productSearch(pname);
			mem = new ResponseEntity<List<ProductBackDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			mem = new ResponseEntity<List<ProductBackDTO>>(HttpStatus.BAD_REQUEST);
		} // end try
		  return mem;
	  }

	@DeleteMapping("/delProduct")
	public String delProduct(@RequestBody HashMap<String, String> map) throws Exception {
		try {
			productService.delProduct(map.get("pid"));
		} catch (Exception e) {
			e.printStackTrace();
			return "삭제에 실패하였습니다.";
		}
		
		return "Product Delete Success";
	}

	@GetMapping("/backProduct/{pid}")
	public ResponseEntity<List<ProductBackDTO>> backProductList(@PathVariable("pid") String pid, Model model) {
		ResponseEntity<List<ProductBackDTO>> product = null;
		try {
			List<ProductBackDTO> list = productService.backProductList(pid);
			model.addAttribute("backProduct", list);
			product = new ResponseEntity<List<ProductBackDTO>>(list, HttpStatus.OK);
			log.info(product);
		} catch (Exception e) {
			e.printStackTrace();
			product = new ResponseEntity<List<ProductBackDTO>>(HttpStatus.BAD_REQUEST);
		}
		return product;
	}
	
	@PutMapping(value = "/product")
	public String userModify(@RequestBody HashMap<String, String> map) {
		ProductBackDTO dto = new ProductBackDTO();
		dto.setPid(map.get("pid"));
		dto.setPprice(map.get("pprice"));
		dto.setPstatus(Integer.parseInt(map.get("pstatus")));
		productService.productModify(dto);
		return "modify success";
	}

    @PostMapping("/product")
    public void insertProduct(@RequestPart(value = "key") ProductInsertDTO product, @RequestPart(value = "cimage", required = false) List<MultipartFile> cimage,
                            @RequestPart(value = "ccolorimage", required = false) MultipartFile ccolorimage) throws Exception {

        // S3 이미지 업로드 (Cimage1, Cimage2, Cimage3)
        List<String> fileNameList = awsS3Service.uploadFile(cimage);
        log.info(fileNameList);

        for (int i = 0; i < fileNameList.size(); i++) {
            if (i > 2) break;
            if(i == 0) product.setCimage1(fileNameList.get(i));
            if(i == 1) product.setCimage2(fileNameList.get(i));
            if(i == 2) product.setCimage3(fileNameList.get(i));
        }

        // S3 이미지 업로드 (Ccolorimage)
        List<MultipartFile> ccolor = new ArrayList<>();
        ccolor.add(ccolorimage);
        List<String> colorimage = awsS3Service.uploadFile(ccolor);
        product.setCcolorimage(colorimage.get(0));
        log.info(product);

        productService.insertProduct(product);
    }

    @GetMapping("/category")
    public List<ProductCategoryDTO> getCategory() throws Exception {
        return productService.getCategory();
    }
}
