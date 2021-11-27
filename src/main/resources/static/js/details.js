function isValidContents(contents) {
    if (contents == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    return true;
}

function editPost(id) {
    showEdits(id);
    let contents = $(`#${id}-contents`).text().trim();
    $(`#${id}-textarea`).val(contents);
}

function showEdits(id) {
    $(`#${id}-editarea`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-delete`).show();

    $(`#${id}-contents`).hide();
    $(`#${id}-edit`).hide();
}

$(document).ready(function () {
    // HTML 문서를 로드할 때마다 실행합니다.
    getMessages();
})

// 메모를 불러와서 보여줍니다.
function getMessages() {
    // 1. 기존 메모 내용을 지웁니다.
    $('#cards-box').empty();
    // 2. 메모 목록을 불러와서 HTML로 붙입니다.
    $.ajax({
        type: 'GET',
        url: '/memories/detail/{id}/comments',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let comment = response[i];
                let id = comment['id'];
                let username = comment['username'];
                let commentary = comment['commentary'];
                let modifiedAt = comment['modifiedAt'];
                addHTML(id, username, commentary, modifiedAt);
            }
        }
    })
}

function addHTML(id, username, commentary, modifiedAt) {
    // 1. HTML 태그를 만듭니다.
    let tempHtml = `<div class="card">
                <!-- date/username 영역 -->
                <div class="metadata">
                    <div class="date">
                        ${modifiedAt}
                    </div>
                    <div id="${id}-username" class="username">
                        ${username}
                    </div>
                </div>
                <!-- contents 조회/수정 영역-->
                <div class="contents">
                    <div id="${id}-contents" class="text">
                        ${contents}
                    </div>
                    <div id="${id}-editarea" class="edit">
                        <textarea id="${id}-textarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                    </div>
                </div>
                <!-- 버튼 영역-->
                <div class="footer">
                    <img id="${id}-edit" class="icon-start-edit" src="images/edit.png" alt="" onclick="editPost('${id}')">
                    <img id="${id}-delete" class="icon-delete" src="images/delete.png" alt="" onclick="deleteOne('${id}')">
                    <img id="${id}-submit" class="icon-end-edit" src="images/done.png" alt="" onclick="submitEdit('${id}')">
                </div>
            </div>`;
    // 2. #cards-box 에 HTML을 붙인다.
    $('#cards-box').append(tempHtml);
}

function writePost() {
    // 1. 작성한 메모를 불러옵니다.
    let contents = $('#contents').val();

    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(contents) == false) {
        return;
    }

    // 4. 전달할 data JSON으로 만듭니다.
    let data = {'commentary': commentary};

    // 5. POST /api/memos 에 data를 전달합니다.
    $.ajax({
        type: "POST",
        url: `/memories/detail/{id}/comments`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    });
}

function submitEdit(id) {
    // 1. 작성 대상 메모의 username과 contents 를 확인합니다.
    let username = $(`#${id}-username`).text().trim();
    let contents = $(`#${id}-textarea`).val().trim();

    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(contents) == false) {
        return;
    }

    // 3. 전달할 data JSON으로 만듭니다.
    let data = {'username': username, 'contents': contents};

    // 4. PUT /api/memos/{id} 에 data를 전달합니다.
    $.ajax({
        type: "PUT",
        url: `/memories/detail/{id}/comments`,
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지 변경에 성공하였습니다.');
            window.location.reload();
        }
    });
}

function deleteOne(id) {
    // 1. DELETE /api/memos/{id} 에 요청해서 메모를 삭제합니다.
    $.ajax({
        type: "DELETE",
        url: `/memories/detail/{id}/comments`,
        success: function (response) {
            alert('메시지 삭제에 성공하였습니다.');
            window.location.reload();
        }
    })
}


// <div className="container" id="cards-box">
//     <div className="row">
//         <div className="col">
//             유저명
//         </div>
//         <div className="col second">
//             내용
//         </div>
//         <div className="col">
//             작성(수정) 시각
//         </div>
//     </div>
// </div>

//background-size: cover; background: url('/images/pexels-gilberto-olimpio-7826039.jpg') no-repeat;


// <form action="/memories/detail/{id}/comments" method="post">
//     <div className="row">
//         <div className="form-group col-md-8">
//             <label htmlFor="commentary" className="col-form-label">댓글</label> <input type="text"
//                                                                                      className="form-control"
//                                                                                      id="commentary" name="commentary"
//                                                                                      placeholder="Commentary">
//         </div>
//         <div className="col-md-6">
//             <input type="submit" className="btn btn-primary" value="Add Commentary">
//         </div>
//         <div className="form-group col-md-8"></div>
//     </div>
// </form>