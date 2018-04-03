<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>人事管理系统——修改员工</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
	<meta http-equiv="description" content="This is my page" />
	<link href="${pageContext.request.contextPath }/css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
	<link href="${pageContext.request.contextPath }/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-migrate-1.2.1.js"></script>
	<script src="${pageContext.request.contextPath }/js/ligerUI/js/core/base.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script> 
	<script src="${pageContext.request.contextPath }/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath }/css/pager.css" type="text/css" rel="stylesheet" />
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	$(function(){
		$.get("${pageContext.request.contextPath }/employee?method=findJob",function(obj){
  		  var $job=$("#job_id");
  		 $job.html("<option>--请选择职位--</option>");
  		 $(obj).each(function () {
			$job.append($("<option value="+this.jId+">"+this.jName+"</option>"));
			}); 
  	   },"json");
	
		$.get("${pageContext.request.contextPath }/employee?method=findDept",function(obj){
		   var $dept=$("#dept_id");
		   $dept.html("<option>--部门选择--</option>");
		 $(obj).each(function () {
			$dept.append($("<option value="+this.dId+">"+this.dName+"</option>"));
			}); 
	   },"json");
		
		
		// 控制文档加载完成以后 选中性别 
		$("#sex").val("${employee.eGender}");
		$("#job_id").val("${employee.job.jId}");
		
    	/** 员工表单提交 */
		$("#employeeForm").submit(function(){
			alert(1);
			var name = $("#name");
			var cardId = $("#cardId");
			var education = $("#education");
			var email = $("#email");
			var phone = $("#phone");
			var address = $("#address");
			var msg = "";
			if ($.trim(name.val()) == ""){
				msg = "姓名不能为空！";
				name.focus();
			}else if ($.trim(cardId.val()) == ""){
				msg = "身份证号码不能为空！";
				cardId.focus();
			}else if (!/^[1-9]\d{16}[0-9A-Za-z]$/.test($.trim(cardId.val()))){
				msg = "身份证号码格式不正确！";
				cardId.focus();
			}else if ($.trim(education.val()) == ""){
				msg = "学历不能为空！";
				education.focus();
			}else if ($.trim(email.val()) == ""){
				msg = "邮箱不能为空！";
				email.focus();
			}else if (!/^\w+@\w{2,3}\.\w{2,6}$/.test($.trim(email.val()))){
				msg = "邮箱格式不正确！";
				email.focus();
			}else if ($.trim(phone.val()) == ""){
				msg = "手机号码不能为空！";
				phone.focus();
			}else if (!/^1[3|5|8]\d{9}$/.test($.trim(phone.val()))){
				msg = "手机号码格式不正确！";
				phone.focus();
			}else if ($.trim(address.val()) == ""){
				msg = "地址不能为空！";
				address.focus();
			}
			if (msg != ""){
				$.ligerDialog.error(msg);
				return false;
			}else{
				return true;
			}
			$("#employeeForm").submit();
		});
    });
		
	function cancelClick() {
		if(confirm("你确定要取消么？")){
			location.href="${pageContext.request.contextPath}/employee?method=jumpEmploy";
		}
	}

	</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td height="10"></td></tr>
  <tr>
    <td width="15" height="32"><img src="${pageContext.request.contextPath }/images/main_locleft.gif" width="15" height="32"></td>
	<td class="main_locbg font2"><img src="${pageContext.request.contextPath }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：员工管理  &gt; 修改员工</td>
	<td width="15" height="32"><img src="${pageContext.request.contextPath }/images/main_locright.gif" width="15" height="32"></td>
  </tr>
</table>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
  <tr valign="top">
    <td>
    	 <form action="${pageContext.request.contextPath }/employee?method=updateEmployee" id="employeeForm" method="post">
			<!-- 隐藏表单，flag表示添加标记 -->
    	 	<input type="hidden" name="flag" value="2">
			<input type="hidden" name="eId" value="${employee.eId }">
		  <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
		    <tr><td class="font3 fftd">
		    	<table>
		    		<tr>
		    			<td class="font3 fftd">姓名：<input type="text" name="eName" id="name" size="20" value="${employee.eName }"/></td>
		    			<td class="font3 fftd">身份证号码：<input type="text" name="eIdCard" id="cardId" size="20" value="${employee.eIdCard }"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">性别：
								<select id="sex" name="eGender" style="width:143px;">
									<option value="0">--请选择性别--</option>
									<option value="1">男</option>
									<option value="2">女</option>
					    		</select>
					    </td>
		    			<td class="font3 fftd">职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：
		    			 	<select id="job_id" name="jId" id="job_id" style="width:143px;">
					    			<option value="0">--请选择职位--</option>
					    				<option value="${job.jId }">${job.jName }</option>
					    		</select>
					    </td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">学历：<input name="eStu" id="education" size="20" value="${employee.eStu }"/></td>
		    			<td class="font3 fftd">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<input name="eEmail" id="email" size="20" value="${employee.eEmail }"/></td>
		    		</tr>
		    		<tr>
		    			<td class="font3 fftd">手机：<input name="eTelNum" id="phone" size="20" value="${employee.eTelNum }"/></td>
		    		</tr>
		    		
		    	</table>
		    </td></tr>
			<tr><td class="main_tdbor"></td></tr>
			
			
			
			<tr>
				<td class="font3 fftd">
					联系地址：<input name="eAddress" id="address" size="40" value="${employee.eAddress }"/>&nbsp;&nbsp;
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			
			
			
			
			<tr>
				<td class="font3 fftd">
					&nbsp;&nbsp;所属部门：
					<select  name="dId" id="dept_id" style="width:100px;">
						   <option value="0">--部门选择--</option>
			    				<option value="${dept.dId }">${dept.dName }</option>
					</select>
				</td>
			</tr>
			<tr><td class="main_tdbor"></td></tr>
			
			<tr><td align="left" class="fftd"><input type="submit" value="修改">&nbsp;&nbsp;<input type="reset" value="取消 " onclick="cancelClick()"></td></tr>
		  </table>
		 </form>
	</td>
  </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>