<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>
<!-- 产权状态 -->
<div id="statusDiv">
<form action="" id="searchStatusForm" class="zui-form">
	<input type="hidden" value="${searchVo.id }" name="searchId"/>
	<div class="page-box">
		<div class="page-title">产权状态 </div>
		<div class="p5">
<%-- 		<input type="hidden" value="${searchVo.housePropertyId }" name="housePropertyId"> --%>
			<table class="table-detail" id="propertyStatusTable">
				<tr>
					<td class="td-title pct10"><b class="c-red mr5">*</b>查册状态</td>
					<td class="pct20">
						<dd class="detail">
							<input class="" id="searchStatusBtn" name="searchStatus"
								type="hidden" validate-type="requird">
						</dd>
					</td>
					<td class="td-title pct10 searchUnit" style="display:none">查册单位</td>
					<td class="pct20">
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> <input type="text" name="searchUnit" value="${searchVo.searchUnit }"
									class="zui-input " validate-type="Require" style="display:none"/>
								</label>
							</dd>
						</dl>
					</td>
					<td class="td-title pct10"></td>
					<td class="pct30"></td>
				</tr>
				
				<tr style="display:none">
					<td class="td-title">是否查封</td>
					<td>
						<dd class="detail">
							<input class="" name="isSearched" value="${searchVo.isSearched }"
								type="hidden" data-multiple="false" data-defaultvalue="false"
								data-data="[{'id':'true','text':'已查封'},{'id':'false','text':'未查封'}]"
								data-valuefield="id" data-textfield="text" 
								validate-type="Require">
						</dd>
					</td>
					<td class="td-title"></td>
					<td></td>
					<td class="td-title"></td>
					<td></td>
				</tr>
				<tr style="display:none">
					<td class="td-title">是否有查封历史</td>
					<td>
						<dd class="detail">
							<input class="" id="isSearchHistoryBtn" name="isSearchHistory"
								type="hidden" validate-type="Require">
						</dd>
					</td>
					<td class="td-title">查封时间</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label>
									<input class="zui-date  strToDate" type="text" value="${searchVo.searchDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'searchDate',dateFmt:'yyyy-MM-dd'})" validate-type="Require" readonly "/>
									<input type="hidden" id="searchDate" name="searchDate" value="${searchVo.searchDate }"/>
								</label>
							</dd>
						</dl>
					</td>
					<td class="td-title">查封期限</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> 
									<input type="text" class="zui-input " name="searchTerm" validate-type="Require" value="${searchVo.searchTerm }"/>
									<font style="font-size: 14px">年</span>
								</label>
							</dd>
						</dl>
					</td>
				</tr>
				<tr style="display:none">
					<td class="td-title">查封金额</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> <input type="text" name="searchAmount" value="${searchVo.searchAmount }"
									class="zui-input width1 "
									validate-type="Require,Digital[10-2]"><font style="font-size: 14px">元</span>
								</label> 
							</dd>
						</dl>
					</td>
					<td class="td-title">查封者</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> <input type="text" class="zui-input " name="searchName" validate-type="Require" value="${searchVo.searchName }"/>
								</label>
							</dd>
						</dl>
					</td>
					<td class="td-title">解封时间</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label>
									<input class="zui-date  strToDate" type="text" value="${searchVo.unsealDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'unsealDate',dateFmt:'yyyy-MM-dd'})" validate-type="Require" readonly "/>
									<input type="hidden" id="unsealDate" name="unsealDate" value="${searchVo.unsealDate }"/>
								</label>
							</dd>
						</dl>
					</td>
				</tr>
				<tr style="display:none">
					<td class="td-title">查封事由</td>
					<td colspan="5"><label> <textarea class="zui-area " name="searchReason" validate-type="Require"
								placeholder="最多可以输入200个字符">${searchVo.searchReason }</textarea>
					</label></td>
				</tr>
				<tr style="display:none">
					<td class="td-title">是否有抵押历史</td>
					<td>
						<dd class="detail">
							<input class="" id="isMortgageHistoryBtn" 
								type="hidden" name="isMortgageHistory" validate-type="Require">
						</dd>
					</td>
					<td class="td-title">抵押时间</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label>
									<input class="zui-date  strToDate" type="text" value="${searchVo.mortgageDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'mortgageDate',dateFmt:'yyyy-MM-dd'})" validate-type="Require" readonly "/>
									<input type="hidden" id="mortgageDate" name="mortgageDate" value="${searchVo.mortgageDate }"/>
								</label>
							</dd>
						</dl>
					</td>
					<td class="td-title">查封金额</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> <input type="text" name="mortgageAmount" value="${searchVo.mortgageAmount }"
									class="zui-input width1 "
									validate-type="Require,Digital[10-2]"><font style="font-size: 14px">元</span>
								</label>
							</dd>
						</dl>
					</td>
				</tr>
				<tr style="display:none">
					<td class="td-title"></td>
					<td></td>
					<td class="td-title">抵押状态</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<input class="zui-combobox " type="hidden" name="mortgageeStatus" value="${searchVo.mortgageeStatus }"
									data-data="[{'id':'0','text':'选择1'},{'id':'1','text':'选择2'},{'id':'2','text':'选择3'}]"
									data-callback="change" data-valuefield="id"
									data-textfield="text" validate-type="Require">
							</dd>
						</dl>
					</td>
					<td class="td-title">解押时间</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label>
									<input class="zui-date  strToDate" value="${searchVo.dischargeDate }" type="text" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'dischargeDate',dateFmt:'yyyy-MM-dd'})" validate-type="Require" readonly "/>
									<input type="hidden" id="dischargeDate" name="dischargeDate" value="${searchVo.dischargeDate }"/>
								</label>
							</dd>
						</dl>
					</td>
				</tr>
				<tr style="display:none">
					<td class="td-title"></td>
					<td></td>
					<td class="td-title">抵押权人</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label> <input type="text" class="zui-input " validate-type="Require" name="mortgagee" value="${searchVo.mortgagee }"/>
								</label>
							</dd>
						</dl>
					</td>
					<td class="td-title">预计日期</td>
					<td>
						<dl class="form-item form-auto">
							<dd class="detail">
								<label>
									<input class="zui-date  strToDate" value="${searchVo.expectDate }" type="text" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'expectDate',dateFmt:'yyyy-MM-dd'})" validate-type="Require" readonly "/>
									<input type="hidden" id="expectDate" name="expectDate" value="${searchVo.expectDate }"/>
								</label>
							</dd>
						</dl>
					</td>
				</tr>
				</div>
			</table>
		</div>
	</div>
