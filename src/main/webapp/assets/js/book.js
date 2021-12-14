/* book.js */
$(function(){
    $(".main_menu a:nth-child(2)").addClass("active");
    $("#add_book").click(function(){
        // alert("도서 추가 팝업 열기");
        $(".popup_wrap").addClass("open");
        $("#add_dep").css("display", "inline-block");
        $("#modify_dep").css("display", "none");
        $(".popup .top_area h2").html("도서 추가");
        $(".popup .top_area p").html("도서 정보를 입력해주세요");
    })
    $("#add_dep").click(function() {
        if(confirm("도서를 등록하시겠습니까?") == false) return;
        let book_title = $("#book_title").val();
        let book_author = $("#book_author").val();
        let book_price = $("#book_price").val();
        let book_desc = $("#book_desc").val();
        let book_stock = $("#book_stock").val();
        let book_categoty = $("#book_categoty option:selected").val();
        let book_status = $("#book_status option:selected").val();

        let data = {
            bi_title:book_title,
            bi_author:book_author,
            bi_price:book_price,
            bi_description:book_desc,
            bi_stock:book_stock,
            bi_category:book_categoty,
            bi_status:book_status
        }

        $.ajax({
            type:"post",
            url:"book/add",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                if(r.status)
                location.reload();
            }
        })
        
    })
    $("#cancel_dep").click(function() {
        if(confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.)") == false) return;

        $("#book_title").val("");
        $("#book_author").val("");
        $("#book_price").val("");
        $("#book_desc").val("");
        $("#book_stock").val("");
        $("#book_categoty").val("1").prop("selected", true);
        $("#book_status").val("1").prop("selected", true);

        $(".popup_wrap").removeClass("open");
    })

    $(".delete_btn").click(function(){
        if(confirm("도서를 삭제하시겠습니까?\n(이 동작은 되돌릴 수 없습니다.)")== false) return;

        let seq = $(this).attr("data-seq");

        $.ajax({
            type:"delete",
            url:"/book/delete?seq="+seq,
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    })
    let modify_data_seq = 0;
    $(".modify_btn").click(function(){
        modify_data_seq = $(this).attr("data-seq");
        $(".popup_wrap").addClass("open");
        $("#add_dep").css("display", "none");
        $("#modify_dep").css("display", "inline-block");
        $(".popup .top_area h2").html("도서 수정");
        $(".popup .top_area p").html("수정할 내용을 입력해주세요");
        $.ajax({
            type:"get",
            url:"/book/get?seq="+$(this).attr("data-seq"),
            success:function(r) {
                console.log(r);
                $("#book_title").val(r.data.bi_title);
                $("#book_author").val(r.data.bi_author);
                $("#book_price").val(r.data.bi_price);
                $("#book_desc").val(r.data.bi_description);
                $("#book_stock").val(r.data.bi_stock);
                $("#book_categoty").val(r.data.bi_category).prop("selected", true);
                $("#book_status").val(r.data.bi_status).prop("selected", true);
            }
        })
    })

    $("#modify_dep").click(function(){
        if(confirm("수정하시겠습니까?")== false) return;

        let book_title = $("#book_title").val();
        let book_author = $("#book_author").val();
        let book_price = $("#book_price").val();
        let book_desc = $("#book_desc").val();
        let book_stock = $("#book_stock").val();
        let book_categoty = $("#book_categoty option:selected").val();
        let book_status = $("#book_status option:selected").val();

        let data = {
            bi_seq:modify_data_seq,
            bi_title:book_title,
            bi_author:book_author,
            bi_price:book_price,
            bi_description:book_desc,
            bi_stock:book_stock,
            bi_category:book_categoty,
            bi_status:book_status
        }
        $.ajax({
            type:"patch",
            url:"/book/update",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
        
    })

    $("#search_btn").click(function(){
        location.href="/book?keyword="+$("#keyword").val();
    })
    $("#keyword").keydown(function(e){
        console.log(e.keyCode)
        if(e.keyCode == 13) {
            $("#search_btn").trigger("click");
        }
    })
})
