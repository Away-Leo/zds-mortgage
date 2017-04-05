<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 产权状态 -->  
<div id="statusDiv">
	<form id="searchStatusForm" class="zui-form">
		<input type="hidden" id="searchId" name="searchVo.id" value="${searchVo.id }"/>
		<div class="page-box">
			<div class="page-title">产权状态 </div>
			<div class="p5">
				<table class="table-detail" id="propertyStatusTable">
					<tr>
						<td class="td-title pct10"><b class="c-red mr5">*</b>查册状态</td>
						<td class="pct20">
							<dd class="detail">
								<input class="" id="searchStatusBtn" name="searchVo.searchStatus"
									type="hidden" validate-type="requird">
							</dd>
						</td>
						<td class="td-title pct10 searchUnit" style="display:none">查册单位</td>
						<td class="pct20">
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" name="searchVo.searchUnit" value="${searchVo.searchUnit }" class="zui-input zui-validatebox"  validate-type="Length[0-64]"/>
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
								<input class="zui-checkbox" name="searchVo.isSearched" value="${searchVo.isSearched }"
									type="hidden" data-multiple="false" data-defaultvalue="false"
									data-data="[{'id':'true','text':'已查封'},{'id':'false','text':'未查封'}]"
									data-valuefield="id" data-textfield="text" >
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
								<input id="isSearchHistoryBtn" name="searchVo.isSearchHistory" type="hidden">
							</dd>
						</td>
						<td class="td-title">查封时间</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label>
										<input class="zui-date  strToDate" type="text" value="${searchVo.searchDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'searchDate',dateFmt:'yyyy-MM-dd',maxDate: '%y-%M-%d'})" readonly "/>
										<input type="hidden" id="searchDate" name="searchVo.searchDate" value="${searchVo.searchDate }"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title">查封期限</td>
						<td>
							<dl class="form-item form-auto">  
								<dd class="detail">  
									<label> 
										<input type="text" class="zui-input zui-validatebox" validate-type="Integer,Length[0-3]" name="searchVo.searchTerm"  value="${searchVo.searchTerm }"/>
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
									<label> <input type="text" class="zui-input zui-validatebox" name="searchVo.searchAmount" value="${searchVo.searchAmount }"
										class="zui-input width1 "
										validate-type="Digital[18-2]"><font style="font-size: 14px">元</span>
									</label> 
								</dd>
							</dl>
						</td>
						<td class="td-title">查封者</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" class="zui-input " name="searchVo.searchName" value="${searchVo.searchName }"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title">解封时间</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label>
										<input class="zui-date  strToDate" type="text" value="${searchVo.unsealDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'unsealDate',dateFmt:'yyyy-MM-dd'})" readonly "/>
										<input type="hidden" id="unsealDate" name="searchVo.unsealDate" value="${searchVo.unsealDate }"/>
									</label>
								</dd>
							</dl>
						</td>
					</tr>
					<tr style="display:none">
						<td class="td-title">查封事由</td>
						<td colspan="5">
							<label> <textarea class="zui-area row-width zui-validatebox" name="searchVo.searchReason"  validate-type="Length[0-200]" 
									placeholder="最多可以输入200个字符">${searchVo.searchReason }</textarea>
							</label>
							<div class="zd-area">
			                    <span class="zd-curval">0</span>/<span class="zd-maxval">200</span>
			                 </div>
						</td>
					</tr>
					<tr style="display:none">
						<td class="td-title">是否有抵押历史</td>
						<td>
							<dd class="detail">
								<input class="" id="isMortgageHistoryBtn" type="hidden" name="searchVo.isMortgageHistory">
							</dd>
						</td>
						<td class="td-title">抵押时间</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label>
										<input class="zui-date  strToDate" type="text" value="${searchVo.mortgageDate }" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'mortgageDate',dateFmt:'yyyy-MM-dd',maxDate: '%y-%M-%d'})" readonly "/>
										<input type="hidden" id="mortgageDate" name="searchVo.mortgageDate" value="${searchVo.mortgageDate }"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title">查封金额</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label> <input type="text" class="zui-input zui-validatebox" name="searchVo.mortgageAmount" value="${searchVo.mortgageAmount }"
										class="zui-input width1 " validate-type="Digital[10-2]"><font style="font-size: 14px">元</span>
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
								<input class="zui-combobox " type="hidden" name="searchVo.mortgageeStatus" value="${searchVo.mortgageeStatus }"
									data-data="[{'id':'0','text':'已抵押'},{'id':'1','text':'未抵押'}]"
									data-callback="change" data-valuefield="id" data-textfield="text">
							</dd>
							</dl>
						</td>
						<td class="td-title">解押时间</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label>
										<input class="zui-date  strToDate" value="${searchVo.dischargeDate }" type="text" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'dischargeDate',dateFmt:'yyyy-MM-dd'})" readonly "/>
										<input type="hidden" id="dischargeDate" name="searchVo.dischargeDate" value="${searchVo.dischargeDate }"/>
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
									<label> <input type="text" class="zui-input" name="searchVo.mortgagee" value="${searchVo.mortgagee }"/>
									</label>
								</dd>
							</dl>
						</td>
						<td class="td-title">预计日期</td>
						<td>
							<dl class="form-item form-auto">
								<dd class="detail">
									<label>
										<input class="zui-date  strToDate" value="${searchVo.expectDate }" type="text" onclick="WdatePicker({realDateFmt:'yyyyMMdd',vel:'expectDate',dateFmt:'yyyy-MM-dd'})" readonly "/>
										<input type="hidden" id="expectDate" name="searchVo.expectDate" value="${searchVo.expectDate }"/>
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
	seajs.use(['jquery','zd/jquery.zds.form'], function($) {
		$.ZUI.init("#statusDiv");
		
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
		
		searchStatus = '${searchVo.searchStatus }' ==''?'0':'${searchVo.searchStatus }';
		isSearchHistory = '${searchVo.isSearchHistory }' ==''?'true':'${searchVo.isSearchHistory }';
		isMortgageHistory = '${searchVo.isMortgageHistory }'==''?'true':'${searchVo.isMortgageHistory }';
		//查册状态
		$("#searchStatusBtn").ZCheckbox({
	        valueField: "id",
	        textField: "text",
	        multiple:false,
	        value:searchStatus,
	        data: [{'id':'1','text':'已查册'},{'id':'0','text':'未查册'}],
	        onSelect:function(v,t){
	        	initPropertyStatus(v);
	        }
	    });
		
		function initPropertyStatus(value){
			if(value == 0){
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
	        data: [{'id':'true','text':'有查封历史'},{'id':'false','text':'无查封历史'}],
	        onSelect:function(v,t){
	        	var trs = $("#propertyStatusTable").find("tr");
	        	if(v){
	        		changeSH('true',trs,td2s)
	        		for(var i=2;i<=4;i++){
		        		$(trs[i]).find("input,textarea").addClass("zui-validatebox");
		        	}
	        	}else{
	        		changeSH('false',trs,td2s)
	        		for(var i=2;i<=4;i++){
		        		$(trs[i]).find("input,textarea").removeClass("zui-validatebox").removeClass("error");
		        		$(trs[i]).find("input,textarea").val("");
		        		$(trs[i]).find(".zd-validation").remove();
		        	}
	        	}
	        }
	    });
		
		function changeSH(isShow,trs,tds){
			if(isShow == 'false'){
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
	        data: [{'id':'true','text':'有抵押历史'},{'id':'false','text':'无抵押历史'}],
	        onSelect:function(v,t){
	        	var trs = $("#propertyStatusTable").find("tr");
	        	if(v == 0){
	        		changeMH('false',trs,td5s)
	        		for(var i=5;i<=7;i++){
		        		$(trs[i]).find("input,a").removeClass("zui-validatebox").removeClass("error");
		        		$(trs[i]).find(".zd-validation").remove();
		        	}
	        	}else{
	        		changeMH('true',trs,td5s)
	        		for(var i=5;i<=7;i++){
		        		$(trs[i]).find("input").addClass("zui-validatebox");
		        	}
	        	}
	        }
	    });
		
		function changeMH(isShow,trs,tds){
			if(isShow == 'true'){
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
		
	});
</script>