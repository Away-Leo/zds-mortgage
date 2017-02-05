package com.zdsoft.finance.product.service.impl;

import com.zdsoft.finance.base.service.impl.BaseServiceImpl;
import com.zdsoft.finance.product.entity.MaterialList;
import com.zdsoft.finance.product.repository.MaterialListRepository;
import com.zdsoft.finance.product.service.QRCodeService;
import org.springframework.stereotype.Service;

/**
 * 二维码service实现
 * @author LiaoGuoWei
 * @create 2016-12-29 16:19
 **/
@Service("qrCodeService")
public class QRCodeServiceImpl extends BaseServiceImpl<MaterialList,MaterialListRepository> implements QRCodeService {
}
