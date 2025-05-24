<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <div class="breadcrumb-option spad set-bg" data-setbg="./assets/img/breadcrumb-bg.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Video </h2>
                        <div class="breadcrumb__links">
                            <a href="/Assignment_2/trang-chu">Trang chủ</a>
                            <span>Video</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <section class="portfolio spad">
        <div class="search container">
            <form action="${ url }" method="get" class="position-relative">
                <input value = "${ search }" type="search" id="micro" class="w-100" style="padding: 10px  50px " name="search" placeholder="Tìm kiếm..." class="form-control">
               
                <button id="search-micro" style="left: 25px !important"><i class="bi bi-search"></i></button>
                 <button onclick="start()" type="button" style="left: 5px" type="button"><i class="bi bi-mic"></i></button>
            </form>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <ul class="portfolio__filter">
                        <li class="active">
                            <a class="text-white" href="/Assignment_2/video">Tất cả</a>
                        </li>
                        <li>
                            <a class="text-white" href="/Assignment_2/yeu-thich">Yêu thích</a>
                        </li>	
                    </ul>
                </div>
            </div>
			<div class="row portfolio__gallery">
				<c:forEach items="${ list }" var="video">
					<div class="col-lg-4 col-md-6 col-sm-6 mb-3">
						<div class="card bg-transparent">
							<a href="/Assignment_2/video-chi-tiet?id=${ video.id }"> <img
								src="/Assignment_2/assets${ video.poster }" class="card-img-top" alt="...">
							</a>
							<div class="card-body ">
								<div class="w-100 overflow-hidden">
									<a href="/Assignment_2/video-chi-tiet?id=${ video.id }"
									class="card-title text-truncate text-white">${ video.title }</a>
								</div>
								<ul class="like-share d-flex justify-content-between pt-2">
									<li class="list-unstyled"><a id="like-${ video.id }" data-user-id = "${ sessionScope.user.id == null ? 0: sessionScope.user.id }" onClick="like(${ sessionScope.user.id == null ? 0: sessionScope.user.id },${video.id})" style="cursor: pointer;"
										class="text-white like"><i class="bi bi-heart-fill"></i></a></li>
									<li class="list-unstyled"><a href="" class="text-white"><i
											class="bi bi-share-fill"></i></a></li>
								</ul>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
	</div>
            <div class="container">
            	<div class="row">
                <div class="col-lg-12">
                    <div class="pagination__option">
                    	<c:if test="${ pagination != 0 }">
                        	<a href="?page=${ numberPage == 1 ? 1 : numberPage - 1 }&search=${search}" class="arrow__pagination left__arrow"><span class="arrow_left"></span> Prev</a>
                        </c:if>
                        <c:forEach var="page" begin="1" end="${ pagination }" >
                        	<a href="?page=${ page }&search=${search}" class="number__pagination">${ page }</a>
                        </c:forEach>
                        <c:if test="${ pagination != 0 }">
                        	<a href="?page=${ numberPage + 1 > pagination ? pagination : numberPage + 1 }&search=${search}" class="arrow__pagination right__arrow">Next <span class="arrow_right"></span></a>
                    	</c:if>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </section>