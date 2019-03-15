
$(document).ready(function () {
    var $username = $('#username'),
        $currentUserDiv = $('#current-username-div'),
        $changeUserDiv = $('#change-user-div'),
        $anotherUsername = $('#another-username'),
        $anotherPassword = $('#another-password'),
        $changeUserBtn = $('#change-user-btn'),
        $qualityCanvas = $('#quality-canvas'),
        $compareUserBtn = $('#compare-user-btn'),
        $userToCompare = $('#user-to-compare'),
        $compareError = $('#compare-error'),

        thisUserData
    ;

    /*******************************************************************************************************************/
    /*初始化base*/
    initBase($username, $currentUserDiv, $changeUserDiv, $anotherUsername, $anotherPassword, $changeUserBtn);
    /*******************************************************************************************************************/
    /**/

    $.ajax({
        type: 'get',
        url: 'stat/userAccRadar?username=' + $.cookie('username'),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(data){
            thisUserData = [data[0].acc, data[1].acc, data[2].acc];

            var qualityChart = new Chart($qualityCanvas, {
                type: 'radar',
                data: {
                    labels: ['Tag', 'Single rectangle', 'Multi rectangle'],
                    datasets: [
                        {
                            label: $.cookie('username'),
                            data: thisUserData,
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255,99,132,1)'
                            ],
                            borderWidth: 1
                        }
                    ]
                }
            });
        }
    });

    $userToCompare.bind('propertychange input', function () {
        $compareError.fadeOut();
    });

    $compareUserBtn.on('click', function () {
        var toCompare = $userToCompare.val();

        $.ajax({
            type: 'get',
            url: 'stat/userAccRadar?username=' + toCompare,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(data){
                if(data){
                    var qualityChart = new Chart($qualityCanvas, {
                        type: 'radar',
                        data: {
                            labels: ['Tag', 'Single rectangle', 'Multi rectangle'],
                            datasets: [
                                {
                                    label: $.cookie('username'),
                                    data: thisUserData,
                                    backgroundColor: [
                                        'rgba(255, 99, 132, 0.2)'
                                    ],
                                    borderColor: [
                                        'rgba(255,99,132,1)'
                                    ],
                                    borderWidth: 1
                                },
                                {
                                    label: toCompare,
                                    data: [data[0].acc, data[1].acc, data[2].acc],
                                    backgroundColor: [
                                        'rgba(54, 162, 235, 0.2)'
                                    ],
                                    borderColor: [
                                        'rgba(54, 162, 235, 1)'
                                    ],
                                    borderWidth: 1
                                }
                            ]
                        }
                    });
                }else{
                    $compareError.fadeIn();
                }
            }
        });
    });
});