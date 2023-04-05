$(document).ready(function () {

    let usersData;
    let rolesData;
    let authUser;
    const selectCreate = document.getElementById("addRoles");

    const editId = document.getElementById("editId");
    const editName = document.getElementById("editName");
    const editAge = document.getElementById("editAge");
    const editPassword = document.getElementById("editPassword");
    const editRole = document.getElementById("editRole");
    let editModal = new bootstrap.Modal(document.getElementById("edit-modal"));
    const selectEdit = document.getElementById("editRole")

    let deleteModal = new bootstrap.Modal(document.getElementById('delete-modal'));
    const deleteId = document.getElementById("deleteId");
    const deleteName = document.getElementById("deleteName");
    const deleteAge = document.getElementById("deleteAge");
    const deleteRole = document.getElementById("deleteRole");

    const usersTableBody = document.getElementById("tbody-users");
    const userTableBody = document.getElementById("tbody-user");


    $(document).on("click", ".edit-btn", function () {
        const id = $(this).data("id")
        openEditModal(id);
    });

    $(document).on("click", "#edit-submit", function (event) {
        const id = $(this).data("id")
        event.preventDefault();
        editUser(id);
    });

    $(document).on("click", ".delete-btn", function () {
        const id = $(this).data("id")
        openDeleteModal(id);
    });

    $(document).on("click", "#delete-submit", function () {
        const id = $(this).data("id")
        deleteUser(id);
    });

    getAuthUser();
    showAndUpdateUsersTab();
    showAndUpdateUserTab();

    //получение данных об авторизованном пользователе и добавление этих данных в header
    function getAuthUser() {
        fetch("http://localhost:8088/api/v1/users/auth-user")
            .then(response => response.json())
            .then(result => {
                authUser = result;
                let divAuthUser = document.getElementById("authUser")
                let userName = authUser.name;
                let userRoles = ' ';
                for (let i = 0; i < authUser.roles.length; i++) {
                    userRoles = userRoles + ' ' + authUser.roles[i].name;
                }

                divAuthUser.innerHTML = `
                       <b>${userName}</b>
                        with role:
                        <b>${userRoles}</b>
`;
            })
            .catch(error => console.error(error));
    }

    //Созданите ползователя вкладка New User
    $("#createUserForm").submit(function (event) {
        event.preventDefault();

        let name = document.getElementById("addName").value;
        let age = document.getElementById("addAge").value;
        let password = document.getElementById("addPassword").value;
        let roles = [];

        for (let i = 0; i < selectCreate.length; i++) {
            if (selectCreate[i].selected) {
                let role = {
                    id: 0,
                    name: null
                }

                role.id = rolesData[i].id;
                role.name = rolesData[i].name;
                roles.push(role);
            }
        }

        let user = {
            name: name,
            age: age,
            password: password,
            roles: roles
        }

        fetch('http://localhost:8088/api/v1/users/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
            .then(response => {
                    response.json();
                    showAndUpdateUsersTab();
                }
            )
            .then(data => {
                console.log(data);
                $(".notes").html('<div class="alert alert-primary" role="alert">\n' +
                    '  Успешно добавлен юзер!\n' +
                    '</div>');
            })
            .catch(error => console.error(error));

    });

    //функция открытия модели Edit
    function openEditModal(id) {
        fetch(`http://localhost:8088/api/v1/users/${id}`)
            .then(response => response.json())
            .then(async user => {
                editId.value = user.id;
                editName.value = user.name;
                editAge.value = user.age;
                editPassword.value = user.password;
                editRole.innerHTML = '';
                for (let i = 0; i < rolesData.length; i++) {
                    let bool = false;
                    user.roles.map(role => {
                        if (role.name === rolesData[i].name) bool = true
                    });
                    selectEdit.append(new Option(rolesData[i].name, rolesData[i].id, false, bool));
                }
                editModal.show();
                $("#edit-submit").data("id", editId.value);
            })
            .catch(error => console.error(error));
    }

    //нажатие на кнопку edit
    function editUser(id) {
        const name = editName.value;
        const age = editAge.value;
        const password = editPassword.value;
        let roles = [];

        for (let i = 0; i < selectEdit.length; i++) {
            if (selectEdit[i].selected) {

                let role = {
                    id: 0,
                    name: ""
                }

                role.id = rolesData[i].id;
                role.name = rolesData[i].name;
                roles.push(role);
            }
        }
        let user = {
            id: id,
            name: name,
            age: age,
            password: password,
            roles: roles
        };
        fetch(`http://localhost:8088/api/v1/users/`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            }, body: JSON.stringify(user)
        })
            .then(response => response.json())
            .then(updatedUser => {
                showAndUpdateUsersTab();
                showAndUpdateUserTab();
                getAuthUser();
                editModal.hide();
            })
            .catch(error => console.log(error));
    }

    //Открытие модальног окна Delete
    function openDeleteModal(id) {
        // Найти пользователя по id
        fetch(`http://localhost:8088/api/v1/users/${id}`)
            .then(response => response.json())
            .then(user => {
                deleteId.value = user.id;
                deleteName.value = user.name;
                deleteAge.value = user.age;
                deleteRole.innerHTML = '';
                for (let i = 0; i < rolesData.length; i++) {
                    let bool = false;
                    user.roles.map(role => {
                        if (role.name === rolesData[i].name) bool = true
                    });
                    deleteRole.append(new Option(rolesData[i].name, rolesData[i].id, false, bool));
                }
                deleteModal.show();
                $("#delete-submit").data("id", id);
            })
            .catch(error => console.error(error));
    }

    //Действие при нажатии Delete в модальном окне
    function deleteUser(id) {
        fetch(`http://localhost:8088/api/v1/users/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                showAndUpdateUsersTab();
                deleteModal.hide();
            })
            .catch(error => console.log(error));
    }

    fetch("http://localhost:8088/api/v1/roles/")
        .then(response => response.json())
        .then(result => {
            rolesData = result;
            for (let i = 0; i < rolesData.length; i++) {
                selectCreate.append(new Option(rolesData[i].name, rolesData[i].id));
                selectEdit.append(new Option(rolesData[i].name, rolesData[i].id));
            }
        })
        .catch(error => console.error(error));

    //таблица users вкладка user_tabs
    function showAndUpdateUsersTab() {
        fetch("http://localhost:8088/api/v1/users/")
            .then(response => response.json())
            .then(result => {
                usersData = result;
                usersTableBody.innerHTML = '';
                for (let i = 0; i < usersData.length; i++) {
                    let trUsersElement = document.createElement("tr")
                    trUsersElement.innerHTML = `
                    <td>${usersData[i].id}</td>
                    <td>${usersData[i].name}</td>
                    <td>${usersData[i].age}</td>
                    <td><p>${usersData[i].roles.map(role => role.name.replaceAll('ROLE_', '')).join(' ')}</p></td>
                    <td><button data-id="${usersData[i].id}" type="button" class="btn btn-primary edit-btn" >Edit</button></td>
                    <td><button data-id="${usersData[i].id}" type="button" class="btn btn-danger delete-btn">Delete</button></td>
`;
                    usersTableBody.append(trUsersElement);
                }
            })
            .catch(error => console.error(error));
    }

    //Вкладка User-Info
    function showAndUpdateUserTab() {
        let trUserElement = document.createElement("tr");
        userTableBody.innerHTML = '';
        fetch("http://localhost:8088/api/v1/users/auth-user")
            .then(response => response.json())
            .then(result => {
                authUser = result;
                let id = authUser.id;
                let name = authUser.name;
                let age = authUser.age;

                trUserElement.innerHTML = `
                    <td>${id}</td>
                    <td>${name}</td>
                    <td>${age}</td>
                    <td><p>${authUser.roles.map(role => role.name.replaceAll('ROLE_', '')).join(' ')}</p></td>
                    `;
                userTableBody.append(trUserElement);
            });
    }
})