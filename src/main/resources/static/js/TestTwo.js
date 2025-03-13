$(document).ready(function () {
    $("#myButton").click(function () {
        let inputValue = $("#name").val().trim(); // 입력값 가져오기 및 공백 제거
        if (!inputValue) {
            alert("입력값을 입력하세요!");
            return;
        }

        // 요청과 응답을 동시에 표시할 새로운 영역 생성
        let requestId = "request" + new Date().getTime(); // 고유 ID 생성
        let newRequest = $("<div>").addClass("request-response-container").attr("id", requestId);

        // 요청 내용 추가
        newRequest.append("<h3>Request:</h3>");
        newRequest.append("<p>요청값: " + inputValue + "</p>");

        // 응답 영역 추가
        newRequest.append("<h3>Response:</h3>");
        newRequest.append(`<p id="response-${requestId}">대기 중…</p>`);

        // 요청 컨테이너에 추가 (새로운 요청이 위쪽에 추가됨)
        $("#requestsContainer").prepend(newRequest);

        // Ajax 요청 실행
        sendRequest(inputValue, function (response, error) {
            let responseText = error ? "요청 실패: " + error : "서버 응답: " + response;
            $(`#response-${requestId}`).text(responseText);
        });
    });
});

// Ajax 요청 함수 (콜백 방식 유지)
function sendRequest(inputValue, callback) {
    $.ajax({
        url: `http://localhost:8080/test-two/${inputValue}`, // 동적 URL
        type: "GET", // HTTP 메서드
        success: function (response) {
            callback(response, null); // 성공 시 응답을 콜백에 전달
        },
        error: function (xhr, status, error) {
            callback(null, error); // 실패 시 에러 메시지를 콜백에 전달
        }
    });
}