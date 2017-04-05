
package com.zdsoft.finance.shoot.repository;

import com.zdsoft.finance.common.base.CustomRepository;
import com.zdsoft.finance.shoot.entity.PhotoTree;

import java.util.List;

/**
 * 版权所有：重庆正大华日软件有限公司
 *
 * @author panshm
 * @version V1.0
 * @Title: PhotoTreeRepository.java
 * @ClassName: PhotoTreeRepository
 * @Description: 资调摄像照片管理Repository
 * @date 2017年3月6日 下午2:47:52
 */
public interface PhotoTreeRepository extends CustomRepository<PhotoTree, String> {

    /**
     * @param caseApplyCode 案件号
     * @param linkCode      关联环节Code
     * @return List<PhotoTree>
     * @Title: findByCaseApplyCodeAndLinkCode
     * @Description: 通过案件号和环境code取得照片信息
     * @author panshm
     */
    public List<PhotoTree> findByCaseApplyCodeAndLinkCode(String caseApplyCode, String linkCode);

    /**
     *
     * @Title: findByCaseApplyCodeAndLinkCodeAndAllNodeStrStartingWith
     * @Description:  通过案件号、环节code和 allnodestr前两个分类得到照片信息
     * @author denglw
     * @param caseApplyCode 案件号
     * @param linkCode 环节COde
     * @param preNodeStr  allnodestr前两个分类(抵押物-抵押物1)
     * @return
     */
    public List<PhotoTree> findByCaseApplyCodeAndLinkCodeAndAllNodeStrStartingWith(String caseApplyCode, String linkCode,String preNodeStr);

    /**
     * @param @param     caseApplyCode 案件号
     * @param linkCode   关联环节Code
     * @param allNodeStr 完整分类编码
     * @return List<PhotoTree>
     * @Title: findByCaseApplyCodeAndLinkCodeAndAllNodeStrLike
     * @Description: 通过案件号、环境code和完整分类编码取得照片信息
     * @author panshm
     */
    public List<PhotoTree> findByCaseApplyCodeAndLinkCodeAndAllNodeStrLike(String caseApplyCode, String linkCode,
                                                                           String allNodeStr);

    /**
     * @param fileName
     * @return
     * @Title: deleteByFileName
     * @Description: 根据文件名删除记录
     * @author denglw
     */
    public int deleteByFileName(String fileName);

    /**
     *
     * @Title: countByCaseApplyCodeAndAllNodeStrAndPhotoPath
     * @Description: 查询该分类是否已存在相同文件
     * @author denglw
     * @param caseApplyCode
     * @param allNodeStr
     * @param photoPath
     * @return
     */
    public int countByCaseApplyCodeAndAllNodeStrAndPhotoPath(String caseApplyCode, String allNodeStr, String photoPath);

    /**
     *
     * @Title: deleteByCaseApplyCodeAndAllNodeStrStartingWith
     * @Description: 删除分类的记录（抵押物-抵押物1）
     * @author denglw
     * @param caseApplyCode 案件号
     * @param allNodeStr 抵押物-抵押物1 前两个分类
     * @return
     */
    public int deleteByCaseApplyCodeAndAllNodeStrStartingWith(String caseApplyCode,String allNodeStr);
}
