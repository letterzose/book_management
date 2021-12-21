$(function(){
    $(".main_menu a:nth-child(3)").addClass("active");
    $("#search_book").click(function(){
        $(".book_search").css("display", "block");
    })
    $("#book_search_close").click(function(){
        $(".book_search").css("display", "");
    })
    $("#book_keyword").keyup(function(e){
        if(e.keyCode == 13) $("#book_search_btn").trigger("click");
    })
    $("#book_search_btn").click(function(){
        $.ajax({
            url:"/book/keyword?keyword="+$("#book_keyword").val(),
            type:"get",
            success:function(r) {
                console.log(r);
                $(".search_result ul").html("");
                for(let i=0; i<r.list.length; i++) {
                    let str_status = "";
                    if(r.list[i].bi_status == 1) str_status = "보유"
                    if(r.list[i].bi_status == 2) str_status = "대출 가능"
                    if(r.list[i].bi_status == 3) str_status = "대출 불가능"

                    let tag =
                    '<li>'+
                        '<a href="#" data-book-seq="'+r.list[i].bi_seq+'">'+r.list[i].bi_title+'</a>'+
                        '<span class="status'+r.list[i].bi_status+'">'+str_status+'</span>'+
                    '</li>';
                    $(".search_result ul").append(tag);
                }

                $(".search_result ul a").click(function(e){
                    e.preventDefault(); // a 태그의 링크 기능 제거
                    let seq = $(this).attr("data-book-seq");
                    let name = $(this).html();
                    
                    $("#book_title_info").attr("data-book-seq", seq);
                    $("#book_title_info").val(name);

                    $(".search_result ul").html("");
                    $("#book_keyqord").val("");
                    $(".book_search").css("display", "");
                })
            }
        })
    })

    $("#add_dep").click(function(){
        let book_title_info =$("#book_title_info").attr("data-book-seq");
        let member_name =$("#member_name").val();
        let member_pwd =$("#member_pwd").val();
        let member_birth =$("#member_birth").val();
        let member_phone =$("#member_phone").val();
        let member_email =$("#member_email").val();
        let member_status =$("#member_status option:selected").val();
        
        let member_pwd_confirm = $("#member_pwd_confirm").val();
        
        if(book_title_info == undefined) {
            alert("도서를 입력해주세요");
            return;
        }
        if(member_pwd == '') {
            alert("비밀번호를 입력해주세요");
        }
        if(member_pwd != member_pwd_confirm) {
            alert("비밀번화 비밀번호 확인이 일치하지 않습니다.");
            return;
        }
        
        let data = {
            mi_bi_seq:book_title_info,
            mi_name:member_name,
            mi_pwd:member_pwd,
            mi_birth:member_birth,
            mi_phone_num:member_phone,
            mi_email:member_email,
            mi_status:member_status
        }

        $.ajax({
            url:"/member/add",
            type:"post",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(e) {
                alert(e.message);
                if(e.status)
                location.reload();
            }
        })
    })
    $("#add_book").click(function(){
        $(".popup_wrap").css("display", "block");
        $("#add_dep").css("display", "inline-block");
        $("#modify_dep").css("display", "none");
        $("#cancel_dep").css("display", "inline-block");
        $(".popup .top_area h2").html("회원 추가");
        $(".popup .top_area p").html("회원 정보를 입력해주세요");
    })
    $("#cancel_dep").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 내용은 저장되지 않습니다)") == false) return;

        $("#book_title_info").attr("data-book-seq", "");
        $("#book_title_info").val("");
        $("#member_name").val("");
        $("#member_pwd").val("");
        $("#member_pwd_confirm").val("");
        $("#member_birth").val("");
        $("#member_phone").val("");
        $("#member_email").val("");
        $("#member_status").val("1").prop("selected", true);

        $(".popup_wrap").css("display", "")
    })
    $("#search_btn").click(function(){
        let type = $("#search_type option:selected").val();
        let keyword = $("#keyword").val();

        location.href = "/member?type="+type+"&keyword="+keyword;
    })

    $(".delete_btn").click(function(){
        if(!confirm("삭제하시겠습니까?")) return;
        let seq = $(this).attr("data-seq");
        $.ajax({
            url:"/member/delete?seq="+seq,
            type:"delete",
            success:function(r) {
                alert(r.message);
                if(r.status)
                    location.reload();
            },
            error:function(r) {
                console.log(r)
                alert(r.responseJSON.message);
            }
        })
    })

    let modify_seq = 0;
    $(".modify_btn").click(function(){
        let seq = $(this).attr("data-seq");
        modify_seq = seq;
        $.ajax({
            url:"/member/get?seq="+seq,
            type:"get",
            success:function(r) {
                $(".popup_wrap").css("display", "block");
                $("#add_dep").css("display", "none");
                $("#modify_dep").css("display", "inline-block");
                $("#cancel_dep").css("display", "inline-block");
                $(".popup .top_area h2").html("회원 수정");
                $(".popup .top_area p").html("회원 정보를 수정해주세요");

                $("#book_title_info").attr("data-book-seq", r.mi_bi_seq);
                $("#book_title_info").val(r.book_title);
                $("#member_name").val(r.mi_name);
                $("#member_pwd").val("**********");
                $("#member_pwd_confirm").val("**********");
                $("#member_birth").val(r.mi_birth);
                $("#member_phone").val(r.mi_phone_num);
                $("#member_email").val(r.mi_email);
                $("#member_status").val(r.mi_status).prop("selected", true);
            }
        })
    })

    $("#modify_dep").click(function(){
        if(confirm("수정하시겠습니까?") == false) return;
        let data = {
            mi_seq:modify_seq,
            mi_bi_seq:$("#book_title_info").attr("data-book-seq"),
            mi_name:$("#member_name").val(),
            mi_birth:$("#member_birth").val(),
            mi_phone_num:$("#member_phone").val(),
            mi_email:$("#member_email").val(),
            mi_status:$("#member_status option:selected").val()        
        }
        $.ajax({
            url:"/member/modify",
            type:"patch",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                if(r.status)
                    location.reload();
            }
        })
    })
})



