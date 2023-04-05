$(document).ready(function () {

    let authUser;
    const userTableBody = document.getElementById("tbody-user");

    getAuthUser();
    showUserInfo();

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

    function showUserInfo() {
        fetch("http://localhost:8088/api/v1/users/auth-user")
            .then(response => response.json())
            .then(result => {
                authUser = result;
                userTableBody.innerHTML = '';
                let trUserElement = document.createElement("tr")
                trUserElement.innerHTML = `
                    <td>${authUser.id}</td>
                    <td>${authUser.name}</td>
                    <td>${authUser.age}</td>
                    <td><p>${authUser.roles.map(role => role.name.replaceAll('ROLE_', '')).join(' ')}</p></td>
                 
`;
                userTableBody.append(trUserElement);
            })
            .catch(error => console.error(error));
    }
});