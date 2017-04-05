package com.zdsoft.finance.casemanage.booking.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdsoft.essential.client.aop.annotation.DataAuthResource;
import com.zdsoft.essential.client.service.CED;
import com.zdsoft.essential.dto.permission.DataOperPermDto;
import com.zdsoft.finance.casemanage.booking.entity.Booking;
import com.zdsoft.finance.casemanage.booking.service.BookingService;
import com.zdsoft.finance.casemanage.booking.vo.BookingVo;
import com.zdsoft.finance.common.base.QueryObj;
import com.zdsoft.finance.common.exception.BusinessException;
import com.zdsoft.finance.marketing.entity.CaseApply;
import com.zdsoft.finance.marketing.service.CaseApplyService;
import com.zdsoft.finance.product.entity.Product;
import com.zdsoft.finance.product.service.ProductService;
import com.zdsoft.framework.core.common.dto.ResponseMsg;
import com.zdsoft.framework.core.common.page.Page;
import com.zdsoft.framework.core.common.page.PageRequest;
import com.zdsoft.framework.core.common.util.DateHelper;
import com.zdsoft.framework.core.common.util.ObjectHelper;
import com.zdsoft.framework.core.common.util.StoreHelper;
import com.zdsoft.framework.core.commweb.annotation.UriKey;
import com.zdsoft.framework.core.commweb.component.BaseController;
import com.zdsoft.framework.cra.annotation.Menu;

/**
 * 
 * 版权所有：重庆正大华日软件有限公司
 * @Title: BookingController.java 
 * @ClassName: BookingController 
 * @Description: 案件预约信息控制器
 * @author dengyy 
 * @date 2017年2月13日 下午7:56:46 
 * @version V1.0
 */
@Controller
@RequestMapping("booking")
public class BookingController extends BaseController{
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private CaseApplyService caseApplySerivce;
	
	@Autowired
	private ProductService  productService ;
	
	@Autowired
	private CED CED ;
	
	/**
	 * 
	 * @Title: bookingList 
	 * @Description: 案件预约列表入口  菜单
	 * @author dengyy 
	 * @return
	 */
	@RequestMapping("/initBookingList")
	@UriKey(key = "com.cnfh.rms.casemanage.booking.initBookingList")
	@Menu(resource = "com.cnfh.rms.casemanage.booking.initBookingList", label = "预约", group = "project", order = 2)
	@DataAuthResource(resource="com.cnfh.rms.casemanage.booking.initBookingList.dataAuth",label="预约",group="com.cnfh.rms.casemanage.booking.initBookingList")
	public ModelAndView initBookingList() {
	    logger.info("进入案件预约列表页面！");
		return new ModelAndView("/casemanage/booking/case_booking_list");
	}
	
