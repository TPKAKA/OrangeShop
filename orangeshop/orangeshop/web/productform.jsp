<%-- 
    Document   : newsform
    Created on : Jan 25, 2024, 11:23:51 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Deverlopment-side SEO -->
        <meta charset="UTF-8">
        <meta name="description" content="Khám phá thế giới ngọt ngào tại cửa hàng bánh sinh nhật Việt Nam.
              Orange Cake cung cấp một loạt các loại bánh ngọt tươi ngon, từ bánh cupcake đa dạng hương vị 
              đến bánh tart tinh tế và bánh cookie thơm phức. Hãy đến với chúng tôi để thưởng thức những trải
              nghiệm ngọt ngào và lãng mạn không thể quên!">
        <meta name="keywords" content="Bánh, sinh nhật, bánh ngọt, Việt Nam">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="robot" content="index,follow">
        <title>Product Form</title>
        <link rel="icon" type="image" href="assets/img/favicon.png">

        <!-- Bootstrap Styles-->
        <title>Bootstrap Admin Theme : Master - WebThemez</title>
        <!-- Bootstrap Styles-->
        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FontAwesome Styles-->
        <link href="assets/css/font-awesome.css" rel="stylesheet" />

        <link href="assets/css/select2.min.css" rel="stylesheet" >
        <link href="assets/css/checkbox3.min.css" rel="stylesheet" >
        <!-- Custom Styles-->
        <link href="assets/css/custom-styles.css" rel="stylesheet" />
        <!-- Google Fonts-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />

        <script src="./ckeditor/ckeditor/ckeditor.js" ></script>

    </head>
    <body>
        <div id="wrapper">
            <nav class="navbar navbar-default top-navbar" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="home"><strong><i class="icon fa fa-home"></i>SWP391-KSG5</strong></a>
                    <div id="sideNav" href="">
                        <i class="fa fa-bars icon"></i> 
                    </div>
                </div>

                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li>
                                <a href="#">
                                    <div>
                                        <strong>John Doe</strong>
                                        <span class="pull-right text-muted">
                                            <em>Today</em>
                                        </span>
                                    </div>
                                    <div>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...</div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <strong>John Smith</strong>
                                        <span class="pull-right text-muted">
                                            <em>Yesterday</em>
                                        </span>
                                    </div>
                                    <div>Lorem Ipsum has been the industry's standard dummy text ever since an kwilnw...</div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <strong>John Smith</strong>
                                        <span class="pull-right text-muted">
                                            <em>Yesterday</em>
                                        </span>
                                    </div>
                                    <div>Lorem Ipsum has been the industry's standard dummy text ever since the...</div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>Read All Messages</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                        </ul>
                        <!-- /.dropdown-messages -->
                    </li>
                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-tasks">
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 1</strong>
                                            <span class="pull-right text-muted">60% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                                <span class="sr-only">60% Complete (success)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 2</strong>
                                            <span class="pull-right text-muted">28% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="28" aria-valuemin="0" aria-valuemax="100" style="width: 28%">
                                                <span class="sr-only">28% Complete</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 3</strong>
                                            <span class="pull-right text-muted">60% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                                <span class="sr-only">60% Complete (warning)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <p>
                                            <strong>Task 4</strong>
                                            <span class="pull-right text-muted">85% Complete</span>
                                        </p>
                                        <div class="progress progress-striped active">
                                            <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100" style="width: 85%">
                                                <span class="sr-only">85% Complete (danger)</span>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>See All Tasks</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                        </ul>
                        <!-- /.dropdown-tasks -->
                    </li>
                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-comment fa-fw"></i> New Comment
                                        <span class="pull-right text-muted small">4 min</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                        <span class="pull-right text-muted small">12 min</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-envelope fa-fw"></i> Message Sent
                                        <span class="pull-right text-muted small">4 min</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-tasks fa-fw"></i> New Task
                                        <span class="pull-right text-muted small">4 min</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="#">
                                    <div>
                                        <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                        <span class="pull-right text-muted small">4 min</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a class="text-center" href="#">
                                    <strong>See All Alerts</strong>
                                    <i class="fa fa-angle-right"></i>
                                </a>
                            </li>
                        </ul>

                    </li>

                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">

                            <li class="divider"></li>
                            <li><a href="./logout"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                            </li>
                        </ul>

                    </li>

                </ul>
            </nav>
            <!--/. NAV TOP  -->
            <nav class="navbar-default navbar-side" role="navigation">
                <div class="sidebar-collapse">
                    <ul class="nav" id="main-menu">
                        <li>
                            <a href="dashboard"><i class="fa fa-dashboard"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap"></i> Manage<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">User Management<span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="manageaccount">Customer Management</a>
                                        </li>
                                        <li>
                                            <a href="StaffManagement">Staff Management</a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">News Management<span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="newsmanagement">News</a>
                                        </li>
                                        <li>
                                            <a href="newscategoriesmanagement">News Category</a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">Product Management<span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="productmanagement">Product</a>
                                        </li>
                                        <li>
                                            <a href="productcategoriesmanagement">Product Category</a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="callapi.jsp"><i class="fa fa-money"></i> KiotViet'Product Selling</a>
                        </li>
                    </ul>

                </div>
            </nav>
            <!-- /. NAV SIDE  -->
            <div id="page-wrapper" >
                <div class="header"> 
                    <h1 class="page-header">
                        Product Form <small>For create and edit Product.</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Forms</a></li>
                        <li class="active">Data</li>
                    </ol> 

                </div>

                <div id="page-inner">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="card-title">
                                        <h3><div class="title">Basic Infomation</div></h3>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <form action="productform" method="POST" enctype="multipart/form-data">
                                        <div class="sub-title">Category <span style="color: red;">*</span></div>
                                        <div class="form-group">
                                            <select name="category" class="selectbox" required>                                                
                                                <c:forEach items="${categoryList}" var="c">
                                                    <c:if test="${product.categoryid == c.categoryid}">
                                                        <option value="${c.categoryid}">${c.categoryname}</option>
                                                    </c:if>
                                                </c:forEach>
                                                <c:forEach items="${categoryList}" var="c">
                                                    <c:if test="${product.categoryid != c.categoryid}">
                                                        <option value="${c.categoryid}">${c.categoryname}</option>
                                                    </c:if>
                                                </c:forEach>                                                     
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Name <span style="color: red;">${verifyName == null ? "*" : verifyName}</span></label>    
                                            <input type="text" name="productName" class="form-control" value="${product.name}" required>
                                        </div>                                       
                                        <div class="form-group">
                                            <label for="exampleInputFile">Image<span style="color: red;">${verifyImg ne null ? verifyImg : "*"}</span></label><br/>
                                            <c:forEach items="${images}" var="img" varStatus="i">
                                                <img id="previewImage${i.index}" src="assets/img/shop/${img.imglink}" style="height:100px; width: auto;"/>
                                                <input name="image${i.index}" type="file" id="exampleInputFile${i.index}" onchange="previewFile(${i.index})" accept=".png, .jpg" style="color: transparent; text: none;">
                                                <input name="image${i.index}" type="file" value="assets/img/shop/${img.imglink}" style="display: none;" hidden>
                                            </c:forEach>
                                            
                                            <c:forEach begin="${images ne null ? images.size() : 0}" end="4" var="img" varStatus="i">
                                                <img id="previewImage${i.index}" src="" style="height:100px; width: auto;"/>
                                                <input name="image${i.index}" type="file" id="exampleInputFile${i.index}" onchange="previewFile(${i.index})" accept=".png, .jpg" style="color: transparent; text: none;">
                                            </c:forEach>            
                                            <p class="help-block">Accept .JPG, .PNG. Maximum capacity 5MB.</p>
                                        </div>
                                        <script>
                                            function previewFile(img) {
                                                var input = document.getElementById('exampleInputFile' + img);
                                                var preview = document.getElementById('previewImage' + img);

                                                var reader = new FileReader();

                                                reader.onload = function (e) {
                                                    preview.src = e.target.result;
                                                };

                                                reader.readAsDataURL(input.files[0]);
                                            }
                                        </script>
                                        <div class="sub-title">Description <span style="color: red;">${verifyDescription == null ? "*" : verifyDescription}</span></div>
                                        <div>
                                            <textarea id="describe" type="text" name="productDescription" class="form-control" rows="10" required>${product.description}</textarea>
                                        </div>

                                        <div class="card-title" style="margin-top: 15px; margin-bottom: 15px;">
                                            <h3><div class="title">Sales Infomation</div></h3>
                                        </div>
                                        <div class="form-group">
                                            <label>Price <span style="color: red;">${verifyPrice == null ? "*" : verifyPrice}</span></label>
                                            <input id="priceInput" oninput="formatCurrency(this)" type="text" name="price" class="form-control" value="${product.price}" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Add quantity <span style="color: red;">${verifyQuantity}</span></label>
                                            <input id="quantityInput" oninput="formatCurrency(this)" type="text" name="addQuantity" class="form-control" value="${product.totalproduct}">
                                        </div>

                                        <div style="margin-top: 15px;">
                                            <input name="id" value="${product.productid}" hidden>
                                            <button type="submit" class="btn btn-default">Submit</button>                                       
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--<footer><p>All right reserved. Template by: <a href="http://webthemez.com">WebThemez.com</a></p></footer>-->
                </div>
                <!-- /. PAGE INNER  -->
            </div>
            <!-- /. PAGE WRAPPER  -->
        </div>
        <!-- /. WRAPPER  -->
        <!-- JS Scripts-->
        <!-- jQuery Js -->
        <script src="assets/js/jquery-1.10.2.js"></script>
        <!-- Bootstrap Js -->
        <script src="assets/js/bootstrap.min.js"></script>
        <!-- Metis Menu Js -->
        <script src="assets/js/jquery.metisMenu.js"></script>
        <script src="assets/js/select2.full.min.js"></script>
        <script type="text/javascript">
                                                $(document).ready(function () {
                                                    $(".selectbox").select2();
                                                });
        </script>
        <!-- Custom Js -->
        <script src="assets/js/custom-scripts.js"></script> 

        <script>
                                                function formatCurrency(input) {
                                                    // Lấy giá trị nhập vào từ input
                                                    let inputValue = input.value;

                                                    // Loại bỏ mọi ký tự không phải số từ chuỗi
                                                    inputValue = inputValue.replace(/[^\d]/g, '');

                                                    // Thêm dấu phẩy sau mỗi 3 chữ số từ dưới lên
                                                    for (let i = inputValue.length - 3; i > 0; i -= 3) {
                                                        inputValue = inputValue.slice(0, i) + ',' + inputValue.slice(i);
                                                    }

                                                    // Gán giá trị đã định dạng trở lại input
                                                    input.value = inputValue;
                                                }
        </script>


        <script> CKEDITOR.replace('describe');</script>


    </body>
</html>
