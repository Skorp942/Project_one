$(document).ready(function() {
    let exampleModal = document.getElementById('userModal')
    exampleModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget
        var userId = button.getAttribute('data')
        $('.IdContainer').empty();
        if (!button.getAttribute('data')) {
            $('#exampleModalLabel').text('Добавить пользователя');
            $('#save-user-button').text('Добавить');
        }
        if (userId) {
            $('#exampleModalLabel').text('Изменить данные пользователя');
            $('#save-user-button').text('Сохранить');
            $.get({
                url: '/user/getUser.json?userId=' + userId,
                success: (data) => {
                    let modal = $(this)
                    modal.find('#userId').val(data.userId)
                    modal.find('#username').val(data.username)
                    modal.find('#password').val(data.password)
                    modal.find('#email').val(data.email)
                    modal.find('#userRole').val(data.userRole)
                    if (data.enabled == true)
                        modal.find('#enabled').val("true")
                    else
                        modal.find('#enabled').val("false")
                },
                error: (err) => {
                    alert('error: ' + err);
                }
            });
        }
    })

    $('#save-user-button').click(function () {
        let modal = $('#userModal')
        let user = {
            userId: modal.find('#userId').val(),
            username: modal.find('#username').val(),
            password: modal.find('#password').val(),
            email: modal.find('#email').val(),
            userRole: modal.find('#userRole').val(),
            enabled: modal.find('#enabled').val()
        };
        $('.IdContainer').empty();
        $('.userContainer').empty();
        $('.passwordContainer').empty();
        $('.emailContainer').empty();
        $.ajax({
            url: '/user/postUser.json',
            type: 'POST',
            data: JSON.stringify(user),
            contentType: "application/json;",
            dataType: "json",
            success: (response) => {
                if (response.errors) {
                    $.each(response.errors, function (index, value) {
                        if (response.errorCode[index] == "username") {
                            $('<p>' + value + '</p>').appendTo('.userContainer');
                        }
                        ;
                        if (response.errorCode[index] == "password") {
                            $('<p>' + value + '</p>').appendTo('.passwordContainer');
                        }
                        ;
                        if (response.errorCode[index] == "email") {
                            $('<p>' + value + '</p>').appendTo('.emailContainer');
                        }
                        ;
                    });
                }
                if (response.message) {
                    $('<p>' + response.message + '</p>').appendTo('.IdContainer')
                }
                if (response.message == "Добавленно") {
                    $.get("/user/tableUsers.html", function (data) {
                        $("#myTable").append(data);
                    });
                }
            },
            error: (err) => {
                console.log(err);
            }
        })
    });

    $('#close-user-button').click(function () {
        var myModal = document.getElementById('userModal')
        var modal = bootstrap.Modal.getInstance(myModal)
        modal.hide()
        modal.dispose()
        location.reload()
    })

    $('.btn-danger').click(function () {
        var userId = $(this).attr("data")
        if (userId) {
            $.ajax({
                url: '/user/deleteUser.json',
                type: 'POST',
                data: JSON.stringify(userId),
                contentType: "application/json;",
                dataType: "json",
                success: (response) => {
                    location.reload()
                },
                error: (err) => {
                    alert(err);
                }
            })
        }
    })
})