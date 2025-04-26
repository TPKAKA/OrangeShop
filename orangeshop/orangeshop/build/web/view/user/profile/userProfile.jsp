<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
        <title>User Profile</title>
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
                                    <li> <a href="OrderUser?action=viewOrders&userId=${user.getUserID()}" data-toggle="tab" class="nav-link">My Order</a></li>
                                    <li><a href="./logout" class="nav-link">Log Out</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-8">

                        <div class="card mb-4">
                            <div class="card-header">Account Details</div>
                            <div class="card-body">

                                <div class="mb-3">
                                    <label class="small mb-1" for="inputUsername">FullName</label>
                                    <input class="form-control" name="FullName" id="inputUsername" type="text" placeholder="Enter your username" value="${user.getFullName()}">
                                </div>


                                <div class="mb-3">
                                    <label class="small mb-1" for="inputAddress">Address</label>
                                    <input class="form-control" name="Address" id="inputAddress" type="text" placeholder="Enter your location" value="${user.getAddress()}">
                                </div>

                                <div class="mb-3">
                                    <label class="small mb-1" for="inputEmailAddress">Email address</label>
                                    <input class="form-control" name="Email" id="inputEmailAddress" type="email" placeholder="Not Registered" value="${user.getEmail()}" readonly="">
                                </div>

                                <div class="row gx-3 mb-3">

                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputPhone">Phone number</label>
                                        <input class="form-control" name="Phone" id="inputPhone" type="text" placeholder="Not Registered" value="${user.getPhone()}" readonly="">
                                    </div>

                                </div>

                                <p style="color: green">${param.messSuccess}</p>
                                <div class="row">
                                    <div class="col-sm-12 d-flex justify-content-between">

                                        <button type="submit" id="submit" name="send" class="btn btn-primary" value="Save Change">Save Change</button>
                                        <a href="UserProfile?uid=${user.getUserID()}" class="btn btn-soft-primary ms-2">Cancel</a>
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
        <script>

        </script>
        <script>
            document.getElementById("uploadForm").addEventListener("submit", function (event) {
                var fileInput = document.getElementById("imageInput");
                if (fileInput.files.length > 0) {
                    var file = fileInput.files[0];
                    var fileSize = file.size; // in bytes
                    var maxSize = 5 * 1024 * 1024; // 5 MB in bytes
                    var acceptedFormats = [".png", ".jpg"];
                    var fileExtension = file.name.split(".").pop().toLowerCase();

                    if (fileSize > maxSize) {
                        alert("File size exceeds 5 MB. Please choose a smaller file.");
                        event.preventDefault(); // Prevent form submission
                    } else if (!acceptedFormats.includes("." + fileExtension)) {
                        alert("Invalid file format. Only PNG and JPG files are allowed.");
                        event.preventDefault(); // Prevent form submission
                    }
                }
            });
        </script>
    </body>
</html>