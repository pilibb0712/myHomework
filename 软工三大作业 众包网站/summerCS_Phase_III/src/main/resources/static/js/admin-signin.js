
$(document).ready(function () {
    'use strict';

    var $adminSignIn = $('#admin-sign-in'),
        $username = $('#admin-name'),
        $password = $('#admin-password'),
        $alert = $('#alert')
    ;

    $adminSignIn.on('click', function () {
        var username = $username.val();
        var password = $password.val();

        if(username !== 'root'){
            $alert.slideDown();
        }else{
            $.ajax({
                type: 'get',
                url: '/admin/login?password=' + password,
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function(isLogedIn){
                    if (isLogedIn){
                        window.location = '/admin-homepage';
                    }else {
                        $alert.slideDown();
                    }
                }
            });
        }


    });
});