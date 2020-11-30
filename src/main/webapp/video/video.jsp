<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<script>
    $(function(){
        $("#userTable").jqGrid({
            url:"${path}/video/page",   //page:当前页   rows每页展示条数    page  rows数据 records总条数  total总页数
            editurl:"${path}/video/edit",
            datatype: "json",
            rowNum:2,
            rowList:[3,5,10,20,30],
            pager: '#userPage',
            sortname: 'id',
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            viewrecords: true,  //是否展示总条数书
            colNames:['ID','标题', '简介', '封面','视频','上传时间','点赞数','播放数','类别ID','用户ID','分组ID','状态'],
            colModel:[
                {name:'id',index:'id', width:55},
                {name:'title',editable:true,index:'invdate', width:90},
                {name:'brief',editable:true,index:'name asc, invdate', width:200,align:"center"},
                {name:'coverPath',index:'amount', width:80, align:"right",
                    formatter:function (cellvalue,options,rowObject) {
                        return "<img src='"+cellvalue+"' width='80px' height='50px'>";

                    }
                },
                {name:'videoPath',editable:true, width:200, align:"right",edittype:"file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<video id='media' src='" + cellvalue + "' controls='controls' width='200px' heigt='500px' poster='" + rowObject.cover + "' />";
                    }
                },
                {name:'uploadTime',index:'total', width:80,align:"right"},
                {name:'likeCount',index:'total', width:80,align:"right"},
                {name:'playCount',index:'total', width:80,align:"right"},
                {name:'categoryId',index:'total',editable:true, width:80,edittype:'select',align:"right",editoptions:{dataUrl:"${path}/category/selectTwolevels"}},
                {name:'userId',index:'total', width:80,align:"right"},
                {name:'groupId',index:'total', width:80,align:"right"},
                {name:'status',index:'total', width:80,align:"center",
                    formatter:function(cellvalue, options, rowObject){
                        if(cellvalue==0){
                            return "<button class='btn btn-danger' onclick='changeStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")'>解除冻结</button>";
                        }else{
                            return "<button class='btn btn-success' onclick='changeStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")'>冻结</button>";
                        }
                    }
                },
            ]
        });
        $("#userTable").jqGrid('navGrid','#userPage',{edit:true,add:true,del:true},
            {
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
                afterSubmit: function (data) {  //添加成功之后执行的内容
                    //1.信息入库   返回id
                    //2.文件上传
                    $.ajaxFileUpload({
                        url: "${path}/video/headUpdate",
                        type: "post",
                        // {"id": data.responseText}
                        data:{"id": data.responseText},
                        fileElementId: "videoPath", //文件选择框的id属性，即<input type="file" id="picImg" name="picImg">的id
                        success: function () {
                            //上传成功 所作的事情
                            //刷新页面
                            $("#userTable").trigger("reloadGrid");
                        }
                    });
                    return "hello";
                }

            },
            {
                closeAfterAdd: true,//关闭添加框
                afterSubmit: function (data) {  //添加成功之后执行的内容
                    //1.信息入库   返回id
                    //2.文件上传
                    $.ajaxFileUpload({
                        url: "${path}/video/headUpload",
                        type: "post",
                    // {"id": data.responseText}
                        data:{"id": data.responseText},
                        fileElementId: "videoPath", //文件选择框的id属性，即<input type="file" id="picImg" name="picImg">的id
                        success: function () {
                            //上传成功 所作的事情
                            //刷新页面
                            $("#userTable").trigger("reloadGrid");
                        }
                    });
                    return "hello";
                }
            },
            {
                closeAfterDel: true,
                reloadAfterSubmit: true
            }
        );
    });

    function changeStatus(id,status){

        if(status==0){
            $.post("${path}/video/edit",{"id":id,"status":"1","oper":"edit"},function(data){
                //刷新页面
                $("#userTable").trigger("reloadGrid");
            },"text");
        }else{
            $.post("${path}/video/edit",{"id":id,"status":"0","oper":"edit"},function(data){
                //刷新页面
                $("#userTable").trigger("reloadGrid");
            },"text");
        }
    }
</script>


<%--设置面板--%>
<div class="panel panel-warning" >

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>视频信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户管理</a></li>
    </ul>


    <%--表单--%>
    <table id="userTable" />

    <%--分页工具栏--%>
    <div id="userPage" />

</div>
