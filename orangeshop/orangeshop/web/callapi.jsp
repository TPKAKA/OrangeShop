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
        <title>KiotViet'Product Selling</title>
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
    <style>
        .header-control {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
        }

        .header-control form {
            margin: 0;
        }

        .header-control form:first-child {
            display: flex;
            align-items: center;
        }

        .header-control input[name="txt"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
            width: 150px; /* Điều chỉnh chiều rộng của ô tìm kiếm */
        }

        .header-control .btn-danger {
            display: flex;
            align-items: center;
        }

        .header-control .search {
            display: flex;
            align-items: center;
        }

        .header-control .search input[name="txt"] {
            width: 100%;
            margin-right: 5px;
        }

        .header-control .search button {
            padding: 10px 15px;
            background-color: #F36A5A;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            font-size: 10px;
            margin-left: 0;
        }

        /* Responsive Design */
        @media screen and (max-width: 600px) {
            .header-control {
                flex-direction: column;
                align-items: stretch;
            }

            .header-control .search input[name="txt"] {
                width: auto;
                margin-bottom: 5px;
            }
        }

    </style>

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
                    <a class="navbar-brand" href="dashboard"><strong><i class="icon fa fa-plane"></i> SWP391-KS-G5</strong></a>

                    <div id="sideNav" href="">
                        <i class="fa fa-bars icon"></i> 
                    </div>
                </div>

                <ul class="nav navbar-top-links navbar-right">



                    <li class="dropdown">


                    </li>

                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                            <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li> <a  href="UserProfile?uid=${sessionScope.admin.getUserID()}" ><i class="fa fa-user fa-fw"></i> User Profile</a>
                            </li>

                            <li class="divider"></li>
                            <li><a href="./logout">Sign out</a><i class="fa fa-sign-out fa-fw"></i> Logout</a>
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
            <div id="page-wrapper" >
                <div class="header"> 
                    <h1 class="page-header" >
                        KiotViet'Product Selling
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="home">Home</a></li>

                    </ol> 
                </div>

                <div> <c:if test="${mess!=null }">
                        <div class="alert alert-success" role="alert">
                            ${mess}
                        </div>
                    </c:if>
                </div>
                <div class="row">
                    <div class="col-md-12">

                        <div class="panel panel-default">
                            <div class="header-control">
                            </div>
                            <form action="pushproducttokiotviet" method="post" enctype="multipart/form-data">
                                <table class="table table-striped table-bordered table-hover">
                                    <tbody>
                                        <tr>
                                            <td><label for="name">Tên sản phẩm:</label></td>
                                            <td><input type="text" name="name" required></td>
                                        </tr>
                                        <tr>
                                            <td><label for="code">Mã sản phẩm:</label></td>
                                            <td><input type="text" name="code" required></td>
                                        </tr>
                                        <tr>
                                            <td><label for="barcode">Mã vạch:</label></td>
                                            <td><input type="text" name="barCode" required></td>
                                        </tr>
                                        <tr>
                                            <td><label for="onHand">Số lượng nhập:</label></td>
                                            <td><input type="text" name="onHand" required></td>
                                        </tr>
                                        <tr>
                                            <td><label for="price">Giá sản phẩm:</label></td>
                                            <td><input type="text" name="price" required></td>
                                        </tr>
                                        <tr>
                                            <td><label for="imageUrl">Ảnh sản phẩm:</label></td>
                                            <td><input type="text" name="imageUrl" required></td>
                                        </tr>

                                    </tbody>
                                </table>
                                <input type="submit" class="btn btn-primary" value="Submit" style="margin: 0px 0px 20px 20px;">
                            </form>



                        </div>
                    </div>
                    <!--End Advanced Tables -->
                </div>
            </div>

            <script src="assets/js/bootstrap.min.js"></script>
            <!-- Metis Menu Js -->
            <script src="assets/js/jquery.metisMenu.js"></script>
            <!-- DATA TABLE SCRIPTS -->
            <script src="assets/js/dataTables/jquery.dataTables.js"></script>
            <script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var sortSelect = document.getElementById("sortSelect");
                    sortSelect.addEventListener("change", function () {
                        var selectedOption = this.options[this.selectedIndex];
                        var url = selectedOption.getAttribute("data-href");
                        window.location.href = url;
                    });
                });


            </script>







    </body>
</ht        
