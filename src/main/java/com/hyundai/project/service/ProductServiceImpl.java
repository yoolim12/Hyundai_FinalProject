package com.hyundai.project.service;

import java.util.List;

import com.hyundai.project.dto.ProductInsertDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyundai.project.dto.ProductColorDTO;
import com.hyundai.project.dto.ProductDTO;
import com.hyundai.project.dto.ProductSizeDTO;
import com.hyundai.project.productDAO.ProductDAO;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public ProductDTO getProductDetail(String pid, String ccolorcode) throws Exception {
        return productDAO.getProductDetail(pid, ccolorcode);
    }

    @Override
    public List<ProductColorDTO> getProductColorDetail(String pid, String ccolorcode) throws Exception {
        log.info("produtDAO");
        return productDAO.getProductColorDetail(pid, ccolorcode);
    }

    @Override
    public List<ProductSizeDTO> getProductSize(String pid, String ccolorcode) throws Exception {
        return productDAO.getProductSize(pid, ccolorcode);
    }

    @Override
    public void delProduct(String pid) {
        productDAO.delProduct(pid);
    }

    @Override
    public void delColor(String pid) {
        productDAO.delColor(pid);
    }

    @Override
    public List<ProductDTO> productSearch(String pname) {
        return productDAO.productSearch(pname);
    }

    @Override
    public void updateMain(String pid, int pstatus) throws Exception {
        productDAO.updateMain(pid, pstatus);
    }

    @Override
    public void updateProduct(ProductDTO product) throws Exception {
        productDAO.updateProduct(product);
    }

    // Transaction 처리
    @Override
    public void insertProduct(ProductInsertDTO pi) throws Exception {
        log.info(pi);
        ProductDTO product = new ProductDTO();
        product.setPid(pi.getPid());
        product.setClarge(pi.getClarge());
        product.setCmedium(pi.getCmedium());
        product.setCsmall(pi.getCsmall());
        product.setBname(pi.getBname());
        product.setPname(pi.getPname());
        product.setPprice(pi.getPprice());
        product.setPdetail(pi.getPdetail());
        log.info(product);
        productDAO.insertProduct(product);
        for(int i=0; i<pi.getPcolor().size(); i++) {
            ProductColorDTO pcolor = new ProductColorDTO();
            pcolor.setPid(pi.getPid());
            pcolor.setCcolorcode(pi.getPcolor().get(i).getCcolorcode());
            pcolor.setCimage1(pi.getPcolor().get(i).getCimage1());
            pcolor.setCimage2(pi.getPcolor().get(i).getCimage2());
            pcolor.setCimage3(pi.getPcolor().get(i).getCimage3());
            pcolor.setCcolorimage(pi.getPcolor().get(i).getCcolorimage());
            pcolor.setCmatchpid(pi.getPcolor().get(i).getCmatchpid());
            productDAO.insertProductColor(pcolor);
            for(int j=0; j<pi.getPcolor().get(i).getPsize().size(); j++) {
                ProductSizeDTO psize = new ProductSizeDTO();
                psize.setPid(pi.getPid());
                psize.setCcolorcode(pi.getPcolor().get(i).getPsize().get(j).getCcolorcode());
                psize.setSsize(pi.getPcolor().get(i).getPsize().get(j).getSsize());
                psize.setSamount(pi.getPcolor().get(i).getPsize().get(j).getSamount());
                productDAO.insertProductSize(psize);
            }
        }
    }
}