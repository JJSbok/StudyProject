package org.zerock.b4.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.b4.dto.ProductRegisterDTO;

@Transactional
public interface ProductService {

    Integer register(ProductRegisterDTO registerDTO);


}
