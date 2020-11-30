<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<script>
    $(function () {
        //创建jqgrid  1.初始化参数全部以js对象形式放入jqGri的方法里
        $("#logList").jqGrid({
            styleUI: "Bootstrap", //构建一个bootstrap风格表格
            url:"${pageContext.request.contextPath}/log/pageAll",//用来发送服务器端地址
            datatype:"json",//定义服务器端返回数据格式 为 JSON
            colNames:["ID","管理人员","操作时间","操作方法","状态"],//用来定义表格中显示列
            colModel:[
                {name:"id"},
                {name:"userName",editable:true},
                {name:"operationTime",align:'center',editable:true},
                {name:"oper",width:"90",fixed:true,editable:true},
                {name:"status",editable:true},
            ],
            //用来定义表格中每一个列  在jqgrid中认为每一个列又是对象  列对象参数
            pager:"#pager",//让表格展示分页工具栏 后台项目   select * from t_user limit (当前页-1)*每页显示记录数,每页显示记录数   控制器 当前页(page)  每页显示记录数(rows)
            page:1,//指定初始页码 当前页默认1
            rowNum:2,//默认20  每页显示记录数
            rowList:[2,4,8,10,15,20,50],
            viewrecords:true,//显示数据库中总记录数
            // sortname:"bir",//使用那个字段排序  通过后台定义一个参数为sidx的变量接收
            // caption:"用户列表",//用来定义标题
            autowidth:true,//自适应父容器
            // multiselect:true,//开启多行选择   true 显示checkbox
            // rownumbers:false,//用来展示表格行号
            editurl:"${pageContext.request.contextPath}/user/edit",
        }).navGrid("#pager",
            {add:false,edit:false,del:false,search:false,refresh:false},  //对展示增删改按钮配置
            {
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
            },  //对编辑配置
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            },  //对添加配置
            {
                closeAfterDelete: true, //不生效
                reloadAfterSubmit: true
            },  //对删除配置
            {
                sopt : [ 'cn', 'eq'],//用来展示那些搜索的运算符
            });//对搜索的配置
    })



</script>
<%--面板--%>
<div class="panel panel-danger">
    <%--面板头--%>
    <div class="panel panel-danger">
        <h3>日志信息</h3>
    </div>
    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">日志</a></li>
    </ul>
        <br>
    <%--表单--%>
    <table id="logList"/>
    <%--分页工具栏--%>
    <div id="pager"></div>
</div>