<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<script>

    $(function(){
        pageInit();
    });

    function pageInit(){
        $("#cateTable").jqGrid({
            url : "${path}/category/page",
            datatype : "json",
            pager : '#catePage',
            page:1,//指定初始页码 当前页默认1
            rowNum:2,//默认20  每页显示记录数
            rowList:[2,4,8,10,15,20,50],
            viewrecords:true,//显示数据库中总记录数
            // sortname : 'id',
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [ 'ID', '名称', '级别', '父类级别ID' ],
            colModel : [
                {name : 'id',index : 'id',  width : 55},
                {name : 'cateName',index : 'cateName',width : 90,editable:true,},
                {name : 'levels',index : 'levels',width : 100},
                {name : 'parentId',index : 'parentId',width : 80,align : "right"},
            ],
            subGrid : true,  //开启子表格
            editurl:"${path}/category/edit",
            // subgrid_id:是在创建表数据时创建的div标签的ID
            //row_id是该行的ID
            subGridRowExpanded : function(subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            }
        });
        $("#cateTable").jqGrid('navGrid', '#catePage', {add : true,edit : true,del : true},
            {
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
            },  //修改
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            }, //添加
            {
                // 删除成功之后触发的function,接收删除返回的提示信息,在页面做展示
                closeAfterDelete: true, //不生效
                reloadAfterSubmit: true,
                afterSubmit:function (after) {
                    alert(after.responseJSON.message);
                    return "true";
                }
            }//删除

        );
    }


    //开启子表格的样式
    function addSubGrid(subgridId, rowId){

        var subgridTableTd= subgridId + "Table";
        var pagerId= subgridId+"Page";


        $("#"+subgridId).html("" +
            "<table id='"+subgridTableTd+"' />" +
            "<div id='"+pagerId+"' />"
        );



        $("#" + subgridTableTd).jqGrid({
            url : "${path}/category/page2?id=" + rowId,
            datatype : "json",
            rowNum : 20,
            pager : "#"+pagerId,
            viewrecords:true,//显示数据库中总记录数
            sortname : 'num',
            sortorder : "asc",
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            editurl:"${path}/category/edit",
            colNames : [ 'ID', '名称', '级别', '父类级别ID' ],
            colModel : [
                {name : 'id',index : 'id',  width : 55},
                {name : 'cateName',index : 'cateName',width : 90,editable:true,},
                {name : 'levels',index : 'levels',width : 100},
                {name : 'parentId',editable:true,edittype:'select',index : 'parentId',width : 80,align : "right",editoptions:{dataUrl:"${path}/category/selectOnelevels"}},
            ]
        });

        $("#" + subgridTableTd).jqGrid('navGrid',"#" + pagerId, {edit : true,add : true,del : true},
            {
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
            },  //修改
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            }, //添加
            {
                // 删除成功之后触发的function,接收删除返回的提示信息,在页面做展示
                closeAfterDelete: true, //不生效
                reloadAfterSubmit: true,
                afterSubmit:function (after) {
                    alert(after.responseJSON.message);
                    return "true";
                }
            }//删除

        );
    }


</script>


<%--设置面板--%>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">类别管理</a></li>
    </ul>

    <%--表单--%>
    <table id="cateTable" />

    <%--分页工具栏--%>
    <div id="catePage" />

</div>

<%--
    删除要有提示信息
--%>
