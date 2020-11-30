<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>
    <!--顶部导航-->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">应学视频App后台管理系统</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="#">欢迎:${admin.userName} <span class="sr-only">(current)</span></a></li>
                    <li><a href="${path}/admin/end">退出系统</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <!--栅格系统-->
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2">
                <%--菜单--%>
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <%--面板--%>
                    <div class="panel panel-success">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title text-center">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    用户管理
                                </a>
                            </h4>
                        </div>
                        <%--面板内容--%>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body text-center">
                                <ul class="list-group">
                                    <li class="list-group-item" style="background: yellow"><a href="javascript:$('#mainId').load('${path}/user/showUser.jsp')">用户展示</a></li>
                                </ul>
                                <ul class="list-group">
                                    <li class="list-group-item" style="background: yellow"><a href="javascript:$('#mainId').load('${path}/user/echartsUser.jsp')">用户统计</a></li>
                                </ul>
                                <ul class="list-group">
                                    <li class="list-group-item" style="background: yellow"><a href="javascript:$('#mainId').load('${path}/user/chinaUser.jsp')">用户分布</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-info">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-title text-center">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    分类管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                            <div class="panel-body text-center" >
                                <ul class="list-group">
                                    <li class="list-group-item" style="background: yellow"><a href="javascript:$('#mainId').load('${path}/category/cateGory.jsp')">分类展示</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="panel panel-warning">
                        <div class="panel-heading" role="tab" id="headingThree">
                            <h4 class="panel-title text-center">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    视频管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                            <div class="panel-body text-center">
                                <ul class="list-group">
                                    <li class="list-group-item" style="background: yellow"><a href="javascript:$('#mainId').load('${path}/video/video.jsp')">视频信息</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                        <div class="panel panel-danger">
                            <div class="panel-heading" role="tab" id="headingFore" >
                                <h4 class="panel-title text-center">
                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFore" aria-expanded="false" aria-controls="collapseFore">
                                        日志管理
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseFore" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFore">
                                <div class="panel-body text-center">
                                    <ul class="list-group">
                                        <li class="list-group-item" style="background: yellow"><a href="javascript:$('#mainId').load('${path}/log/log.jsp')">日志信息</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
        <!--左边手风琴部分-->
        <!--巨幕开始-->
            <div class="col-sm-10" id="mainId">
                <div class="jumbotron" style="width: 1200px;height: 200px">
                    <h1>欢迎来到应学视频App后台管理系统</h1>
                </div>
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <img src="${path}/imgs/1.jpg" style="width:1200px;height: 300px" alt="...">
                            <div class="carousel-caption">
                                ...
                            </div>
                        </div>
                        <div class="item">
                            <img src="${path}/imgs/2.jpg" style="width: 1200px;height: 300px" alt="...">
                            <div class="carousel-caption">
                                ...
                            </div>
                        </div>
                        <div class="item">
                            <img src="${path}/imgs/3.jpg" style="width: 1200px;height: 300px" alt="...">
                            <div class="carousel-caption">
                                ...
                            </div>
                        </div>
                        ...
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        <!--右边轮播图部分-->
        <!--页脚-->
            <nav class="navbar navbar-default navbar-fixed-bottom">
                <div class="container ">
                    <h5 class="text-center">network@exceee...</h5>
                </div>
            </nav>
        </div>
    </div>
    <!--栅格系统-->

</body>
</html>
