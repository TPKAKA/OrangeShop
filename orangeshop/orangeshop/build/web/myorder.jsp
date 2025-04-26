<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="convert" class="controllerAdmin.OrderManager"/>
<html lang="en">
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
        <title>My Order</title>
        <link rel="icon" type="image" href="assets/img/favicon.png">
        
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/userProfile.css" type="text/css"/>
        <link rel="stylesheet" href="assets/css/style.min.css" type="text/css"/>

        <style>
            .breadcrumb_content ul {
                line-height: 15px;
            }
            .breadcrumb_content ul li {
                display: inline-block;
                text-transform: capitalize;
                font-size: 14px;
                margin-right: 5px;
                color: #242424;
            }
            .breadcrumb_content ul li:last-child {
                margin-right: 0;
            }
            .breadcrumb_content ul li a {
                color: #999;
            }
            .breadcrumb_content ul li a:hover {
                color: #ff6a28;
            }
            .dashboard_tab_button {
                margin-bottom: 20px;
            }

            .dashboard_tab_button ul li {
                margin-bottom: 5px;
            }
            .dashboard_tab_button ul li a {
                font-size: 14px;
                color: #ffffff;
                font-weight: 500;
                text-transform: capitalize;
                background: #242424;
                border-radius: 3px;
            }
            .dashboard_tab_button ul li a:hover {
                background: #ff6a28;
                color: #ffffff;
            }
            .dashboard_tab_button ul li a.active {
                background: #ff6a28;
            }
            .table_desc {
                border: 1px solid #ddd;
                margin-bottom: 59px;
                margin-top: 2px;
            }
            .table_desc .cart_page table {
                width: 100%;
            }
            .table_desc .cart_page table thead tr th {
                border-bottom: 3px solid #ff6a28;
                border-right: 1px solid #ddd;
                color: #747474;
                font-size: 14px;
                font-weight: 600;
                text-transform: uppercase;
                padding: 10px;
                text-align: center;
            }
            .table_desc .cart_page table tbody tr td {
                border-bottom: 1px solid #ddd;
                border-right: 1px solid #ddd;
                text-align: center;
                padding: 10px;
            }
            .table_desc .cart_page table tbody tr td.product_thumb {
                min-width: 159px;
            }
            .table_desc .cart_page table tbody tr td.product_thumb img {
                width: inherit;
            }
            .table_desc .cart_page table tbody tr td.product_name {
                min-width: 260px;
            }
            .table_desc .cart_page table tbody tr td.product_name a {
                color: #747474;
                text-transform: capitalize;
                font-size: 14px;
                font-weight: 600;
            }
            .table_desc .cart_page table tbody tr td.product_name a:hover {
                color: #ff6a28;
            }
            .table_desc .cart_page table tbody tr td.product_remove {
                min-width: 125px;
            }
            .table_desc .cart_page table tbody tr td.product_remove a {
                font-size: 20px;
                color: #ff6a28;
            }
            .table_desc .cart_page table tbody tr td.product_remove a:hover {
                color: #ff6a28;
            }
            .table_desc .cart_page table tbody tr td.product-price {
                min-width: 159px;
                color: #ff6a28;
                font-size: 18px;
                font-weight: 600;
            }
            .table_desc .cart_page table tbody tr td.product_total {
                min-width: 159px;
            }
            .table_desc .cart_page table tbody tr td.product_quantity {
                min-width: 175px;
            }
            .table_desc .cart_page table tbody tr td.product_quantity input {
                width: 60px;
                height: 40px;
                padding: 0 5px 0 10px;
                background: none;
                border: 1px solid #ddd;
            }
        </style>
    </head>
    <body>
        <div class="container-xl px-4 mt-4">

            <nav class="nav nav-borders">
                <c:choose>
                    <c:when test="${admin != null}">
                        <!-- If admin is logged in -->
                        <a class="nav-link active ms-0" href="UserProfile?uid=${sessionScope.admin.getUserID()}" >Profile</a>
                        <a class="nav-link" href="ProfileSecurity?uid=${sessionScope.admin.getUserID()}" >Security</a>
                    </c:when>
                    <c:when test="${staff != null}">
                        <!-- If staff is logged in -->
                        <a class="nav-link active ms-0" href="UserProfile?uid=${sessionScope.staff.getUserID()}" >Profile</a>
                        <a class="nav-link" href="ProfileSecurity?uid=${sessionScope.staff.getUserID()}" >Security</a>
                    </c:when>
                    <c:when test="${customer != null}">
                        <!-- If customer is logged in -->
                        <a class="nav-link active ms-0" href="UserProfile?uid=${sessionScope.customer.getUserID()}" >Profile</a>
                        <a class="nav-link" href="ProfileSecurity?uid=${sessionScope.customer.getUserID()}" >Security</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link active ms-0" href="UserProfile?uid=${sessionScope.customer.getUserID()}" >Profile</a>
                        <a class="nav-link" href="ProfileSecurity?uid=${sessionScope.customer.getUserID()}" >Security</a>
                    </c:otherwise>
                </c:choose>

                <div class="back-to-home rounded d-none d-sm-block">
                    <a href="home" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i>
                        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home icons">
                        <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                        <polyline points="9 22 9 12 15 12 15 22"></polyline>
                        </svg>
                    </a>
                </div>
            </nav>
            <hr class="mt-0 mb-4">
            <form id="uploadForm" action="UserProfile" method="POST" class="mt-4" enctype="multipart/form-data">
                <div class="breadcrumb_content">
                    <ul>
                        <li><a href="home">home</a></li>
                        <li>/</li>
                        <li>my account</li>
                    </ul>
                </div>
                <input type="hidden" name="uid" value="${user.getUserID()}" />
                <div class="row">
                    <div class="col-xl-4">

                        <div class="card mb-4 mb-xl-0">
                            <div class="card-header">Profile Picture</div>

                            <div class="card-body text-center">

                                <img src="${user.getImage()}" class="img-account-profile rounded-circle mb-2" style="height: 150px; width: 150px;" alt="Avatar">

                                <input id="imageInput" name="Image" type="file" class="form-control" accept=".png, .jpg">
                                <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                            </div>
                            <div class="dashboard_tab_button">
                                <ul role="tablist" class="nav flex-column ">
                                    <li> <a href="#orders" data-toggle="tab" class="nav-link">My Order</a></li>
                                    <li><a href="./logout" class="nav-link">Log Out</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <div class="col-xl-8">

                        <div class="card mb-4">
                            <div class="card-header">My Order</div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="table_desc">
                                        <div class="cart_page table-responsive">
                                            <table>
                                                <thead>
                                                    <tr>
                                                        <th>Order ID</th>
                                                        <th>Date</th>
                                                        <th>Total</th>
                                                        <th>Product Name</th>
                                                        <th>Payment</th>
                                                        <th>Address</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>                                               
                                                <tbody>
                                                    <c:forEach items="${cusid}" var="cusid">
                                                        <tr>
                                                            <td>${cusid.orderid}</td>
                                                            <td>${cusid.date}</td>
                                                            <td>${convert.convertToVietnameseDong(cusid.totalamount)}</td>    
                                                            <td>${cusid.product.name}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${cusid.statuspayment == 1}">
                                                                        DONE
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        PROCESSING
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>${cusid.user.address}</td>
                                                            <td><a href="OrderUser?action=viewOrderDetails&orderID=${cusid.orderid}" class="view">view</a></td>
                                                        </tr>
                                                    </c:forEach>                         
                                                </tbody>
                                            </table>   
                                        </div> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
            </form>


        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>