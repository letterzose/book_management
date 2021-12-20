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
    <link rel="stylesheet" href="/assets/css/member_list.css">
    <script src="/assets/js/member.js"></script>
</head>
<body>
    <main>
        <h1><i class="fas fa-address-book"></i> 회원 관리</h1>
        <button id="add_book"><i class="fas fa-plus-circle"></i> 회원 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <select id="search_type">
                        <option value="title">도서</option>
                        <option value="name"
                            <c:if test="${data.type=='name'}">selected</c:if>
                        >이름</option>
                    </select>
                    <input type="text" id="keyword" placeholder="검색어 입력" value="${data.keyword}">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
                <button id="reset_btn">초기화</button>
            </div>
            <div class="table_area">
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>도서</th>
                            <th>이름</th>
                            <th>생년월일</th>
                            <th>전화번호</th>
                            <th>이메일</th>
                            <th>상태</th>
                            <th>등록일</th>
                            <th>수정일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.list.size() == 0}">
                            <tr>
                                <td id="nodata" colspan="11">데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="m">
                            <tr>
                                <td>${m.mi_seq}</td>
                                <td>${m.book_title}</td>
                                <td>${m.mi_name}</td>
                                <td>${m.mi_birth}</td>
                                <td>${m.mi_phone_num}</td>
                                <td>${m.mi_email}</td>
                                <td class="member_status">
                                    <c:if test="${m.mi_status == 1}">
                                        <span style="background-color:rgb(17, 226, 27)">정상</span>
                                    </c:if>
                                    <c:if test="${m.mi_status == 2}">
                                        <span style="background-color:rgb(255, 23, 23)">정지</span>
                                    </c:if>
                                    <c:if test="${m.mi_status == 3}">
                                        <span style="background-color:rgb(255, 110, 26)">휴면</span>
                                    </c:if>
                                    <c:if test="${m.mi_status == 4}">
                                        <span style="background-color:rgb(251, 186, 64)">정지</span>
                                    </c:if>
                                </td>
                                <td>${m.mi_reg_dt}</td>
                                <td>${m.mi_mod_dt}</td>
                                <td>
                                    <button class="modify_btn" data-seq="${m.mi_seq}"><i class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${m.mi_seq}"><i class="fas fa-minus-circle"></i></button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="pager_area">
                <button id="prev"><i class="fas fa-chevron-left"></i></button>
                <div class="pagers">
                    <c:forEach begin="1" end="${data.pageCnt}" var="i">
                        <a href="/member?offset=${(i-1)*10}&type=${type}&keyword=${keyword}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="department_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-address-book"></i>
                </div>
                <h2>회원 추가</h2>
                <p>회원 정보를 입력해주세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="book_title_info" placeholder="도서 명" disabled>
                <button id="search_book">도서검색</button>
                <br>
                <input type="text" id="member_name" placeholder="이름">
                <input type="text" id="member_birth" placeholder="생년월일 (YYYYMMDD)">
                <input type="password" id="member_pwd" placeholder="비밀번호">
                <input type="password" id="member_pwd_confirm" placeholder="비밀번호 확인">
                <input type="text" id="member_phone" placeholder="전화번호 (01012345678)">
                <input type="text" id="member_email" placeholder="이메일 (mail@mail.com)">
                <select id="member_status">
                    <option value="1">정상</option>
                    <option value="2">정지</option>
                    <option value="3">휴면</option>
                </select>
            </div>
            <div class="btn_area">
                <button id="add_dep">등록하기</button>
                <button id="modify_dep">수정하기</button>
                <button id="cancel_dep">취소하기</button>
            </div>
        </div>
    </div>
    <div class="book_search">
        <div class="book_search_box">
            <input type="text" id="book_keyword" placeholder="예) 컴퓨터, 컴퓨터공학, 공학">
            <button id="book_search_btn"><i class="fas fa-search"></i></button>
        </div>
        <div class="search_result">
            <ul>
                
            </ul>
        </div>
        <div class="book_search_buttons">
            <button id="book_search_close">닫기</button>
        </div>
    </div>
</body>
</html>