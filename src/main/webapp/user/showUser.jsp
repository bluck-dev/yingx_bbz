<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<script>
    $(function () {
        //创建jqgrid  1.初始化参数全部以js对象形式放入jqGri的方法里
        $("#userList").jqGrid({
            styleUI: "Bootstrap", //构建一个bootstrap风格表格
            url:"${pageContext.request.contextPath}/user/pageAll",//用来发送服务器端地址
            datatype:"json",//定义服务器端返回数据格式 为 JSON
            colNames:["ID","头像","昵称","简介","手机号","状态","注册时间"],//用来定义表格中显示列
            colModel:[
                {name:"id"},
                {name:"picImg",editable:true,edittype:"file",
                    formatter:function (cellvalue,options,rowObject) {
                        return "<img src='"+cellvalue+"' width='80px' height='50px'>";
                        <%--return "<img src='${picImg}' width='80px' height='50px'>";--%>

                    }
                },
                {name:"nickName",align:'center',editable:true},
                {name:"brief",width:"90",fixed:true,editable:true,},  //select <option value="1">男</option>//value 本地写死   dataUrl:"/dept/findAll" 返回html数据 <select><option></option></select>
                {name:"phone",editable:true},
                {name:"status",editable:true,
                    formatter:function (cellvalue,options,rowObject) {
                        if(cellvalue==0){
                            return "<button class='btn btn-danger' onclick='changeStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")'>解除冻结</button>";
                        }else{
                            return "<button class='btn btn-success' onclick='changeStatus(\""+rowObject.id+"\",\""+rowObject.status+"\")'>冻结</button>";
                        }
                    }
                },
                {name:"createDate"},
            ],
            //用来定义表格中每一个列  在jqgrid中认为每一个列又是对象  列对象参数
            pager:"#pager",//让表格展示分页工具栏 后台项目   select * from t_user limit (当前页-1)*每页显示记录数,每页显示记录数   控制器 当前页(page)  每页显示记录数(rows)
            page:1,//指定初始页码 当前页默认1
            rowNum:2,//默认20  每页显示记录数
            rowList:[2,4,8,10,15,20,50],
            viewrecords:true,//显示数据库中总记录数
            // sortname:"bir",//使用那个字段排序  通过后台定义一个参数为sidx的变量接收
            // caption:"用户列表",//用来定义标题
            // autowidth:true,//自适应父容器
            // multiselect:true,//开启多行选择   true 显示checkbox
            // rownumbers:false,//用来展示表格行号
            editurl:"${pageContext.request.contextPath}/user/edit",
        }).navGrid("#pager",
            {add:true,edit:true,del:true,search:true,refresh:true},  //对展示增删改按钮配置
            {
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
            },  //对编辑配置
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
                afterSubmit: function (data) {  //添加成功之后执行的内容
                    //1.信息入库   返回id
                    //2.文件上传
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/user/Upload",
                        type: "post",
                        // {"id": data.responseText}
                        data:{"id": data.responseText},
                        fileElementId: "picImg", //文件选择框的id属性，即<input type="file" id="picImg" name="picImg">的id
                        success: function () {
                            //上传成功 所作的事情
                            //刷新页面
                            $("#userList").trigger("reloadGrid");
                        }
                    });
                    return "hello";
                }
            },  //对添加配置
            {
                closeAfterDelete: true, //不生效
                reloadAfterSubmit: true
            },  //对删除配置
            {
                sopt : [ 'cn', 'eq'],//用来展示那些搜索的运算符
            });//对搜索的配置
    })
    function changeStatus(id,status){
        if(status==0){
            $.post("${pageContext.request.contextPath}/user/edit",{"id":id,"status":"1","oper":"edit"},function(data){
                //刷新页面
                $("#userList").trigger("reloadGrid");
            },"text");
        }else{
            $.post("${pageContext.request.contextPath}/user/edit",{"id":id,"status":"0","oper":"edit"},function(data){
                //刷新页面
                $("#userList").trigger("reloadGrid");
            },"text");
        }
    }

    $(function () {
        $("#aliyun").click(
            function () {
                //先获取输入框输入的手机号
                var phone = $("#phone").val();
                $.post("${pageContext.request.contextPath}/user/phone",{"phoneNumbers":phone},function (aa) {
                    if (aa.status=="11") {
                        alert(aa.sign);
                    }else {
                        alert(aa.sign);
                    }

                },"JSON")

            })

    })
</script>
<%--面板--%>
<div class="panel panel-success">
    <%--面板头--%>
    <div class="panel panel-heading">
        <h3>用户信息</h3>
    </div>
    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户管理</a></li>
    </ul>
        <br>
        <div>
            <div class="pull-left">
                <button class="btn btn-info"><a href="${pageContext.request.contextPath}/user/EasyPoi">导出用户信息</a></button>
                <button class="btn btn-success">导入用户</button>
                <button class="btn btn-danger">测试按钮</button>
            </div>
            <div class="pull-right col-sm-5">
                <form>
                    <div class="col-md-4 col-md-offset-6" style="padding: 0px;">
                        <input type="text" class="form-control" id="phone" name="phoneNumbers" placeholder="请输入手机号..." required
                               minlength="11">
                    </div>
                    <div class="col-md-2 pull-right" style="padding: 0px;">
                        <button type="button" id="aliyun" class="btn btn-info btn-block">发送验证码</button>
                    </div>
                </form>
            </div>
        </div>
        <br>
        <br>
    <%--表单--%>
    <table id="userList"/>
    <%--分页工具栏--%>
    <div id="pager"></div>
</div>