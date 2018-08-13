<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>JQuery easyui demo</title>
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.1/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.1/themes/color.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.4.1/demo/demo.css">
<script type="text/javascript" src="jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}
.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}
.fitem {
	margin-bottom: 5px;
}
.fitem label {
	display: inline-block;
	width: 80px;
}
.fitem input {
	width: 160px;
}
</style>
</head>
<body>
	<!-- <div style="height: 70px; width: 100%;">
    	<form id="ff" method="post">   
    <div style="float: left; padding:20px 0 0 20px; ">   
        <label for="name">用户名:</label>   
        <input class="easyui-validatebox" type="text" name="name"  />   
    </div>   
    <div style="float: left;padding:20px 0 0 20px;">   
        <label for="email">邮箱:</label>   
        <input class="easyui-validatebox" type="text" name="email" data-options="validType:'email'" />   
    </div>  
    <div style="float: left;padding:20px 0 0 20px;">   
        <input class="easyui-validatebox" type="button" value="查询" />   
    </div> 
</form>  
    </div> -->
	<table id="dg" class="easyui-datagrid" style="height: 670px;"
		url="Admin/User?action=findAll" toolbar="#toolbar" pagination="true"
		rownumbers="true" fitColumns="true" singleSelect="true"
		data-options="fit:false,border:false,pageSize:5,pageList:[5,10,15,20]">
		<thead>
			<tr>
				<!-- json -->
				<th field="head" width="50">头像</th>
				<th field="password" width="50">密码</th>
				<th field="birthday" width="50">生日</th>
					<th field="name" width="50">姓名</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newUser()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
	</div>
	<div id="dlg" class="easyui-dialog" style="padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">详细信息</div>
		<form id="fm" method="post" novalidate>
				<div class="fitem">
				<label>头像:</label> <input name="head" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>密码:</label> <input name="password" class="easyui-textbox"
					required="true">
			</div>
			<div class="fitem">
				<label>生日:</label> <input name="birthday" class="easyui-textbox"
					required="true">
			</div>
		<div class="fitem">
				<label>姓名:</label> <input name="name" class="easyui-textbox"
					required="true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveUser()" style="width: 90px">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')"
			style="width: 90px">取消</a>
	</div>
	<script type="text/javascript">
		var url;
		function newUser() {
			$('#dlg').dialog('open').dialog('setTitle', '新增');
			$('#fm').form('clear');
			url = 'Admin/User?action=addUsers';
		}
		function editUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '编辑');
				$('#fm').form('load', row);
				url = 'Admin/User?action=updateUsers&id=' + row.id;
			}
		}
		function saveUser() {
			$('#fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.errorMsg) {
						$.messager.show({
							title : 'Error',
							msg : result.errorMsg
						});
					} else {
						$('#dlg').dialog('close'); // close the dialog
						$('#dg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyUser() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('Confirm', '确定要删除吗?', function(r) {
					if (r) {
						$.post('Admin/User?action=deleteUsers', {
							id : row.id
						}, function(result) {
							if (result.success) {
								$('#dg').datagrid('reload'); // reload the user data
							} else {
								$.messager.show({ // show error message
									title : 'Error',
									msg : result.errorMsg
								});
							}
						}, 'json');
					}
				});
			}
		}
	</script>
</body>
</html>