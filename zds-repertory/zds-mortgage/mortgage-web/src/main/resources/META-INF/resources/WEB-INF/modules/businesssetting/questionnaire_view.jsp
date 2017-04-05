<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zdsoft.cn/tags" prefix="z"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%
	String resServerUpload = request.getContextPath();
%>    
<head>
<style type="text/css">
.row{
width: 100%;
overflow:hidden;
}
.col-xs-2 {
	width: 10%;
	position: relative;
	min-height: 1px;
	padding-left: 15px;
	padding-right: 15px;
	float: left;
}
.col-xs-6 {
	width: 49%;
	position: relative;
	min-height: 1px;
	padding-left: 15px;
	padding-right: 15px;
	float: left;
	text-align:right;
}
.img-thumbnail {
	padding: 4px;
	line-height: 1.42857143;
	background-color: #fff;
	border: 1px solid #ddd;
	border-radius: 4px;
	-webkit-transition: all .2s ease-in-out;
	-o-transition: all .2s ease-in-out;
	transition: all .2s ease-in-out;
	display: inline-block;
	max-width: 100%;
	height: auto;
}
</style>
    </head>
<!-- 智能问卷查看页面 -->
<div class="container" style="height: 200px;width: 100%;">

		<div class="row">
		        <div class="col-xs-2">
		        	<c:if test="${empty main_picture_id}">
			            <img src="<%=resServerUpload %>/assets/images/user01@2x.png" alt="主借人" class="img-thumbnail">
		        	</c:if>
		        	<c:if test="${not empty main_picture_id}">
			            <img src="../upload/download?attachmentId=${main_picture_id}" alt="主借人" class="img-thumbnail">
		        	</c:if>
		        </div>
		        <div class="col-xs-2">
		       		 <c:if test="${empty spouse_picture_id}">
			             <img src="<%=resServerUpload %>/assets/images/user02@2x.png" alt="主借人配偶" class="img-thumbnail">
		        	</c:if>
		        	<c:if test="${not empty spouse_picture_id}">
			            <img src="../upload/download?attachmentId=${spouse_picture_id}" alt="主借人配偶" class="img-thumbnail">
		        	</c:if>
		        </div>
		 </div>
</div>
		
  <!-- 列表区域 -->
<div class="page-box" >
	  <div class="p10" >
	  		   <c:if test="${sceneTypeCode=='YWDM0010203'}">
	      <div class="col-xs-6" style="margin-bottom: 10px">
		    	<button id="all_question" class="btn-blue mr10"  style="display: none;">全量问题</button>
			    <button id="same_question" class="btn-blue mr10" >相同问题</button>
          </div>
      </c:if>
		     <div id="zg-table" style="width: 50%;"> </div>
		 </div>
</div>
		
	<script type="text/javascript">
	seajs.use(['jquery','zd/jquery.zds.page.callback','zd/tools','zd/jquery.zds.form','zd/jquery.zds.message','zd/jquery.zds.table'], 
			function($, CALLBACK,ZTools) {
		
		$("#all_question").click(function(){
			$("#same_question").show();
			$("#all_question").hide();
			$('#zg-table').ZTable("reload",{"caseApplyId":"${caseApplyId}","sceneTypeCode":"${sceneTypeCode}","isSame":false});
		});
		
		$("#same_question").click(function(){
			$("#all_question").show();
			$("#same_question").hide();
			$('#zg-table').ZTable("reload",{"caseApplyId":"${caseApplyId}","sceneTypeCode":"${sceneTypeCode}","isSame":true});
		});
		
		//列表
		$('#zg-table').ZTable({
		       url : "<z:ukey key='com.zdsoft.finance.question.getQuestionnaireList' context='admin'/>",
		       singleSelect : true,
		       isRowNum : false,
		       pagination : false,
		       currentPage : 1,
		       queryParams:{"caseApplyId":"${caseApplyId}","sceneTypeCode":"${sceneTypeCode}","isSame":false},
		       idField: "id",
		       tableCls : 'table-index',//table的样式
		       columns:[[
		    	  	{field : 'questionContent',title : '问题',align : 'left',
                        formatter: function (row, value) {
                        	if(row.same&&!row.result){
                        		value="<font color='red'>"+value+"</font>";
                        	}
                            return value; 
                        }},
					{field : 'capitalResult',title : '资调答案',align : 'center',
                        formatter: function (row, value) {
                            return value==null?"x":value; 
                        }},
					{field : 'signResult',title : '面签答案',align : 'center',hidden:${sceneTypeCode=='YWDM0010203'?false:true},
                        formatter: function (row, value) {
                        	return value==null?"x":value; 
                        }}
			] ]
		});
		
		window.saveQuestionnaire=function(){
			return {"validFlg":false,"caseApplyId":"${caseApplyId}","sceneTypeCode":"${sceneTypeCode}","questionList":"","isView":true};
		}
	});
	
	</script>
</div>
