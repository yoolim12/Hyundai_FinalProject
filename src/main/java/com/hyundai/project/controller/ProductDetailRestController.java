package com.hyundai.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDTO;
import com.hyundai.project.dto.ProductSizeDTO;
import com.hyundai.project.service.ProductService;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@Controller
public class ProductDetailRestController {
    @Setter(onMethod_ = @Autowired)
    private ProductService service;

    @GetMapping(value = "/detail/{pid}/{ccolorcode}")
    public ResponseEntity<ProductDTO> getProductDetail(@PathVariable("pid") String pid,
                                                       @PathVariable("ccolorcode") String ccolorcode) {
        ResponseEntity<ProductDTO> entry = null;
        try {
            entry = new ResponseEntity<ProductDTO>(service.getProductDetail(pid, ccolorcode), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entry = new ResponseEntity<ProductDTO>(HttpStatus.BAD_REQUEST);
        } // end try
        return entry;
    }// end list

    @GetMapping(value = "/color/{pid}/{ccolorcode}")
    public ResponseEntity<List<ProductColorDTO>> getProductColorDetail(@PathVariable("pid") String pid, @PathVariable("ccolorcode") String ccolorcode) {
        ResponseEntity<List<ProductColorDTO>> entry = null;
        try {
            entry = new ResponseEntity<List<ProductColorDTO>>(service.getProductColorDetail(pid, ccolorcode), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entry = new ResponseEntity<List<ProductColorDTO>>(HttpStatus.BAD_REQUEST);
        } // end try
        return entry;
    }

    @GetMapping(value = "/size/{pid}/{ccolorcode}")
    public ResponseEntity<List<ProductSizeDTO>> getProductSize(@PathVariable("pid") String pid, @PathVariable("ccolorcode") String ccolorcode) {
        ResponseEntity<List<ProductSizeDTO>> entry = null;
        try {
            entry = new ResponseEntity<List<ProductSizeDTO>>(service.getProductSize(pid, ccolorcode), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entry = new ResponseEntity<List<ProductSizeDTO>>(HttpStatus.BAD_REQUEST);
        } // end try
        return entry;
    }

}
