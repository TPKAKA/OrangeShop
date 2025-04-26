

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
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
        <title>Login</title>
        <link rel="icon" type="image" href="assets/img/favicon.png">
        <!-- Bootstrap -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons -->
        <link href="assets/css/materialdesignicons.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/css/remixicon.css" rel="stylesheet" type="text/css" />
        <link href="https://unicons.iconscout.com/release/v3.0.6/css/line.css"  rel="stylesheet">
        <!-- Css -->
        <link href="assets/css/style.min.css" rel="stylesheet" type="text/css" id="theme-opt" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
        <style>
            #number,
            #verificationcode {

            }

            #recaptcha-container {
                margin-bottom: 10px;
                transform:scale(1);
                transform-origin:0 0;

            }

            #send,
            #verify {
                width: 100%;
                height: 48px;
                outline: none;
            }

            .error-message {
                display: none;
                color: red;
                font-size: 14px;
                margin-top: auto;
                margin-bottom: 10px;
                text-align: center;
            }

            .toast-top-right {
                top: 90px !important;
                right: 30px;
            }
            .toast {
                font-size: 15px; /* Điều chỉnh font chữ theo ý muốn */
                display: flex; /* Sử dụng Flexbox */
                flex-direction: column; /* Đặt hướng chính là dọc */
                justify-content: center; /* Căn giữa theo chiều dọc */
                align-items: center; /* Căn giữa theo chiều ngang */
            }
        </style>
    <body>

        <!-- Loader -->
        <div id="preloader">
            <div id="status">
                <div class="spinner">
                    <div class="double-bounce1"></div>
                    <div class="double-bounce2"></div>
                </div>
            </div>
        </div>
        <!-- Loader -->

        <div class="back-to-home rounded d-none d-sm-block">
            <a href="index.jsp" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>

        <!-- Hero Start -->
        <section class="bg-home d-flex bg-light align-items-center" style="background: url('assets/img/class/class-2.jpg') center;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">

                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <img src="assets/img/logo.png" height="50" class="mx-auto d-block" alt="">
                                <h4 class="text-center">Đăng nhập vào OrangeFood</h4>  
                                <form action="login-phone" method="post" class="login-form mt-4">
                                    <div class="row">

                                        <div class="col-lg-12">
                                            <div class="d-flex justify-content-between">
                                                <div class="mb-3">
                                                    <label class="form-label mb-0">Số điện thoại <span class="text-danger">*</span></label> 
                                                </div>
                                                <a href="login" class="text-dark h6 mb-0">Đăng nhập với Email</a>
                                            </div>
                                        </div>
                                        <div class="col-lg-8" style="padding-right: 0px">
                                            <div class="mb-3">
                                                <input style="height:40.1px" id="number" type="text" class="form-control" placeholder="Số điện thoại" 
                                                       name="phone" ${success == 'Success' ? 'readonly':''} 
                                                       autofocus="" value="${phone}">
                                            </div>
                                        </div>

                                        <div class="col-lg-4 mb-0" style="padding-left: 0px">
                                            <div class="d-grid">
                                                <button type="submit" class="btn btn-warning">Kiểm tra</button>
                                            </div>
                                        </div>

                                        <div class="col-lg-8" style="padding-right: 0px">
                                            <div class="mb-3" style="display: flex">
                                                <input style="height:40.1px" id="verificationcode" type="text" 
                                                       class="form-control" placeholder="Nhập mã xác nhận">
                                            </div>
                                        </div>

                                        <div class="col-lg-4 mb-0" style="padding-left: 0px">
                                            <div class="d-grid">
                                                <input type="button" class="btn btn-danger" value="Gửi mã" ${success != 'Success' ? 'disabled':''} onClick="phoneAuth()">
                                            </div>
                                        </div>


                                        <div style="display: none" id="recaptcha-container"></div>

                                        <c:if test="${fail != null || failempty != null || failformat != null}">
                                            <div class="col-lg-12">
                                                <div class="mb-3 text-center">
                                                    <span style="color:red; ">${fail}</span>
                                                    <span style="color:red; ">${failempty}</span>
                                                    <span style="color:red; ">${failformat}</span>
                                                </div>
                                            </div>
                                        </c:if>

                                        <div class="col-lg-12 error-message">Mã xác nhận không hợp lệ. Vui lòng thử lại</div>

                                        <div class="col-lg-12 mb-0">
                                            <div class="d-grid">
                                                <input type="button" class="btn btn-primary" ${success != 'Success' ? 'disabled':''} 
                                                       value="Đăng Nhập" onClick="codeverify()">
                                            </div>
                                        </div>      

                                        <div class="col-lg-12 mt-3 text-center">
                                            <h6 class="text-muted">Hoặc</h6>
                                        </div><!--end col-->

                                        

                                        <div class="col-6 mt-3">
                                            <div class="d-grid">
                                                <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&&redirect_uri=http://localhost:9999/TemplateAdminT1/Homemanagement/loginwithgoogle&response_type=code&client_id=1023249071341-cuhk6ioa4frvop3r14jdmr8hvkckqit9.apps.googleusercontent.com&approval_prompt=force" 
                                                   class="btn btn-soft-primary"><i class="uil uil-google"></i> Google</a>
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-12 text-center">
                                            <p class="mb-0 mt-3"><small class="text-dark me-2">Bạn chưa có tài khoản ?</small> <a href="register-phone" class="text-dark fw-bold">Đăng Ký</a></p>
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div><!---->
                        <%-- Toast Success Phone --%>
                        <script>
                            $(document).ready(function () {
                                var message = '${success}'; // Lấy giá trị từ biến message
                                if (message !== '') {
                                    // Hiển thị toast
                                    toastr.success('Vui lòng nhấn gửi mã - nhập mã xác thực để đăng nhập', 'Số điện thoại của bạn hợp lệ!', {
                                        timeOut: 4000,
                                        showMethod: 'slideDown', // Hiệu ứng trượt từ bên phải sang khi hiển thị
                                        hideMethod: 'slideUp'});
                                }
                            });
                        </script>
                        <%-- Toast Fail Phone Empty--%>
                        <script>
                            $(document).ready(function () {
                                var message = '${failempty}'; // Lấy giá trị từ biến message
                                if (message !== '') {
                                    // Hiển thị toast
                                    toastr.warning(message, '', {
                                        timeOut: 4000,
                                        showMethod: 'slideDown', // Hiệu ứng trượt từ bên phải sang khi hiển thị
                                        hideMethod: 'slideUp'});
                                }
                            });
                        </script>
                        <%-- Toast Fail Phone Format--%>
                        <script>
                            $(document).ready(function () {
                                var message = '${failformat}'; // Lấy giá trị từ biến message
                                if (message !== '') {
                                    // Hiển thị toast
                                    toastr.warning(message, '', {
                                        timeOut: 4000,
                                        showMethod: 'slideDown', // Hiệu ứng trượt từ bên phải sang khi hiển thị
                                        hideMethod: 'slideUp'});
                                }
                            });
                        </script>
                        <%-- Toast Fail Phone Existed--%>
                        <script>
                            $(document).ready(function () {
                                var message = '${fail}'; // Lấy giá trị từ biến message
                                if (message !== '') {
                                    // Hiển thị toast
                                    toastr.warning(message, '', {
                                        timeOut: 4000,
                                        showMethod: 'slideDown', // Hiệu ứng trượt từ bên phải sang khi hiển thị
                                        hideMethod: 'slideUp'});
                                }
                            });
                        </script>
                    </div> <!--end col-->
                </div><!--end row-->
            </div> <!--end container-->
        </section><!--end section-->
        <!-- Hero End -->
        <!-- Import các tệp script của Firebase SDK -->
        <script src="https://www.gstatic.com/firebasejs/9.12.1/firebase-app-compat.js"></script>
        <script src="https://www.gstatic.com/firebasejs/9.12.1/firebase-auth-compat.js"></script>
        <script>
                            // Cấu hình thông tin của Firebase
                            // For Firebase JS SDK v7.20.0 and later, measurementId is optional
                            const firebaseConfig = {
                                apiKey: "AIzaSyDqYDV0a2ms4vzpNH2gKpEzzKKKYie2W5k",
                            authDomain: "sw391-a1848.firebaseapp.com",
                            projectId: "sw391-a1848",
                            storageBucket: "sw391-a1848.appspot.com",
                            messagingSenderId: "1023249071341",
                            appId: "1:1023249071341:web:4c45414815a2a0b4c8e0e4",
                            measurementId: "G-1F028PM2VM"
                            };
                            firebase.initializeApp(firebaseConfig);
                            // Gọi hàm render để hiển thị Recaptcha
                            render();
                            // Hàm render để hiển thị Recaptcha
                            function render() {
                                window.recaptchaVerifier = new firebase.auth.RecaptchaVerifier('recaptcha-container');
                                recaptchaVerifier.render();
                            }

                            // Hàm gửi tin nhắn xác thực (Click gửi mã)
                            function phoneAuth() {
                                // Lấy số điện thoại từ trường nhập liệu
                                var number = document.getElementById('number').value;
                                number = convertPhone(number);
                                // Hiển thị Recaptcha container
                                document.getElementById('recaptcha-container').style.display = 'block';
                                // Gửi tin nhắn xác thực đến số điện thoại
                                firebase.auth().signInWithPhoneNumber(number, window.recaptchaVerifier).then(function (confirmationResult) {
                                    // Lưu kết quả xác thực vào biến confirmationResult
                                    window.confirmationResult = confirmationResult;
                                    coderesult = confirmationResult;

                                }).catch(function (error) {
                                    alert(error.message);
                                });
                            }
                            // Hàm chuyển đổi số điện thoại
                            function convertPhone(phone) {
                                if (!phone.startsWith("+84")) {
                                    phone = phone.replace('0', "+84");
                                    console.log(phone + "new ");
                                }
                                return phone;
                            }
                            // Hàm xác thực mã (Click đăng nhâp)
                            function codeverify() {
                                // Lấy mã xác thực từ trường nhập liệu
                                var code = document.getElementById('verificationcode').value;
                                // Xác thực mã
                                coderesult.confirm(code).then(function () {
                                    // Chuyển hướng đến trang login thành công và truyền thông tin số điện thoại
                                    window.location.href = "succesful-phone?phone=${phone}";
                                    // Ẩn thông báo lỗi
                                    document.getElementsByClassName('error-message')[0].style.display = 'none';
                                }).catch(function () {
                                    // Hiển thị thông báo lỗi
                                    document.getElementsByClassName('error-message')[0].style.display = 'block';
                                });
                            }
        </script>
        <!-- javascript -->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <!-- Icons -->
        <script src="assets/js/feather.min.js"></script>
        <!-- Main Js -->
        <script src="assets/js/app.js"></script>

    </body>
</html>