</form>
</div>
<script type="text/javascript">
	seajs.use(['jquery'], 
	function($) {
		
		var trs = $("#propertyStatusTable").find("tr");
       	var td0s = $(trs[0]).find("td");
		var td2s = $(trs[2]).find("td");
		var td5s = $(trs[5]).find("td");
		//查册状态
		var searchStatus = '1';
		//查封历史
		var isSearchHistory = 'true';
		//抵押历史
		var isMortgageHistory = 'true';
		
		searchStatus = '${searchVo.searchStatus }' ==''?'1':'${searchVo.searchStatus }';
		isSearchHistory = '${searchVo.isSearchHistory }' ==''?'false':'${searchVo.isSearchHistory }';
		isMortgageHistory = '${searchVo.isMortgageHistory }'==''?'false':'${searchVo.isMortgageHistory }';
		//查册状态
		$("#searchStatusBtn").ZCheckbox({
	        valueField: "id",
	        textField: "text",
	        multiple:false,
	        value:searchStatus,
	        data: [{'id':'0','text':'已查册'},{'id':'1','text':'未查册'}],
	        onSelect:function(v,t){
	        	initPropertyStatus(v);
	        }
	    });
		
		function initPropertyStatus(value){
			if(value == 1){
	        	for(var i=0;i<trs.length;i++){
	        		if(i>0){
		        		$(trs[i]).hide();
	        		}
	        		$(trs[i]).find("input,textarea").removeClass("zui-validatebox").removeClass("error");
	        		$(trs[i]).find(".zd-validation").remove();
	        	}
	        	for(var j=2;j<td0s.length;j++){
	        		$(td0s[j]).find("input").hide();
	        	}
	        	$(".searchUnit").hide();
        	}else{
        		for(var i=0;i<trs.length;i++){
        			if(i>0){
		        		$(trs[i]).show();
        			}
	        		$(trs[i]).show().find("input").addClass("zui-validatebox")
	        	}
        		for(var j=2;j<td0s.length;j++){
	        		$(td0s[j]).find("input").show();
	        	}
        		$(".searchUnit").show();
        		var sh = $("#isSearchHistoryBtn").ZCheckbox("getValue");
	        	changeSH(sh+'',trs,td2s);
	        	
	        	var mh = $("#isMortgageHistoryBtn").ZCheckbox("getValue");
	        	changeMH(mh+'',trs,td5s);
        	}
		}
		
		//查封历史
		$("#isSearchHistoryBtn").ZCheckbox({
	        valueField: "id",
	        textField: "text",
	        multiple:false,
	        singleCheck:true,
	        value:isSearchHistory,
	        data: [{'id':'false','text':'有查封历史'},{'id':'true','text':'无查封历史'}],
	        onSelect:function(v,t){
	        	var trs = $("#propertyStatusTable").find("tr");
	        	if(!v){
	        		changeSH('false',trs,td2s)
	        		for(var i=2;i<=4;i++){
		        		$(trs[i]).find("input,textarea").addClass("zui-validatebox");
		        	}
	        	}else{
	        		changeSH('true',trs,td2s)
	        		for(var i=2;i<=4;i++){
		        		$(trs[i]).find("input,textarea").removeClass("zui-validatebox").removeClass("error");
		        		$(trs[i]).find(".zd-validation").remove();
		        	}
	        	}
	        }
	    });
		
		function changeSH(isShow,trs,tds){
			if(isShow == 'true'){
				for(var i=2;i<tds.length;i++){
					$(trs[i]).find("input,textarea").removeClass("zui-validatebox").removeClass("error");
	        		$(trs[i]).find(".zd-validation").remove();
        			$(tds[i]).hide();
        		}
				$(trs[3]).find("input,textarea").removeClass("zui-validatebox").removeClass("error");
        		$(trs[3]).find(".zd-validation").remove();
				$(trs[4]).find("input,textarea").removeClass("zui-validatebox").removeClass("error");
        		$(trs[4]).find(".zd-validation").remove();
	        	$(trs[3]).hide();
	        	$(trs[4]).hide();
			}else{
				for(var i=0;i<tds.length;i++){
        			$(tds[i]).show();
        		}
        		$(trs[3]).show();
	        	$(trs[4]).show();
			}
		}
		
		//抵押历史
		$("#isMortgageHistoryBtn").ZCheckbox({
	        valueField: "id",
	        textField: "text",
	        multiple:false,
	        value:isMortgageHistory,
	        data: [{'id':'false','text':'有抵押历史'},{'id':'true','text':'无抵押历史'}],
	        onSelect:function(v,t){
	        	var trs = $("#propertyStatusTable").find("tr");
	        	if(v == 1){
	        		//changeMH('true',trs,td5s)
	        		for(var i=5;i<=7;i++){
		        		$(trs[i]).find("input,a").removeClass("zui-validatebox").removeClass("error");
		        		$(trs[i]).find(".zd-validation").remove();
		        	}
	        	}else{
	        		//changeMH('false',trs,td5s)
	        		for(var i=5;i<=7;i++){
		        		$(trs[i]).find("input").addClass("zui-validatebox");
		        	}
	        	}
	        }
	    });
		
		function changeMH(isShow,trs,tds){
			if(isShow == 'false'){
				for(var i=2;i<tds.length;i++){
        			$(tds[i]).show();
        		}
        		$(trs[6]).show();
	        	$(trs[7]).show();
			}else{
				for(var i=2;i<tds.length;i++){
					$(trs[i]).find("input,textarea").removeClass("zui-validatebox").removeClass("error");
	        		$(trs[i]).find(".zd-validation").remove();
        			$(tds[i]).hide();
        		}
				$(trs[6]).find("input,textarea").removeClass("zui-validatebox").removeClass("error");
        		$(trs[6]).find(".zd-validation").remove();
				$(trs[7]).find("input,textarea").removeClass("zui-validatebox").removeClass("error");
        		$(trs[7]).find(".zd-validation").remove();
        		$(trs[6]).hide();
	        	$(trs[7]).hide();
			}
		}

		initPropertyStatus(searchStatus);
		
	})
</script>