	/**
     * 
     * @Title: bookingList 
     * @Description: 案件预约分页查询列表
     * @author dengyy 
     * @param request 请求信息
     * @param pageable 分页信息
     * @return
     */
    @RequestMapping("/bookingList")
    @UriKey(key = "com.cnfh.rms.casemanage.booking.bookingList")
    @ResponseBody
    public ResponseMsg bookingList(HttpServletRequest request, PageRequest pageable,Boolean flag){
        logger.info("进入获取案件预约信息列表信息");
        ResponseMsg msg = new ResponseMsg();
        // 获取查询参数
        @SuppressWarnings("unchecked")
        List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
        try {
            if(ObjectHelper.isEmpty(flag)){
                QueryObj qu = new QueryObj();
                qu.setElement("bookingType");
                qu.setObj("c");
                qu.setOperator("=");
                qu.setValue(Booking.BOOKINGTYPE_ONE);
                queryObjs.add(qu);
            }
            //数据权限
            DataOperPermDto dtOperPermDto=CED.findDataPerms(StoreHelper.getApplication(), "com.cnfh.rms.casemanage.booking.initBookingList.dataAuth");
            // 获取案件预约列表信息
            Page<Map<String, Object>> caseLimitApply = caseApplySerivce.findPageAppointment(pageable, queryObjs,dtOperPermDto);
            msg.setMsg("列表查询成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(caseLimitApply.getTotalRows());
            msg.setRows(caseLimitApply.getRecords());
        } catch (Exception e) {
            logger.error("获取案件预约列表信息失败！",e);
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg("获取案件预约列表信息失败！");
            e.printStackTrace();
        }
        return msg;
    }
	
	/**
     * 
     * @Title: initLedgerBooking 
     * @Description:案件预约台账(内务)  菜单
     * @author dengyy 
     * @return
     */
    @RequestMapping("/initLedgerBooking")
    @UriKey(key = "com.cnfh.rms.casemanage.booking.initLedgerBooking")
    @Menu(resource = "com.cnfh.rms.casemanage.booking.initLedgerBooking", label = "预约台账(内务)", group = "project", order = 2)
    @DataAuthResource(resource="com.cnfh.rms.casemanage.booking.initLedgerBooking.dataAuth",label="预约台账(内务)",group="com.cnfh.rms.casemanage.booking.initLedgerBooking")
    public ModelAndView initLedgerBooking() {
        logger.info("进入案件预约台账(内务)列表页面！");
        return new ModelAndView("/casemanage/booking/case_ledger_booking_list");
    }
    
    /**
     * 
     * @Title: ledgerBookingList 
     * @Description: 获取案件预约台账列表数据
     * @author dengyy 
     * @param request 请求信息
     * @param pageable 分页信息
     * @return
     */
    @RequestMapping("/ledgerBookingList")
    @UriKey(key = "com.cnfh.rms.casemanage.booking.ledgerBookingList")
    @ResponseBody
    public ResponseMsg ledgerBookingList(HttpServletRequest request, PageRequest pageable,Boolean flag){
        logger.info("进入获取案件预约台账列表数据");
        ResponseMsg msg = new ResponseMsg();
        // 获取查询参数
        @SuppressWarnings("unchecked")
        List<QueryObj> queryObjs = (List<QueryObj>) request.getAttribute("listObj");
        try {
            //数据权限
            DataOperPermDto dtOperPermDto=CED.findDataPerms(StoreHelper.getApplication(), "com.cnfh.rms.casemanage.booking.initBookingList.dataAuth");
            if (ObjectHelper.isEmpty(flag)) {
                QueryObj qu = new QueryObj();
                qu.setElement("bookingType");
                qu.setObj("booking");
                qu.setOperator("=");
                qu.setValue("1");
                queryObjs.add(qu);
            }
            //案件预约台账列表
            Page<Map<String,Object>> ledgerBooking = bookingService.findListLedgerBooking(pageable, queryObjs,dtOperPermDto);
            msg.setMsg("列表查询成功");
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setTotal(ledgerBooking.getTotalRows());
            msg.setRows(ledgerBooking.getRecords());
        } catch (Exception e) {
            logger.error("获取案件预约台账列表信息失败！",e);
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg("获取案件预约台账列表信息失败！");
            e.printStackTrace();
        }
        return msg;
    }
	
	/**
	 * 
	 * @Title: BookingEdit 
	 * @Description: 跳转到预约编辑界面
	 * @author dengyy 
	 * @param id 案件id
	 * @param customerName 主借人
	 * @param phoneNumber 电话号码
	 * @param email 邮箱
	 * @return
	 */
	@RequestMapping("/BookingEdit")
	@UriKey(key="com.cnfh.rms.casemanage.booking.BookingEdit")
	public ModelAndView BookingEdit(String id, String customerName,String phoneNumber, String email){
	    logger.info("进入预约编辑页面初始化");
	    logger.debug("案件信息id："+id);
		Map<String,Object> appointmentModel = new HashMap<String,Object>();
		logger.debug("案件信息主借人："+customerName);
	    logger.debug("案件信息主借人电话："+phoneNumber);
	    logger.debug("案件信息主借人邮箱："+email);
		try {
		    //根据id获取预约信息
		    Booking booking = bookingService.findByCaseApplyId(id);
		    //案件信息
		    CaseApply caseApply = caseApplySerivce.findOne(id);
		    //案件的产品信息
		    Product product = productService.findOne(caseApply.getProductSubtypeId());
			//案件预约信息
			if(ObjectHelper.isNotEmpty(booking)){
			    BookingVo bookingVo = new BookingVo(booking);
				appointmentModel.put("bookingVo", bookingVo);
			}else{
			    BookingVo bookingVo = new BookingVo();
			    //初始化预约发送类容信息  从产品信息中带出
			    bookingVo.setSendtContent( product.getFaceData());
			    appointmentModel.put("bookingVo", bookingVo);
			}
			appointmentModel.put("caseApplyId", id);
			appointmentModel.put("customerName", customerName);
			appointmentModel.put("phoneNumber", phoneNumber);
			appointmentModel.put("email", email);
		} catch (Exception e) {
		    logger.error("获取案件信息失败！",e);
            e.printStackTrace();
        }
		return new ModelAndView("/casemanage/booking/case_booking_edit", appointmentModel);
				
	}
	
	/**
	 * 
	 * @Title: detailsAppointment 
	 * @Description: 跳转到预约详情界面
	 * @author dengyy 
	 * @param id 案件id
	 * @return
	 */
	@RequestMapping("/bookingView")
	@UriKey(key="com.cnfh.rms.casemanage.booking.bookingView")
	public ModelAndView bookingView(String id) {
	    logger.info("进入案件预约信息查看");
	    logger.debug("案件id："+id);
		Map<String,Object> appointmentModel = new HashMap<String,Object>();
		try {
		    //获取预约信息
		    Booking booking = bookingService.findByCaseApplyId(id);
			if(ObjectHelper.isNotEmpty(booking)){
				//转换数据
				BookingVo bookingVo = new BookingVo(booking);
				appointmentModel.put("bookingVo", bookingVo);
			}
			appointmentModel.put("caseApplyId", id);
		} catch (Exception e) {
		    logger.error("获取案件预约信息失败！",e);
			e.printStackTrace();
		}
		return new ModelAndView("/casemanage/booking/case_booking_view", appointmentModel);
		
	}
	
	/**
	 * 
	 * @Title: bookingtPersonCount 
	 * @Description: 获取接下来的预约案件数(包括今天)
	 * @author dengyy 
	 * @return
	 */
	@RequestMapping("/bookingtPersonCount")
	@UriKey(key = "com.cnfh.rms.casemanage.booking.bookingtPersonCount")
	@ResponseBody
	public ResponseMsg bookingtPersonCount(){
	    logger.info("获取统计的案件预约信息！");
		ResponseMsg msg = new ResponseMsg();
		//获取当前时间
		Long nowDate = DateHelper.dateToLong(new Date(),DateHelper.DATE_SHORT_SIMPLE_FORMAT);
		//统计当前时间及以后的预约信息
		List<Map<String,Object>> appointmentPersonCount = bookingService.queryCaseCount(nowDate);
		if(ObjectHelper.isNotEmpty(appointmentPersonCount)){
			msg.setMsg("列表查询成功");
			msg.setResultStatus(ResponseMsg.SUCCESS);
			msg.setTotal((long) appointmentPersonCount.size());
			msg.setRows(appointmentPersonCount); 
		}else{
			msg.setMsg("目前暂无预约");
		}
		return msg;
	}
	
	
	/**
	 * 
	 * @Title: bookingClient 
	 * @Description:  获取案件预约客户(即案件的主借人,所有担保人)
	 * @author dengyy 
	 * @param id 案件id
	 * @return
	 */
	@RequestMapping("/bookingClient")
	@UriKey(key = "com.cnfh.rms.casemanage.booking.bookingClient")
	@ResponseBody
	public ResponseMsg bookingClient(String id){
	    logger.info("获取案件的所有参与人！");
	    logger.debug("案件id："+id);
		ResponseMsg msg = new ResponseMsg();
		List<Map<String,Object>> appointmentClients = caseApplySerivce.appointmentClient(id);
		msg.setResultStatus(ResponseMsg.SUCCESS);
		if(ObjectHelper.isNotEmpty(appointmentClients)){
		    msg.setTotal((long) appointmentClients.size());
		    msg.setRows(appointmentClients); 
		}
		return msg;
	}
	
	/**
	 * 
	 * @Title: saveOrUpdateBooking 
	 * @Description: 保存修改预约 
	 * @author dengyy 
	 * @param id 案件id
	 * @param bookingVo 预约信息
	 * @return
	 */
	@RequestMapping("/saveOrUpdateBooking")
	@UriKey(key = "com.cnfh.rms.casemanage.booking.saveOrUpdateBooking")
	@ResponseBody
	public ResponseMsg saveOrUpdateBooking(BookingVo bookingVo) {
	    logger.info("保存修改案件的预约信息！");
	    logger.debug("案件id："+bookingVo.getCaseApplyId());
		ResponseMsg msg = new ResponseMsg();
        try {
            //判断短信信息
            if(Booking.REMINDWAY_ONE.equals(bookingVo.getRemindWay())){
                if(ObjectHelper.isEmpty(bookingVo.getPhoneNumber())){
                    msg.setResultStatus(ResponseMsg.SYS_ERROR);
                    msg.setMsg("发送短信需要录入客户联系电话！");
                    return msg;
                }
            }
            //获取预约信息
            Booking booking =bookingVo.toPo();
            booking = bookingService.saveOrUpdateBooking(booking);
            msg.setResultStatus(ResponseMsg.SUCCESS);
            msg.setMsg("保存成功");
        } catch (BusinessException e) {
            logger.error("保存预约信息失败！",e);
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg("保存预约信息失败！");
            e.printStackTrace();
        }catch (Exception e) {
            logger.error("保存预约信息失败！",e);
            msg.setResultStatus(ResponseMsg.SYS_ERROR);
            msg.setMsg("保存预约信息失败！");
            e.printStackTrace();
        }
		return msg;
	}
}
