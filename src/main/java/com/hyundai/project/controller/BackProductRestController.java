package com.hyundai.project.controller;

import com.hyundai.project.config.AwsS3Config;
import com.hyundai.project.dto.*;
import com.hyundai.project.service.AwsS3Service;
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
