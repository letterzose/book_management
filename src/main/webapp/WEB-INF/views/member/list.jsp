<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <link rel="stylesheet" href="/assets/css/book_list.css">
    <script src="/assets/js/member.js"></script>
</head>
<body>
    <main>
        <h1><i class="fas fa-address-book"></i> 회원 관리</h1>
        <button id="add_book"><i class="fas fa-plus-circle"></i>회원 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <input type="text" id="keyword" placeholder="검색어 입력">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
                <button id="reset_btn">초기화</button>
            </div>
            <div class="table_area">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>책제목</th>
                            <th>저자</th>
                            <th>가격</th>
                            <th>설명</th>
                            <th>재고</th>
                            <th>카테고리</th>
                            <th>등록일</th>
                            <th>수정일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.total == 0}">
                            <tr>
                                <td id="nodata" colspan="10">데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="b">
                            <tr>
                                <td>${b.bi_seq}</td>
                                <td>${b.bi_title}</td>
                                <td>${b.bi_author}</td>
                                <td>${b.bi_price}원</td>
                                <td>${b.bi_description}</td>
                                <td>${b.bi_stock}권</td>
                                <td>${b.bi_category}</td>
                                <td>${b.bi_reg_dt}</td>
                                <td>${b.bi_mod_dt}</td>
                                <td>
                                    <button class="modify_btn" data-seq="${b.bi_seq}"><i class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${b.bi_seq}"><i class="fas fa-minus-circle"></i></button>
                                </td>
                            </tr>
                        </c:forEach>
                        <!-- <tr>
                            <td>1234</td>
                            <td>자바</td>
                            <td>김</td>
                            <td>15000원</td>
                            <td>가나다라</td>
                            <td>100권</td>
                            <td>프로그램</td>
                            <td>2021-12-10 11:11:00</td>
                            <td>2021-12-10 11:11:00</td>
                            <td>
                                <button class="modify_btn"><i class="fas fa-pencil-alt"></i></button>
                                <button class="delete_btn"><i class="fas fa-minus-circle"></i></button>
                            </td>
                        </tr> -->
                    </tbody>
                </table>
            </div>
            <div class="pager_area">
                <button id="prev"><i class="fas fa-chevron-left"></i></button>
                <div class="pagers">
                    <c:forEach begin="1" end="${data.pageCnt}" var="i">
                        <a href="/book?offset=${(i-1)*10}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="member_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-address-book"></i>
                </div>
                <h2>도서 추가</h2>
                <p>도서 정보를 입력해주세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="member_name" placeholder="이름"><br>
                <input type="text" id="member_phone_num" placeholder="전화번호 (01012345678)">
                <input type="text" id="member_email" placeholder="이메일 (mail@mail.com)">
                <input type="number" id="book_price" placeholder="가격"><br>
                <input type="text" id="book_desc" placeholder="설명"><br>
                <input type="number" id="book_stock" placeholder="재고"><br>
                <select id="book_categoty">
                    <option value="1">소설</option>
                    <option value="2">시</option>
                    <option value="3">에세이</option>
                </select>
                <select id="book_status">
                    <option value="1">보유</option>
                    <option value="2">대출 가능</option>
                    <option value="3">대출 불가능</option>
                </select>
            </div>
            <div class="btn_area">
                <button id="add_dep">등록하기</button>
                <button id="modify_dep">수정하기</button>
                <button id="cancel_dep">취소하기</button>
            </div>
        </div>
    </div>
</body>
</html>