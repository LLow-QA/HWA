'use strict';

const email = sessionStorage.getItem('currentLogin');

const areYouLogged = () => {

    if (email == undefined){
        alert('Please log in to access your account settings.');
        location.replace("Login.html");
    }
    else{
        location.replace("myaccount.html");
    }


}