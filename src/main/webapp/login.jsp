<%-- 
    Document   : login
    Created on : Jul 7, 2025, 9:10:01 PM
    Author     : BACH YEN
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Đăng nhập</title>

        <!-- LINK BOOTSTRAP -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
        <!-- LINK FONT -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
              rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Playpen+Sans:wght@100..800&display=swap" rel="stylesheet">

        <!-- LINK CSS -->
        <link rel="stylesheet" href="./assets/css/login.css">
        <link rel="stylesheet" href="./assets/css/base.css">

    </head>

    <body>

        <div class="login-wrapper">
            <div class="container">
                <div class="login-content">
                    <div class="row">
                        <div class="col-5">
                            <div class="logo">
                                <img src="./assets/img/logo/fac/LogoA-trans.png " class="" alt="" />
                            </div>
                        </div>
                        <div class="col-7">
                            <h3 class="section-heading login-header">
                                <span>Đăng</span>
                                <span class="section-heading-pink">nhập</span>
                            </h3>

                            <form class="form-wrapper" action="login" method="post">
                                <div class="position-relative mb-5">
                                    <i
                                        class="bi bi-envelope position-absolute top-50 start-0 translate-middle-y ms-3 text-muted"></i>
                                    <input type="email" class="form-control ps-5 rounded-pill" placeholder="Email" name="user"
                                           id="" required>
                                </div>

                                <div class="position-relative mb-5">
                                    <i
                                        class="bi bi-lock position-absolute top-50 start-0 translate-middle-y ms-3 text-muted"></i>
                                    <input type="password" class="form-control ps-5 rounded-pill" placeholder="Mật khẩu"
                                           name="pass" id="" required>
                                </div>

                                <%
                                    List<String> errorList = (List<String>) session.getAttribute("errorList");
                                    if (errorList != null && !errorList.isEmpty()) {
                                        for (String err : errorList) {
                                           %>
                                           <p><%=err%></p>
                                           <%
                                        }
                                    }
            session.setAttribute("errorList", null);

                                %>

                                <div class="submit">
                                    <button type="submit" class="btn primary-btn">Đăng nhập</button>
                                </div>
                            </form>

                        </div>
                    </div>
                    <!-- Sign-up link -->
                    <div class="signup-wrapper">
                        <p>
                            <a href="register" class="signup-link">Đăng kí tài khoản</a>
                        </p>
                    </div>
                </div>

            </div>
        </div>



        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <img src="./assets/img/logo/fac/LogoA-trans.png" alt="F-Active Logo" class="footer-logo-img">
                        <p class="footer-text">
                            Active Net một dự án cộng tác từ Câu lạc bộ sự kiện F-Active cùng HiFive Team
                        </p>
                        <ul class="footer-social-link-list">
                            <a href="" class="footer-social-link-item">
                                <li class="footer-social-list-item">
                                    <i class="bi bi-facebook"></i>
                                </li>
                            </a>
                            <a href="" class="footer-social-link-item">
                                <li class="footer-social-list-item">
                                    <i class="bi bi-youtube"></i>
                                </li>
                            </a>
                            <a href="" class="footer-social-link-item">
                                <li class="footer-social-list-item">
                                    <i class="bi bi-tiktok"></i>
                                </li>
                            </a>
                        </ul>
                    </div>
                    <div class="col-md-4 footer-col">
                        <div class="footer-content">
                            <h4 class="footer-heading">
                                Active Net
                            </h4>
                            <ul class="footer-link-list">
                                <a href="home" class="footer-item-link">
                                    <li class="footer-link-list-item">Trang chủ</li>
                                </a>
                                <a href="about" class="footer-item-link">
                                    <li class="footer-link-list-item">Giới thiệu</li>
                                </a>
                                <a href="product" class="footer-item-link">
                                    <li class="footer-link-list-item">Cửa hàng</li>
                                </a>
                                <a href="contact" class="footer-item-link">
                                    <li class="footer-link-list-item">Liên hệ</li>
                                </a>
                                <a href="login" class="footer-item-link">
                                    <li class="footer-link-list-item">Đăng nhập</li>
                                </a>
                            </ul>
                        </div>

                    </div>
                    <div class="col-md-4 footer-col">
                        <div class="footer-content">
                            <h4 class="footer-heading">
                                Get In Touch
                            </h4>
                            <ul class="footer-link-list">
                                <a href="mailto: clbfactive1420@gmail.com" class="footer-item-link">
                                    <li class="footer-link-list-item">clbfactive1420@gmail.com</li>
                                </a>
                                <a href="tel:0704906670" class="footer-item-link">
                                    <li class="footer-link-list-item">070 490 6670</li>
                                </a>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <p class="footer-copyright">Copyright © 2025 Hifive Team | All Rights Deserved @hifiveteam</p>
                </div>
            </div>
        </footer>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
        crossorigin="anonymous"></script>


    </body>

</html>
