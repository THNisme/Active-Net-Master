<%-- 
    Document   : events
    Created on : Jul 21, 2025, 11:17:40 PM
    Author     : Admin
--%>

<%@page import="model.Event"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
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
        <link rel="stylesheet" href="./assets/css/dashboard.css">
        <link rel="stylesheet" href="./assets/css/base.css">

    </head>

    <body>

        <nav class="navbar bg-body-tertiary fixed-top">
            <div class="container-fluid">
                <div class="dashboard-nav-wrapper">
                    <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
                            data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                        <button class="btn primary-btn" type="submit"><i class="bi bi-search"></i></button>
                    </form>

                    <button type="button" class="btn primary-btn" data-bs-toggle="modal" data-bs-target="#addNewModal">
                        <i class="bi bi-plus-circle"></i>
                    </button>
                </div>


                <div class="dashboard-nav-wrapper">
                    <img src="./assets/img/logo/fac/LogoA-trans.png" alt="">
                    <a class="navbar-brand dashboard-nav-brand" href="dashboard">
                        Welcome, <%User u = (User) session.getAttribute("user");
                            out.println(u.getName());
                        %>
                    </a>
                    <a href="logout" class="btn primary-btn btn-nav-login active"><i class="bi bi-box-arrow-right"></i></a>
                </div>
                <div class="offcanvas offcanvas-start dashboard-offcanvas" tabindex="-1" id="offcanvasNavbar"
                     aria-labelledby="offcanvasNavbarLabel">
                    <div class="offcanvas-header">
                        <h5 class="offcanvas-title" id="offcanvasNavbarLabel">
                            <a class="navbar-brand" href="#">
                                <img src="./assets/img/logo/fac/Logo-back-text.png" alt="">
                            </a>
                        </h5>
                        <!-- <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button> -->
                    </div>
                    <div class="offcanvas-body">
                        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                            <!-- Đơn hàng -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Đơn hàng
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Quản lý đơn hàng</a></li>
                                    <li><a class="dropdown-item" href="#">Chi tiết đơn hàng</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="#">Thông tin thanh toán</a></li>
                                </ul>
                            </li>
                            <!-- Sản phẩm -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Sản phẩm
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Quản lý sản phẩm</a></li>
                                    <li><a class="dropdown-item" href="#">Danh mục sản phẩm</a></li>
                                    <li>
                                        <!-- <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="#">Something else here</a></li> -->
                                </ul>
                            </li>

                            <!-- Sự kiện -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Sự kiện
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Quản lý vé</a></li>
                                    <li><a class="dropdown-item" href="ticketcategory">Danh mục vé</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="#">Thông tin sự kiện</a></li>
                                </ul>
                            </li>

                            <!-- Người dùng -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    Người dùng
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="users">Quản lý người dùng</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" href="messages">Tất cả tin nhắn</a></li>
                                    <li><a class="dropdown-item" href="messages?view=inbox">Tin nhắn chưa đọc</a></li>
                                </ul>
                            </li>
                        </ul>


                    </div>
                </div>
            </div>
        </nav>

        <section class="dashboard-content container">
            <h1 class="section-heading">Thông tin <span class="section-heading-pink">Sự kiện</span></h1>

            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle">
                    <thead>
                        <tr class="table-dark text-center">
                            <th scope="col">EID</th>
                            <th scope="col">Tên</th>
                            <th scope="col">Mô tả</th>
                            <th scope="col">Ngày diễn ra</th>
                            <th scope="col">Địa điểm</th>                            
                            <th scope="col">Ngày tạo</th>
                        </tr>
                    </thead>
                    <tbody class="text-center">
                        <%
                            List<Event> eventList = (List<Event>) request.getAttribute("eventList");
                            if (eventList != null) {
                                for (Event e : eventList) {

                        %>
                        <tr>
                            <th scope="row" class="text-center"><%=e.getId()%></th>
                            <td><%=e.getName()%></td>
                            <td><%=e.getDescription()%></td>
                            <td><%=e.getDate()%></td>
                            <td><%=e.getLocation()%></td>
                            <td><%=e.getCreated_at()%></td>
                            <td>
                                <div class="table-tools-wrapper text-center">
                                    <a onclick="fnEdit('<%=e.getId()%>', '<%=e.getName()%>', '<%=e.getDescription()%>', '<%=e.getDate()%>', '<%=e.getLocation()%>', '<%=e.getCreated_at()%>')" class="btn primary-btn" data-bs-toggle="modal" data-bs-target="#editModal"><i class="bi bi-pencil-square"></i></a>
                                    <a onclick="fnDelete('<%=e.getId()%>')" class="btn primary-btn"><i class="bi bi-trash3" data-bs-toggle="modal" data-bs-target="#deleteModal"></i></a>
                                </div>
                            </td>
                        </tr>
                        <%}
                            } else {
                                out.println("<p>" + "Không có sự kiện nào" + "</p>");
                            }
                        %>

                    </tbody>
                </table>
            </div>


        </section>

        <!-- Modal Add New -->
        <div class="modal fade mt-5 " id="addNewModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
             aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog" style="min-width: 1000px">
                <form action="users?view=add" method="post">
                    <div class="modal-content">

                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel">Tạo sự kiện mới</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <div class="modal-body">

                            <div class="mb-3 row">
                                <label for="nameAdd" class="col-sm-2 col-form-label fw-medium">Tên:</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="nameAdd" name="nameAdd">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="descAdd" class="col-sm-2 col-form-label fw-medium">Mô tả:</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="descAdd" name="descAdd">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="dateAdd" class="col-sm-2 col-form-label fw-medium">Ngày diễn ra:</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="dateAdd" name="dateAdd">
                                </div>
                            </div>
                            <div class="mb-3 row">
                                <label for="locationAdd" class="col-sm-2 col-form-label fw-medium">Địa điểm:</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="locationAdd" name="locationAdd">
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal Edit -->
    <div class="modal fade mt-5 " id="editModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog" style="min-width: 1200px">

            <form action="events?view=edit" method="post">
                <div class="modal-content">

                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Chỉnh sửa</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body">
                        <div class="mb-3 row">
                            <label for="idEdit" class="col-sm-2 col-form-label fw-medium">ID:</label>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control-plaintext" id="idEdit" name="idEdit">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="eventNameEdit" class="col-sm-2 col-form-label fw-medium">Tên:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="eventNameEdit" name="eventNameEdit">
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="descEdit" class="col-sm-2 col-form-label fw-medium">Mô tả:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="descEdit" name="descEdit"> 
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="dateEdit" class="col-sm-2 col-form-label fw-medium">Ngày diễn ra:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="dateEdit" name="dateEdit"> 
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="locationEdit" class="col-sm-2 col-form-label fw-medium">Địa điểm:</label>
                            <div class="col-sm-10">
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="locationEdit" name="locationEdit"> 
                                </div>
                            </div>
                        </div>

                        <div class="mb-3 row">
                            <label for="createAtEdit" class="col-sm-2 col-form-label fw-medium">Ngày tạo:</label>
                            <div class="col-sm-10">
                                <input type="text" readonly class="form-control-plaintext" id="createAtEdit" name="createAtEdit">
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary">Thay đổi</button>
                    </div>

                </div>
            </form>

        </div>
    </div>


    <!-- Modal Delete -->
    <div class="modal fade mt-5 " id="deleteModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
         aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <form action="events?view=delete" method="post">
                <div class="modal-content">

                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="staticBackdropLabel">Xóa sự kiện</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>

                    <div class="modal-body">

                        <div class="mb-3 row">
                            <label for="dateEdit" class="col-sm-7 col-form-label">Bạn có chắc chắn xóa sự kiện có ID </label>
                            <div class="col-sm-5">
                                <input type="text" readonly class="form-control-plaintext fw-bold text-success" id="idDelete" name="idDelete"> 
                            </div>
                            <p class="text-center mt-5"><span class="fw-bold text-danger">Lưu ý:</span> Thao tác sẽ xóa tất cả các dữ liệu khác (vé, vật phẩm..) liên quan đến sự kiện này !</p>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-danger">Xóa</button>
                    </div>
                </div>
            </form>

        </div>
    </div>

    <script>
        function fnEdit(id, name, desc, date, location, createAt) {
            document.getElementById("idEdit").value = id;
            document.getElementById("eventNameEdit").value = name;
            document.getElementById("descEdit").value = desc;
            document.getElementById("dateEdit").value = date;
            document.getElementById("locationEdit").value = location;
            document.getElementById("createAtEdit").value = createAt;
        }

        function fnDelete(id) {
            document.getElementById("idDelete").value = id;
        }
    </script>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q"
    crossorigin="anonymous"></script>
</body>

</html>
