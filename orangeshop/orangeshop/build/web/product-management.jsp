<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
        <title>Product Management</title>
        <link rel="icon" type="image" href="assets/img/favicon.png">

        <link href="assets/css/bootstrap.css" rel="stylesheet" />
        <!-- FontAwesome Styles-->
        <link href="assets/css/font-awesome.css" rel="stylesheet" />
        <!-- Morris Chart Styles-->
        <!-- Custom Styles-->
        <link href="assets/css/custom-styles.css" rel="stylesheet" />
        <!-- Google Fonts-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        <!-- TABLE STYLES-->
        <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
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
                    <div id="sideNav" href="" class>
                        <i class="fa fa-bars icon"></i>
                    </div>
                </div>

                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                       
                        
                    </li>
                   
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                       
                        
                    </li>
                 
                  
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li> <a  href="UserProfile?uid=${sessionScope.admin.getUserID()}" ><i class="fa fa-user fa-fw"></i> User Profile</a>
                            </li>
                            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                            </li>
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
                                <li>
                                    <a href="#">Order Management<span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="ordermanager">Order Management</a>
                                        </li>
                                        <li>
                                            <a href="requestorder">Order From KiotViet</a>
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
            <div id="page-wrapper">
                <div class="header">
                    <h1 class="page-header">
                        Product Management
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#">Home</a></li>
                        <li><a href="#">Tables</a></li>
                        <li class="active">Product</li>
                    </ol>

                </div>
                <div>
                    <c:if test="${mess!=null }">
                        <div class="alert alert-success" role="alert">
                            ${mess}
                        </div>
                    </c:if>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <!-- Advanced Tables -->
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div style="display: flex;">
                                    <div>
                                        <h3>Category</h3>
                                    </div>
                                    <div style="margin: 13px;">
                                        <select onchange="redirectOnChange(this)">
                                            <optgroup label="Category">
                                                <c:forEach items="${categoryList}" var="c">
                                                    <c:if test="${c.categoryid == categoryId}">
                                                        <option value="${c.categoryid}"
                                                                data-href="productmanagement?categoryId=${c.categoryid}" selected>
                                                            ${c.categoryname}
                                                        </option>
                                                    </c:if>
                                                    <c:if test="${c.categoryid != categoryId}">
                                                        <option value="${c.categoryid}"
                                                                data-href="productmanagement?categoryId=${c.categoryid}">
                                                            ${c.categoryname}
                                                        </option>
                                                    </c:if>
                                                </c:forEach>
                                            </optgroup>
                                        </select>

                                        <script>
                                            function redirectOnChange(select) {
                                                var selectedOption = select.options[select.selectedIndex];
                                                var href = selectedOption.getAttribute("data-href");
                                                window.location.href = href;
                                            }
                                        </script>
                                    </div>
                                    <div style="margin: 10px;">
                                        <form action="productmanagement" method="POST">
                                            <input name="productName" type="text" placeholder="Enter Product name" required>
                                            <button type="submit" class="btn btn-primary"><i
                                                    class="fa fa-search"></i></button>
                                        </form>
                                    </div>
                                    <div style="margin: 10px;">
                                        <a href="productform"><button type="submit" class="btn btn-primary">Add
                                                Product</button></a>
                                    </div>
                                    <div class="shop__pagination" style="margin-top: 10px; justify-content: center; margin-left: auto;">
                                        <c:if test="${page ne null}">
                                            <a href="productmanagement?category=${category}&page=${page - 1}"><button class="btn btn-primary"><</button></a>
                                            <button class="btn btn-primary">${page == null ? 1 : page}</button>
                                            <a href="productmanagement?category=${category}&page=${page + 1}"><button class="btn btn-primary">></button></a>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Price</th>
                                                <th>Quantity</th>
                                                <th>Sold</th>
                                                <th>Operation</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${productList}" var="c">
                                                <tr class="gradeU">
                                                    <td>${c.key.productid}</td>
                                                    <td>${c.key.name}</td>
                                                    <td class="formattedPrice">${c.key.price}</td>
                                                    <td>${c.key.totalproduct}</td>
                                                    <td>${c.value}</td>
                                                    <td style="display: flex;">
                                                        <a href="productform?id=${c.key.productid}" style="margin-right: 10px;">
                                                            <button type="submit" class="btn btn-primary">Edit</button>
                                                        </a>
                                                        <form id="${c.key.productid}" action="productstatus">
                                                            <input type="text" name="productId" value="${c.key.productid}" hidden/>
                                                            <c:if test="${c.key.status == true}">
                                                                <button type="button" class="btn btn-primary" onclick="confirmStatus('ngừng ', ${c.key.productid})">ON</button>
                                                            </c:if>
                                                            <c:if test="${c.key.status == false}">
                                                                <button type="button" class="btn btn-danger" onclick="confirmStatus('', ${c.key.productid})">OFF</button>
                                                            </c:if>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!--End Advanced Tables -->
                    </div>
                </div>
            </div>
        </div>
        <!-- /. ROW  -->

        <!-- /. PAGE WRAPPER  -->
        <!-- /. WRAPPER  -->
        <!-- JS Scripts-->
        <!-- jQuery Js -->
         <!--<script src="assets/js/jquery-1.10.2.js"></script>-->
        <!-- Bootstrap Js -->
        <script src="assets/js/bootstrap.min.js"></script>
        <!-- Metis Menu Js -->
        <script src="assets/js/jquery.metisMenu.js"></script>
        <!-- DATA TABLE SCRIPTS -->
        <script src="assets/js/dataTables/jquery.dataTables.js"></script>
        <script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
        <script>
                                                                    $(document).ready(function () {
                                                                        $('#dataTables-example').dataTable();
                                                                    });
        </script>
        <!-- Custom Js -->
        <script src="assets/js/custom-scripts.js"></script>
        <script>
                                                                    function confirmStatus(action, productId) {
                                                                        if (confirm("Bạn có chắc muốn " + action + "bán sản phẩm này?")) {
                                                                            var form = document.getElementById(productId);
                                                                            form.submit();
                                                                        }
                                                                    }
        </script>
        <script>
                            // Lấy tất cả các phần tử có class là 'formattedPrice'
                            var priceElements = document.querySelectorAll('.formattedPrice');

                            // Duyệt qua từng phần tử và định dạng giá tiền của mỗi sản phẩm
                            priceElements.forEach(function (element) {
                                var price = parseFloat(element.textContent); // Chuyển đổi giá tiền thành số
                                var formattedPrice = price.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                                formattedPrice = formattedPrice.replace(' đ', ''); // Loại bỏ ký tự ' đ'
                                element.textContent = formattedPrice; // Gán giá tiền đã định dạng vào phần tử
                            });
        </script>


    </body>

</html>