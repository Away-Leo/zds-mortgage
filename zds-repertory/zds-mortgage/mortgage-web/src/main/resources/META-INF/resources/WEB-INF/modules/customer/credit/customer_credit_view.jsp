<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../../common/common_js.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户征信信息查看</title>
</head>
<body id="body">
<div class="page-title">征信信息</div>
<div class="page-title">张三征信信息概览</div>
<div class="page-box">
    <div class="page-title">贷款</div>
        <div class="p5">
        	<table class="table-detail">
                <tr>
                    <td class="td-title pct10">姓名</td>
                    <td class="pct20">${personalVo.clientNm}</td>
                    <td class="td-title pct10">性别</td>
                    <td class="pct20">${personalVo.genderNm}</td>
                    <td class="td-title pct10">检索码</td>
                    <td class="pct30">${personalVo.searchCd}</td>
                </tr>
            </table>
        </div>
</div>
<div class="page-box">
    <div class="page-title">信用卡</div>
        <div class="p5">
        	<table class="table-detail">
                <tr>
                    <td class="td-title pct10">姓名</td>
                    <td class="pct20">${personalVo.clientNm}</td>
                    <td class="td-title pct10">性别</td>
                    <td class="pct20">${personalVo.genderNm}</td>
                    <td class="td-title pct10">检索码</td>
                    <td class="pct30">${personalVo.searchCd}</td>
                </tr>
            </table>
        </div>
</div>

<div class="page-title">逾期征信信息概览（最近12个月）</div>
<div class="page-box">
    <div class="page-title">贷款逾期</div>
        <div class="p5">
        	<table class="table-detail">
                <tr>
                    <td class="td-title pct10">姓名</td>
                    <td class="pct20">${personalVo.clientNm}</td>
                    <td class="td-title pct10">性别</td>
                    <td class="pct20">${personalVo.genderNm}</td>
                    <td class="td-title pct10">检索码</td>
                    <td class="pct30">${personalVo.searchCd}</td>
                </tr>
            </table>
        </div>
</div>
<div class="page-box">
    <div class="page-title">贷记卡逾期</div>
        <div class="p5">
        	<table class="table-detail">
                <tr>
                    <td class="td-title pct10">姓名</td>
                    <td class="pct20">${personalVo.clientNm}</td>
                    <td class="td-title pct10">性别</td>
                    <td class="pct20">${personalVo.genderNm}</td>
                    <td class="td-title pct10">检索码</td>
                    <td class="pct30">${personalVo.searchCd}</td>
                </tr>
            </table>
        </div>
</div>
<div class="page-box">
    <div class="page-title">准贷记卡逾期</div>
        <div class="p5">
        	<table class="table-detail">
                <tr>
                    <td class="td-title pct10">姓名</td>
                    <td class="pct20">${personalVo.clientNm}</td>
                    <td class="td-title pct10">性别</td>
                    <td class="pct20">${personalVo.genderNm}</td>
                    <td class="td-title pct10">检索码</td>
                    <td class="pct30">${personalVo.searchCd}</td>
                </tr>
            </table>
        </div>
</div>

<!--  -->
<div class="page-title">其他人征信概要</div>
<div class="page-title">王五征信信息概览</div>
<div class="page-box">
    <div class="page-title">贷款</div>
        <div class="p5">
        	<table class="table-detail">
                <tr>
                    <td class="td-title pct10">姓名</td>
                    <td class="pct20">${personalVo.clientNm}</td>
                    <td class="td-title pct10">性别</td>
                    <td class="pct20">${personalVo.genderNm}</td>
                    <td class="td-title pct10">检索码</td>
                    <td class="pct30">${personalVo.searchCd}</td>
                </tr>
            </table>
        </div>
</div>
<div class="page-box">
    <div class="page-title">信用卡</div>
        <div class="p5">
        	<table class="table-detail">
                <tr>
                    <td class="td-title pct10">姓名</td>
                    <td class="pct20">${personalVo.clientNm}</td>
                    <td class="td-title pct10">性别</td>
                    <td class="pct20">${personalVo.genderNm}</td>
                    <td class="td-title pct10">检索码</td>
                    <td class="pct30">${personalVo.searchCd}</td>
                </tr>
            </table>
        </div>
</div>
<!--  -->
<div class="page-title">逾期征信信息概览（最近12个月）</div>
<div class="page-box">
    <div class="page-title">贷款逾期</div>
        <div class="p5">
        	<table class="table-detail">
                <tr>
                    <td class="td-title pct10">姓名</td>
                    <td class="pct20">${personalVo.clientNm}</td>
                    <td class="td-title pct10">性别</td>
                    <td class="pct20">${personalVo.genderNm}</td>
                    <td class="td-title pct10">检索码</td>
                    <td class="pct30">${personalVo.searchCd}</td>
                </tr>
            </table>
        </div>
</div>
<div class="page-box">
    <div class="page-title">贷记卡逾期</div>
        <div class="p5">
        	<table class="table-detail">
                <tr>
                    <td class="td-title pct10">姓名</td>
                    <td class="pct20">${personalVo.clientNm}</td>
                    <td class="td-title pct10">性别</td>
                    <td class="pct20">${personalVo.genderNm}</td>
                    <td class="td-title pct10">检索码</td>
                    <td class="pct30">${personalVo.searchCd}</td>
                </tr>
            </table>
        </div>
</div>
<div class="page-box">
    <div class="page-title">准贷记卡逾期</div>
        <div class="p5">
        	<table class="table-detail">
                <tr>
                    <td class="td-title pct10">姓名</td>
                    <td class="pct20">${personalVo.clientNm}</td>
                    <td class="td-title pct10">性别</td>
                    <td class="pct20">${personalVo.genderNm}</td>
                    <td class="td-title pct10">检索码</td>
                    <td class="pct30">${personalVo.searchCd}</td>
                </tr>
            </table>
        </div>
</div>
<script type="text/javascript">
	seajs.use(['jquery','zd/tools','zd/jquery.zds.address'],function($,ZTOOLS) {

	});
</script>
</body>
</html>