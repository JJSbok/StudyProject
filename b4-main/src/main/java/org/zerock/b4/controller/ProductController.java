package org.zerock.b4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.zerock.b4.dto.ProductRegisterDTO;
import org.zerock.b4.service.ProductService;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/register")
  public void register(){
    log.info("get product register");

  }

  @PostMapping("/register")
  public String registerPost(ProductRegisterDTO registerDTO){

    log.info("---------------------");
    log.info(registerDTO);

    Integer pno = productService.register(registerDTO);

    log.info("NEW PNO : " + pno);

    return "redirect:/product/list";
  }

}
