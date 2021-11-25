//$(document).ajaxError(ajaxErrorHandler);

//var ajaxErrorHandler = function () {
//do something
//}

$(document).ready(function (){
    loadMemory();
})

function loadMemory() {
    $('#setup').empty();
    $.ajax({
        type: "GET",
        url: "/memories",
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let memory = response[i];
                let id = memory['id'];
                let title = memory['title'];
                let thoughts = memory['thoughts'];
                let comment = memory['comment'];
                let modifiedAt = memory['modifiedAt'];
                addHTML(id, title, thoughts, comment, modifiedAt);
            }
        }
    })
}

function addHTML(id, title, thoughts, comment, modifiedAt) {
    let tempHtml = `<div class="list-group w-50 vh-20 index-list rounded-pill" id="setup">
                        <a href="/detail/${id}" class="list-group-item list-group-item-action">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="title mb-1" >제목: ${title}</h5>
                                <small class="modifiedAt"><i class="far fa-clock"></i> ${modifiedAt}</small>
                            </div>
                            <p></p>
                            <p class="name mb-1">내용: ${thoughts}</p>
                        </a>
                    </div>`
    $('#setup').append(tempHtml);
    console.log(modifiedAt);
}


function recordMemory() {
    let title = $('#title').val();
    let thoughts = $('#thoughts').val();
    let data = {'title': title, 'thoughts': thoughts};

    $.ajax({
        type: "POST",
        url: "/memories",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            if (response) {
                alert("메시지가 성공적으로 작성되었습니다.");
                window.location.reload();
            } else {
                alert("실패");
            }
        },
        error: function (request, status, error) {
            alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
            console.log(title);
            console.log(thoughts);
        }
    });
}