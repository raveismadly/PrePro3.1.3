//get all users
getAllUsers();

//функция получения всех юзеров
function getAllUsers() {
    $.getJSON("http://localhost:8080/admin/rest", function (data) {
        let userRow = '';
        let userId = '';
        $.each(data, function (key, value) {
            if (key = "id") {
                userRow += '<tr>';
                userRow += '<td>' + value.id + '</td>>';
                userId = value.id;
            }
            if (key = "username") {
                userRow += '<td>' + value.username + '</td>>';
            }
            if (key = "password") {
                userRow += '<td>' + value.password + '</td>>';
            }
            if (key = "roles") {
                let roles = value.roles;
                let roleName = '';
                $.each(roles, function (key1, value1) {
                    if (key1 = "role") {
                        roleName += value1.role;
                    }
                });
                userRow += '<td>' + roleName + '</td>';
                userRow += '<td><a class="btn btn-primary" id="btnEditUser' + value.id + '" data-toggle="modal" data-target=".bd-example-modal-md" onclick="editUser(' + value.id + ')" role="button">Edit</a></td>';
                userRow += '<td><a class="btn btn-primary" id="btnDeleteUser" onclick="deleteUser(' + value.id + ')" role="button">Delete</a>' + '</td>';
                userRow += '</tr>';
            }
        });
        $('#users-table').append(userRow);
    });
}

// add user
$('#btnAddUser').click(function () {
    let addUser = {};
    addUser.username = $('#inputAddUsername').val();
    addUser.password = $('#inputAddPassword').val();
    addUser.roles = getCheckedRoles(); //$('#roleAdd').val();//
    $.ajax({
        url: 'http://localhost:8080/admin/add/rest',
        method: 'POST',
        data: JSON.stringify(addUser),
        contentType: 'application/json; charset=utf-8',
        success: function () {
            //очищаем форму Add
            $('#inputAddUsername').val('');
            $('#inputAddPassword').val('');

            let table = $('#users-table');
            table.empty();
            $('#exampleModalAdd').modal('close');
            $('#nav-allusers-tab').tab('show');
            getAllUsers();

        },
        error: function (error) {
            alert("error Add: " + error);
        }
    });
});

//GET edit user
function editUser(id) {
    $.ajax({
        url: "http://localhost:8080/admin/edit/rest/" + id,
        method: "GET",
        success: function (editData) {
            $('#modal-title').text('Edit User: ' + editData.username);
            $('#modalEditId').val(editData.id);
            $('#modalEditUsername').val(editData.username);
            let emptyPassword = '';
            $('#modalEditNewPassword').val(emptyPassword);

            //  var editUserString = JSON.stringify(editData);
        }, error: function (error) {
            alert(error);
        }

    });
}

// edit Post
$('#btnSaveEdit').click(function () {
    let edit = {};
    edit.id = $('#modalEditId').val();
    edit.username = $('#modalEditUsername').val();
    edit.password = $('#modalEditNewPassword').val();
    edit.roles = getCheckedRoles();
    $.ajax({
        url: 'http://localhost:8080/admin/edit/rest',
        data: JSON.stringify(edit),
        method: 'POST',
        contentType: 'application/json; charset=utf-8',
        success: function () {
            let table = $('#users-table');
            table.empty();
            $('#exampleModal').modal('hide');
            $('#nav-allusers-tab').tab('show');
            getAllUsers();
        }
    })
});

// //DELETE user
function deleteUser(id) {
    $.ajax({
        url: 'http://localhost:8080/admin/delete/rest/' + id,
        method: 'GET',
        success: function () {
            //стираем таблицу и заново отображаем всех юзеров
            let table = $('#users-table');
            table.empty();
            getAllUsers();
        },
        error: function (error) {
            alert(error);
        }
    })
}


function getCheckedRoles() {
    let verseChoose = document.querySelector('select').getAttribute('id');
    let singleValues = verseChoose === "#roleEdit" ? $("#roleAdd").val() : $("#roleEdit").val();
    let roles = [];
    let role = {};
    if (singleValues === "ADMIN") {

        role.id = "1";
    } else if (singleValues === "USER") {
        role.id = "2";
    }
    roles.push(role);
    return roles;
}




