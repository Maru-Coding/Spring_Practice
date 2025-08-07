console.log("Reply Module......");

var replyService = (function(){
	
	// 댓글 작성 처리
	function add(reply, callback, error){
		console.log("add reply..............");
		
		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}00
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})			
	}
	
	// 댓글 목록 조회
	function getList(param, callback, error) {
		var bno = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
			function(data){
				if (callback) {
					// 댓글 목록 모두 가져오기
					// callback(data);
					
					// 댓글 숫자와 목록을 가져오는 경우
					callback(data.replyCnt, data.list);
				}
			}).fail(function(xhr, status, err) {
				if (error) {
					error();
				}
			});
	}
	
	// 댓글 조회 (1건)
	function get(rno, callback, error){
		$.get("/replies/" + rno + ".json", function(result){
			if (callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err) {
			if (error) {
				error();
			}
		});
	}
	
	// 댓글 삭제
	function remove(rno, callback, error){
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}
	
	// 댓글 수정
	function update(reply, callback, error) {
		console.log("RNO: " + reply.rno);
		
		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}
	
	// 시간 처리 (JSON 데이터의 숫자형태를 시간형태로 변환)
	function displayTime(timeValue) {
		const today = new Date();
		const dateObj = new Date(timeValue);
		const gap = today.getTime() - timeValue;
	
		const yy = dateObj.getFullYear();
		const mm = dateObj.getMonth() + 1;
		const dd = dateObj.getDate();
	
		const hh = dateObj.getHours();
		const mi = dateObj.getMinutes();
		const ss = dateObj.getSeconds();
	
		const twoDigits = (num) => (num > 9 ? '' : '0') + num;
	
		if (gap < 1000 * 60 * 60 * 24) {
			// 하루 이내: 시간만 출력
			return [twoDigits(hh), ':', twoDigits(mi), ':', twoDigits(ss)].join('');
		} else {
			// 하루 이상: 날짜 + 시간 출력
			return [
				yy, '/', twoDigits(mm), '/', twoDigits(dd), ' ',
				twoDigits(hh), ':', twoDigits(mi), ':', twoDigits(ss)
			].join('');
		}
	}
			
	return {
		add : add,
		get : get,
		getList : getList,
		remove : remove,
		update : update,
		displayTime : displayTime
	};
})();