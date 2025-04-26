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
        <title>Profile Security</title>
        <link rel="icon" type="image" href="assets/img/favicon.png">
        
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
       
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="assets/css/userSecurity.css" type="text/css"/>
        <link rel="stylesheet" href="assets/css/style.min.css" type="text/css"/>


    </head>
    <body>
        <div class="container-xl px-4 mt-4">

            <nav class="nav nav-borders">
                <c:choose>
                    <c:when test="${not empty sessionScope.admin}">
                        <!-- If admin is logged in -->
                        <a class="nav-link active ms-0" href="UserProfile?uid=${sessionScope.admin.getUserID()}" >Profile</a>
                        <a class="nav-link" href="ProfileSecurity?uid=${sessionScope.admin.getUserID()}" >Security</a>
                    </c:when>
                    <c:when test="${not empty sessionScope.staff}">
                        <!-- If staff is logged in -->
                        <a class="nav-link active ms-0" href="UserProfile?uid=${sessionScope.staff.getUserID()}" >Profile</a>
                        <a class="nav-link" href="ProfileSecurity?uid=${sessionScope.staff.getUserID()}" >Security</a>
                    </c:when>
                    <c:when test="${not empty sessionScope.customer}">
                        <!-- If customer is logged in -->
                        <a class="nav-link active ms-0" href="UserProfile?uid=${sessionScope.customer.getUserID()}" >Profile</a>
                        <a class="nav-link" href="ProfileSecurity?uid=${sessionScope.customer.getUserID()}" >Security</a>
                    </c:when>
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
            <div class="row">
                <div class="col-lg-8">

                    <div class="card mb-4">
                       
                        
                        <div class="card-header">Change Password</div>
                        <div class="card-body">
                            <form action="ProfileSecurity" method="POST" class="mt-4">
                                     <input hidden name="email" value="${user.getEmail()}" />
                                     <input hidden name="uid" value="${user.getUserID()}" />
                                <div class="mb-3">
                                    <label class="small mb-1" for="currentPassword">Current Password</label>
                                    <input class="form-control" id="currentPassword" type="password" placeholder="Enter current password" value="${user.getPassword()}" readonly="">
                                </div>

                                <div class="mb-3">
                                    <label class="small mb-1" for="newPassword">New Password</label>
                                    <input class="form-control" id="newPassword" name="pass" type="password" placeholder="Enter new password" value="">
                                </div>

                                <div class="mb-3">
                                    <label class="small mb-1" for="confirmPassword">Confirm Password</label>
                                    <input class="form-control" id="confirmPassword" name="re_pass" type="password" placeholder="Confirm new password" value="">
                                </div>
                                <p style="color: red">${param.mess}</p>
                                <p style="color: green">${param.messSuccess}</p>
                                <input type="submit" id="submitdel" name="send" class="btn btn-soft-primary ms-2" value="Save">
                                <a href="ProfileSecurity?uid=${user.getUserID()}" class="btn btn-soft-primary ms-2">Cancel</a>
<!--                                <button class="btn btn-primary" type="button">Save</button>-->
                            </form>
                        </div>
                    </div>

                   
                </div>
                <div class="col-lg-4">
                    <div class="card mb-4">
                        <form action="DeleteUser" method="POST" class="mt-4">
                        <input hidden name="uid" value="${user.getUserID()}" />
                        <div class="card-header">Delete Account</div>
                        <div class="card-body">
                            <p>Deleting your account is a permanent action and cannot be undone. If you are sure you want to delete your account, select the button below.</p>
                            <input type="submit" id="submit" name="send" class="btn btn-danger-soft text-danger" value="I understand, delete my account">
                            
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <script >

        </script>
    </body>
</html>