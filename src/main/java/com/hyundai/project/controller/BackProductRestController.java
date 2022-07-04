package com.hyundai.project.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.hyundai.project.config.AwsS3Config;
import com.hyundai.project.dto.*;
import com.hyundai.project.memberDAO.MemberDAO;
import com.hyundai.project.productDAO.ProductDAO;
import com.hyundai.project.service.AwsS3Service;
import com.hyundai.project.service.MemberService;
import com.hyundai.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/back")
public class BackProductRestController {

    @Autowired
    private ProductService productService;

    private final AwsS3Service awsS3Service;

    private AwsS3Config awsS3Config;

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
        // Map<String, List<Object>> size;

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

//        ProductInsertDTO pi = new ProductInsertDTO();
//        pi.setPid(product.get("pid").toString());
//        pi.setBname(product.get("bname").toString());
//        pi.setClarge(product.get("clarge").toString());
//        pi.setCmedium(product.get("cmedium").toString());
//        pi.setCsmall(product.get("csmall").toString());
//        pi.setPname(product.get("pname").toString());
//        pi.setPprice(product.get("pprice").toString());
//        pi.setPdetail(product.get("pdetail").toString());
//        pi.setCcolorcode(product.get("ccolorcode").toString());
//        pi.setCimage1(product.get("cimage1").toString());
//        pi.setCimage2(product.get("cimage2").toString());
//        pi.setCimage3(product.get("cimage3").toString());
//        pi.setCcolorimage(product.get("ccolorimage").toString());
//        pi.setCmatchpid(product.get("cmatchpid").toString());
//
//        List<ProductSizeDTO> ps = (List<ProductSizeDTO>) product.get("ssize");
//        // [{pid=Jm8, ccolorcode=Jm8, samount=0, ssize=90}, {pid=Jm8, ccolorcode=Jm8, samount=0, ssize=95}]
//        log.info(ps.size());
//        String[] array = ps.toString().split("=");
//        for(int i=0; i<array.length; i++)
//            log.info(array[i]);

        //log.info(ps.toString().split("=").toString());
//        List<ProductSizeDTO> insertps = new ArrayList<>();
//        log.info(ps.get(0).getPid().toString());
//        for(int i=0; i<ps.size(); i++) {
//            ProductSizeDTO temp = new ProductSizeDTO();
//            temp.setPid(ps.get(i).getPid());
//            temp.setCcolorcode(ps.get(i).getCcolorcode());
//            temp.setSamount(ps.get(i).getSamount());
//            temp.setSsize(ps.get(i).getSsize());
//            insertps.add(temp);
//        }
//        pi.setSsize(insertps);
//        ProductSizeDTO doc = new ProductSizeDTO(ps.get(0));
//        List<ProductSizeDTO> ps = (List<ProductSizeDTO>) product.get("ssize");
//        for(ProductSizeDTO l : ps) {
//            log.info(l);
//        }
//        pi.setSsize(ps);
        //log.info(pi);

        productService.insertProduct(product);
    }

    @GetMapping("/category")
    public List<ProductCategoryDTO> getCategory() throws Exception {
        return productService.getCategory();
    }
}
