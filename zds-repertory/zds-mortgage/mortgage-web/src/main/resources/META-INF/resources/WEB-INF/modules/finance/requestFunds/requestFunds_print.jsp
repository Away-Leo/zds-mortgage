<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript" src="../../common/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="../../common/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="../../common/jquery.jqprint-0.3.js"></script>
    <meta charset="UTF-8">
    <title>print</title>
    <link rel="stylesheet" type="text/css" href="common/style.css"/>
</head>
<body>

<div id="ddd">
    <div style="margin: 0 auto;width:900px;">
        <div style="width: 250px;margin: 0 auto;">
            <span style="font-size: 25px;">收费明细</span>
        </div>
        <div class="">
            <div style="height: 25px;"><div style=""><span style="padding-right: 150px;">收款主体名称:</span> <span style="padding-right: 10px;">收款日期:</span><span style="padding-left: 40px;">年</span><span style="padding-left: 30px;">月</span><span style="padding-left: 30px;">日</span></div></div>

            <table >
                <tr>
                    <td>客户编码</td>
                    <td>${clientCode}</td>
                    <td>客户名称</td>
                    <td  ></td>
                    <td  ></td>
                    <td  ></td>
                    <td  >贷款期限</td>
                    <td colspan="2" ></td>
                </tr>
                <tr>
                    <td>业务员</td>
                    <td > </td>
                    <td>贷款额</td>
                    <td> </td>
                    <td>贷款类型</td>
                    <td></td>
                    <td>贷款日期</td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <td colspan="2">收费项目</td>
                    <td>收费项目性质</td>
                    <td>预计应收</td>
                    <td>实收</td>
                    <td>预计应付</td>
                    <td colspan="3">支付对象</td>
                </tr>
                <tr>
                    <td colspan="2"></td>
                    <td  >收入</td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                    <td colspan="3"></td>
                </tr>

                <tr>
                    <td colspan="2"></td>
                    <td  >代收代付</td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="2">合计</td>
                    <td  ></td>
                    <td ></td>
                    <td ></td>
                    <td ></td>
                    <td colspan="3"></td>
                </tr>
            </table>
            <div>
                <label>制单:</label><span style="padding-left:200px;padding-right: 50px;"></span>
                <label>财务负责人:</label><span style="padding-left: 80px;"></span>
            </div>
        </div>
    </div>
</div>
<input type="button" onclick=" a()" value="打印"/>

</body>
<script type="text/javascript">
function  a(){
    $("#ddd").jqprint();
}
</script>
</html>