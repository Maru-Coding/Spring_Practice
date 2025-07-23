<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Board Read</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->

	<!-- 게시글 영역 -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Board Read Page</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="form-group">
						<label>Bno</label>
						<input class="form-control" name='bno' value='<c:out value="${board.bno}"/>' readonly="readonly">
						
					</div>
					
					<div class="form-group">					
						<label>Title</label>
						<input class="form-control" name='title' value='<c:out value="${board.title}"/>' readonly="readonly">
					</div>
					
					<div class="form-group">
						<label>Text area</label>
						<textarea class="form-control" rows="3" name='content' readonly="readonly"><c:out value="${board.content}"/></textarea>
					</div>
					
					<div class="form-group">
						<label>Writer</label>
						<input class="form-control" name='writer' value='<c:out value="${board.writer}"/>' readonly="readonly">
					</div>
					
					<div class="form-group">
						<label>Reg Date</label>
						<input class="form-control" name='regDate' value='<fmt:formatDate pattern = "yyyy/MM/dd HH:mm:ss" value="${board.regdate}"/>' readonly="readonly">
					</div>
					
					<div class="form-group">
						<label>Update Date</label>
						<input class="form-control" name='updateDate' value='<fmt:formatDate pattern = "yyyy/MM/dd HH:mm:ss" value="${board.updateDate}"/>' readonly="readonly">
					</div>
					
					<button data-oper='modify' class="btn btn-default" onclick="location.href='/board/modify?bno=<c:out value="${board.bno}"/>'">Modify</button>
					<button data-oper='list' class="btn btn-info" onclick="location.href='/board/list'">List</button>
					
					<form id='operForm' action="/board/modify" method="get">
						<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
						<input type='hidden' id='pageNum' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
						<input type='hidden' id='amount' name='amount' value='<c:out value="${cri.amount}"/>'>
						<input type='hidden' id='amount' name='type' value='<c:out value="${cri.type}"/>'>
						<input type='hidden' id='amount' name='keyword' value='<c:out value="${cri.keyword}"/>'>
					</form>
				</div>
				<!-- end panel-body -->
			</div>
			<!-- end panel -->
		</div>
	</div>
	<!-- /.row -->
	
	<!-- 댓글 영역 -->
	<div class='row'>
		<div class="col-lg-12">
			<!-- /.panel -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-comments fa-fw"></i> Reply
				</div>
				
				<!-- /.panel-heading -->
				<div class="panel-body">
					<ul class="chat" style="list-style: none; padding-left: 0; margin: 0;">
						<!-- start reply -->
						<li class="left clearfix" data-rno='12'>
							<div>
								<div class="header">
									<strong class="primary-font">user00</strong>
									<small class="pull-right text-muted">2025-07-23 17:06:00</small>
								</div>
								<p>GOOD JOB!</p>
							</div>
						</li>
						<!-- end reply -->
					</ul>
					<!-- ./ end ul -->
				</div>
				<!-- /.panel .chat-panel -->
			</div>
		</div>
		<!-- ./ end row -->
	</div>
</div>

<!-- 댓글 관련 스크립트 불러오기 -->
<script type="text/javascript" src="/resources/js/reply.js"></script>

<!-- 댓글 관련 스크립트 구현 -->
<script>
	$(document).ready(function(){
		var bnoValue = '<c:out value="${board.bno}"/>';
		var replyUL = $(".chat");
		
		showList(1);
		
		function showList(page){
			replyService.getList({bno:bnoValue,page: page || 1}, function(list){
				var str = "";
				
				if(list == null || list.length == 0){
					replyUL.html("");
					
					return;
				}
				
				for (var i = 0, len = list.length || 0; i < len; i++){
					str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
					str += "  <div><div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
					str += "    <small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
					str += "    <p>"+list[i].reply+"</p></div></li>";
				}
				
				replyUL.html(str);
			}); // end function
		} // end showList
	});

	/* 댓글 Ajax 테스트 (하단)
	console.log("=================");
	console.log("JS TEST");
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	*/
	
	// 댓글 목록 조회 테스트
	/*
	replyService.getList({bno:bnoValue, page:1}, function(list){
		for(var i = 0, len = list.length||0; i < len; i++){
			console.log(list[i]);
		}
	});
	*/
	
	// 댓글 조회 테스트 (1건))
	/*
	replyService.get(22, function(data){
		console.log(data);
	});
	*/
	
	// 댓글 작성 테스트
	/* 
	replyService.add(
		{reply:"JS Test", replyer:"tester", bno:bnoValue}
		,
		function(result){
			alert("RESULT : " + result);
		}
	);
	*/
	
	// 댓글 삭제 테스트
	/*
	replyService.remove(21, function(count){
		console.log(count);
		
		if (count === "success"){
			alert("REMOVED");
		}
	}, function (err){
		alert('ERROR...');
	});
	*/
	
	// 댓글 수정 테스트
	/*
	replyService.update({
		rno : 22,
		bno : bnoValue,
		reply : "Modified Reply......"
	}, function(result){
		alert("수정 완료!");
	});
	*/
</script>

<!-- 댓글 상세조회에서 수정버튼과 목록 돌아가기 기능 -->
<script type="text/javascript">
	$(document).ready(function(){
		var operForm = $("#operForm");
		
		$("button[data-oper='modify']").on("click", function(e){
			operForm.attr("action", "/board/modify").submit();
		});
		
		$("button[data-oper='list']").on("click", function(e){
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list");
			operForm.submit();
		});
	});
</script>

<%@include file="../includes/footer.jsp"%>