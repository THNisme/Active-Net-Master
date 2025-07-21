<%-- 
    Document   : home
    Created on : Jul 7, 2025, 9:09:47 PM
    Author     : BACH YEN
--%>

<%@page import="model.IO"%>
<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Ticket"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Trang chủ</title>

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
        <link rel="stylesheet" href="./assets/css/base.css">
        <link rel="stylesheet" href="./assets/css/home.css">

    </head>

    <body>
        <nav class="navbar nav-bg">
            <div class="container">
                <div class="row nav-wrapper">
                    <div class="col-md-3">
                        <a class="nav-logo-link" href="#">
                            <img src="./assets/img/logo/fac/Logo-Nav.png" alt="F-Active Logo" class="nav-brand-img">
                        </a>
                    </div>
                    <div class="col-md-6">
                        <ul class="nav-list">
                            <a href="home" class="nav-item-link active">
                                <li class="nav-list-item">Trang chủ</li>
                            </a>
                            <a href="about.jsp" class="nav-item-link">
                                <li class="nav-list-item">Giới thiệu</li>
                            </a>
                            <a href="product" class="nav-item-link">
                                <li class="nav-list-item">Cửa hàng</li>
                            </a>
                            <a href="contact.jsp" class="nav-item-link">
                                <li class="nav-list-item">Liên hệ</li>
                            </a>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <%
                            User u = (User) session.getAttribute("user");
                            if (u == null) {

                        %>
                        <a href="login" class="btn primary-btn btn-nav-login active">Đăng nhập</a>
                        <%                        } else {
                        %>
                        <a href="logout" class="btn primary-btn btn-nav-login active"><i class="bi bi-box-arrow-right"></i></a>
                        <a href="profile" class="btn primary-btn btn-nav-login active"><i class="bi bi-person-circle"></i></a>
                            <%                        }
                            %>
                    </div>
                </div>
        </nav>




        <section class="welcome"><!-- Welcome -->

            <div class="container">
                <div class="row welcome-container">
                    <div class="col-8">
                        <div class="wel-left">
                            <h1 class="section-heading wel-left">
                                <span class="section-heading-pink">Active</span>
                                <span>Net</span>
                            </h1>

                            <h1 class="footer-heading">Web Assigment Project.</h1>

                            <p class="paragraph">Chào mừng mọi người đến với trang bán vé và vật phẩm sự kiện chính chủ của
                                Câu lạc bộ sự kiện F-Active.</p>
                            <a href="login" class="btn primary-btn">Join with us</a>

                        </div>

                    </div>

                    <div class="col-4 wel-right">

                        <img src="./assets/img/icons/be_5.png" class="" alt="...">

                    </div>
                </div>

            </div>
        </section>

        <section class="ticket"><!-- Ticket -->
            <img src="./assets/img/logo/fac/Logo-back-text.png" class="center-logo" alt="...">
            <p class="p-center">Săn ngay vé đỉnh tại Active-Net, nơi bán vé uy tín </br>chính chủ từ F-Active.</p>


            <div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel" data-bs-interval="2500">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="container text-center">
                            <div class="row">

                                <%
                                    ArrayList<Ticket> listTicket = (ArrayList<Ticket>) request.getAttribute("listTicket");
                                    int i = 0;
                                    int n = listTicket.size();
                                    Ticket t = new Ticket();
                                    while (i < n) {
                                        t = listTicket.get(i);
                                        if (i % 4 != 0 || i == 0) {

                                %>
                                <div class="col-3">
                                    <div class="ticket-wrapper">
                                        <img src="<%=t.getImageUrl()%>" class="li-img" alt="...">
                                        <p class="ticket-name">Vé sự kiện “<%=t.getName()%>”</p>
                                        <p class="ticket-time"><%=t.getEvent().getDate()%></p>
                                        <p class="price"><%=IO.formatCurrency(t.getPrice() + "")%> VND</p>
                                        <a href="#" class="btn primary-btn buy"><i class="bi bi-bag"></i></a>
                                    </div>
                                </div>
                                <% } else {%>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="container text-center">
                            <div class="row">
                                <div class="col-3">
                                    <div class="ticket-wrapper">
                                        <img src="<%=t.getImageUrl()%>" class="li-img" alt="...">
                                        <p class="ticket-name">Vé sự kiện “<%=t.getName()%>”</p>
                                        <p class="ticket-time"><%=t.getEvent().getDate()%></p>
                                        <p class="price"><%=IO.formatCurrency(t.getPrice() + "")%> VND</p>
                                        <a href="#" class="btn primary-btn buy"><i class="bi bi-bag"></i></a>
                                    </div>
                                </div>
                                <% }
                                        i++;
                                    }%>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
            <div class="more-wrapper">
                <a href="shop.jsp" class="btn primary-btn more">Xem thêm</a>
            </div>

        </section>

        <section class="why"><!-- Why -->

            <div class="container">
                <h1 class="section-heading wel-left">
                    <span>Vì sao lại có</span>
                    <span class="section-heading-pink">Active-Net</span>
                </h1>

                <p class="paragraph">Với Active Net, nơi giúp bạn có thể đăng ký tham, mua</br>
                    các vật phẩm của sự kiện mọi lúc mọi nơi.</p>

                <div class="why-wrapper">
                    <div class="row">
                        <div class="col-7">

                            <div class="why-content">
                                <img src="./assets/img/icons/den.png" class="icon-img" alt="...">
                                <div>
                                    <p class="why-p p-header">Trực Quan Với Đa Dạng Sự Kiện</p>
                                    <p class="why-p">
                                        Theo dõi suất vé, vật phẩm liên quan, thông tin giới thiệu sự kiện từ CLB FAC tất cả
                                        mọi thứ
                                        đều
                                        có
                                        thể
                                        tìm với Active Net.</p>
                                </div>

                            </div>

                            <div class="why-content">
                                <img src="./assets/img/icons/tim.png" class="icon-img" alt="...">
                                <div>
                                    <p class="why-p p-header">Sản Phẩm Chính Chủ</p>
                                    <p class="why-p">
                                        Active Net được làm ra mục đích giúp mọi người có thể tham gia mọi hoạt động từ câu
                                        lạc bộ
                                        một
                                        cách
                                        an toàn và thuận tiện.</p>
                                </div>
                            </div>

                            <div class="why-content">
                                <img src="./assets/img/icons/tainghe.png" class="icon-img" alt="...">
                                <div>
                                    <p class="why-p p-header">Tư Vấn Hỗ Trợ</p>
                                    <p class="why-p">
                                        Mọi thắc mắc đều được hỗ trợ qua kênh liên hệ với bộ phận chăm sóc khách hàng từ
                                        F-Active.
                                    </p>
                                </div>
                            </div>

                        </div>

                        <div class="col-5 why-right">
                            <div class="row">
                                <img src="./assets/img/logo/fac/logo_hoa.png" class="" alt="...">
                            </div>
                            <div class="row">
                                <a href="about.jsp" class="btn primary-btn">F-Active !</a>
                            </div>

                        </div>

                    </div>
                </div>
            </div>



        </section>

        <section class="ticket"><!-- Product -->
            <img src="./assets/img/logo/fac/Logo-back-text.png" class="center-logo" alt="...">
            <p class="p-center">Đặt mua các vật phẩm sự kiện, ủng hộ đơn vị tổ chức</br> cùng lưu giữ trải nghiệm !</p>

            <div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel" data-bs-interval="2500">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="container text-center">
                            <div class="row">

                                <%
                                    ArrayList<Product> listProduct = (ArrayList<Product>) request.getAttribute("listProduct");
                                    i = 0;
                                    n = listProduct.size();
                                    Product p = new Product();
                                    while (i < n) {
                                        p = listProduct.get(i);
                                        if (i % 4 != 0 || i == 0) {

                                %>

                                <div class="col-3">
                                    <div class="ticket-wrapper">
                                        <img src="<%=p.getImageUrl()%>" class="li-img" alt="...">
                                        <p class="ticket-name pro"><%=p.getName()%></p>
                                        <p class="price"><%=IO.formatCurrency(p.getPrice()+ "")%> VND</p>
                                        <a href="#" class="btn primary-btn buy"><i class="bi bi-bag"></i></a>
                                    </div>
                                </div>
                                <% } else {%>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="container text-center">
                            <div class="row">
                                <div class="ticket-wrapper">
                                    <img src="<%=p.getImageUrl()%>" class="li-img" alt="...">
                                    <p class="ticket-name pro"><%=p.getName()%></p>
                                    <p class="price"><%=IO.formatCurrency(p.getPrice()+ "")%> VND</p>
                                    <a href="#" class="btn primary-btn buy"><i class="bi bi-bag"></i></a>
                                </div>

                                <% }
                                        i++;
                                    }%>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
            <div class="more-wrapper">
                <a href="shop.jsp" class="btn primary-btn more">Xem thêm</a>
            </div>

        </section>

        <section class="collab"><!-- Collab Partner -->

            <h1 class="section-heading">
                <span>Các đơn vị</span>
                <span class="section-heading-pink">đồng hành</span>
            </h1>

            <p class="p-center">Chân thành cảm ơn các đơn vị đã chung tay cùng </br>
                F-Active qua các dự án tâm huyết</p>

            <div class="container">
                <div class="collab-wrapper">
                    <div class="row">
                        <div class="col-3">
                            <div class="collab-part">
                                <img src="./assets/img/logo/others/MC-Logo.jpg" class="" alt="...">
                                <p class="ticket-name">Multimedia comunication</p>
                                <p class="collab-para">Ngành truyền thông đa phương tiện Đại học FPT cơ sở Cần Thơ</p>
                            </div>

                        </div>

                        <div class="col-3">
                            <div class="collab-part bg2">
                                <img src="./assets/img/logo/fac/LogoA-white.png" class="" alt="...">
                                <p class="ticket-name">Event club F-Active</p>
                                <p class="collab-para">Câu lạc bộ sự kiện F-Active tại Đại học FPT cơ sở Cần Thơ</p>
                            </div>

                        </div>

                        <div class="col-3">
                            <div class="collab-part">
                                <img src="./assets/img/logo/others/LOGO-SRO-2023.png" class="" alt="...">
                                <p class="ticket-name">Student Relations Office</p>
                                <p class="collab-para">Phòng Công tác sinh viên Đại học FPT cơ sở Cần Thơ</p>
                            </div>

                        </div>

                        <div class="col-3 ">
                            <div class="collab-part">
                                <img src="./assets/img/logo/hifive/HF-logo.jpg" class="" alt="...">
                                <p class="ticket-name">HiFive Team</p>
                                <p class="collab-para">Đơn vị phát triển dự án Active-Net lớp SE1904 môn PRJ301</p>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="more-wrapper">
                    <a href="contact.jsp" class="btn primary-btn call">Liên hệ hợp tác cùng F-Active &lt;3 </a>
                </div>

            </div>

        </section>












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
                                <a href="" class="footer-item-link">
                                    <li class="footer-link-list-item">Trang chủ</li>
                                </a>
                                <a href="" class="footer-item-link">
                                    <li class="footer-link-list-item">Giới thiệu</li>
                                </a>
                                <a href="" class="footer-item-link">
                                    <li class="footer-link-list-item">Cửa hàng</li>
                                </a>
                                <a href="" class="footer-item-link">
                                    <li class="footer-link-list-item">Liên hệ</li>
                                </a>
                                <a href="" class="footer-item-link">
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