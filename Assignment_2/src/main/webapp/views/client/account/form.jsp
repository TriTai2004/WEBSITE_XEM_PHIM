<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<section class="account">
       <div class="container">
            <div class="row">
                <div class="col-2">
                    <ul>
                        <li class=""><a class="${ uri.contains('tai-khoan') ? 'active': '' }" href="/Assignment_2/tai-khoan">Thông tin tài khoản</a></li>
                        <li class=""><a class="${ uri.contains('doi-mat-khau') ? 'active': '' }" href="/Assignment_2/doi-mat-khau">Đổi mật khẩu</a></li>
                        <li><a href="/Assignment_2/dang-nhap">Đăng xuất</a></li>
                    </ul>
                </div>
                <div class="col">
                    <jsp:include page="${ pageAccount }"></jsp:include>
                </div>
            </div>
       </div>
    </section>