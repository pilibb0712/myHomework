
$(document).ready(function () {
    'use strict';

    var $totalUserNum = $('#total-user-num'),
        $creditList = $('#credit-list'),
        $userRegistrationCanvas = $('#user-registration-canvas'),

        $doingProjectsCanvas = $('#doing-projects-canvas'),
        $doneProjectsCanvas = $('#done-projects-canvas'),

        $datePickBtn = $('#date-pick-btn'),
        $startDate = $('#start-date'),
        $endDate = $('#end-date'),
        $datepicker = $('#datepicker'),

        defaultStartDate = '2018-06-07',
        defaultEndDate = '2018-07-07'
    ;

    function getAllDays(start, end) {
        var starts = start.split('-');
        var ends = end.split('-');

        starts[0] = parseInt(starts[0]);
        starts[1] = parseInt(starts[1]);
        starts[2] = parseInt(starts[2]);

        ends[0] = parseInt(ends[0]);
        ends[1] = parseInt(ends[1]);
        ends[2] = parseInt(ends[2]);

        var result = [start];

        while(starts[0] !== ends[0] || starts[1] !== ends[1] || starts[2] !== ends[2]){

            starts[2]++;

            if(starts[1] === 2){
                if(starts[2] > 28){
                    starts[2] = 1;
                    starts[1] ++;
                }
            }else if(starts[1] === 1 || starts[1] === 3 || starts[1] === 5 || starts[1] === 7 || starts[1] === 8
             || starts[1] === 10 ||starts[1] === 12){
                if(starts[2] > 31){
                    starts[2] = 1;
                    starts[1] ++;
                }
            }else{
                if(starts[2] > 30){
                    starts[2] = 1;
                    starts[1] ++;
                }
            }

            if(starts[1] > 12){
                starts[1] = 1;
                starts[0] ++;
            }

            var next = String(starts[0]);
            if(starts[1] < 10){
                next = next + '-0' + starts[1];
            }else{
                next = next + '-' + starts[1];
            }

            if(starts[2] < 10){
                next = next + '-0' + starts[2];
            }else{
                next = next + '-' + starts[2];
            }

            result.push(next);
        }

        return result;
    }

    /**************************************************************************************/
    /*用户数量*/
    $.ajax({
        type: 'get',
        url: '/admin/userNum',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(userNum){
            $totalUserNum.text(userNum);
        }
    });
    /**************************************************************************************/
    /*用户注册情况*/
    $datepicker.datepicker({
        toggleActive: true,
        format: 'yyyy-mm-dd'
    });

    var days = getAllDays(defaultStartDate, defaultEndDate);

    $.ajax({
        type: 'get',
        url: '/admin/listRegisterNum?beginTime=' + defaultStartDate + '&endTime='+ defaultEndDate,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(data){
            var userRegistrationChart = new Chart($userRegistrationCanvas, {
                type: 'line',
                data: {
                    labels: days,
                    datasets: [
                        {
                            label: 'User registration',
                            data: data,
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


    $datePickBtn.on('click', function () {
        var start = $startDate.val();
        var end = $endDate.val();

        var newDays = getAllDays(start, end);
        $.ajax({
            type: 'get',
            url: '/admin/listRegisterNum?beginTime=' + defaultStartDate + '&endTime='+ defaultEndDate,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(data){
                var userRegistrationChart = new Chart($userRegistrationCanvas, {
                    type: 'line',
                    data: {
                        labels: newDays,
                        datasets: [
                            {
                                label: 'User registration',
                                data: data,
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
    });
    /**************************************************************************************/
    /*项目分类*/
    $.ajax({
        type: 'get',
        url: '/admin/getPercentageOfMarkType',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(info){
            console.log(info);
            var doingChart = new Chart($doingProjectsCanvas, {
                type: 'pie',
                data: {
                    labels: ['Tag', 'Single rectangle', 'Multi rectangle', 'Dot'],
                    datasets: [
                        {
                            label: 'Done Projects',
                            data: info[1],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255,99,132,1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)'
                            ],
                            borderWidth: 1
                        }
                    ]
                }
            });

            var doneChart = new Chart($doneProjectsCanvas, {
                type: 'pie',
                data: {
                    labels: ['Tag', 'Single rectangle', 'Multi rectangle', 'Dot'],
                    datasets: [
                        {
                            label: 'Doing Projects',
                            data: info[0],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255,99,132,1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)'
                            ],
                            borderWidth: 1
                        }
                    ]
                }
            });
        }//关闭回调函数
    });

    /**************************************************************************************/
    /*项目积分情况*/
    $.ajax({
        type: 'get',
        url: '/admin/projectStatistic',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(info){

            $.each(info.credits, function (index, content) {
                $creditList.append(
                    '<tr>' +
                        '<td>' + content.projectId + '</td>' +
                        '<td>' + content.credit + '</td>' +
                        '<td><button class="btn btn-default close-btn" id="' + content.projectId + '">Close</button></td>' +
                    '</tr>'
                );

                $('.close-btn').on('click', function () {
                    $.ajax({
                        type: 'get',

                        url: '/admin/closeProject?projectId=' + $(this).attr('id'),
                        contentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        success: function(isClosed){
                            if (isClosed){
                                $(this).remove();
                            }else{
                                alert('Server error');
                            }
                        }
                    });
                });
            });

        }//关闭回调函数
    });

});