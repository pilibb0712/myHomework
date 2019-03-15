

$(document).ready(function () {
    'use strict';

    var $username = $('#username'),
        $currentUserDiv = $('#current-username-div'),
        $changeUserDiv = $('#change-user-div'),
        $anotherUsername = $('#another-username'),
        $anotherPassword = $('#another-password'),
        $changeUserBtn = $('#change-user-btn'),

        $projectName = $('#project-name'), //项目名称
        $projectDescription = $('#project-description'), //项目描述

        $taskBtn = $('#task-btn'),

        $submitNowBtn = $('#submit-now-btn'),  //提交当前这些图片
        $submitNowModal = $('#submit-now-modal'),

        $claimMoreBtn = $('#claim-more-btn'),   //认领更多图片
        $claimNumberText = $('#claim-number-text'),
        $claimNumberError = $('#claim-number-error'),
        $claimMoreModal = $('#claim-more-modal'),

        $imageLeft = $('.image-left'),
        $imageLeftFirst = $('.image-left:first'),

        $startWorkBtn = $('#start-work-btn'),

        $projectPic = $('#project-pic'),  //项目图片
        $previousPicBtn = $('#pic-previous'),
        $nextPicBtn = $('#pic-next'),
        $currentPicPointer = $('#current-pic-pointer'),

        $currentCreditsCanvas = $('#current-credits-canvas'), //此项目当前积分

        $myRanking = $('#my-ranking'),   //积分排名
        $rankingList = $('#ranking-list'),
        $previousRankingBtn = $('#ranking-previous'),
        $nextRankingBtn = $('#ranking-next'),
        $currentRankingPointer = $('#current-ranking-pointer'),

        $imgNum = $('#img-num'),

        projectId = $projectName.attr('name'),

        $creditsRatioCanvas = $('#credits-ratio-polar'),
        
        $searchRankingText = $('#search-ranking-text'),
        $searchRankingBtn = $('#search-ranking-btn'),
        $searchedRanking = $('#searched-ranking'),

        eachRowPicNum = 6,   //一行上的图片数量
        eachPageRowNum = 3,  //一页上的行数
        eachPageRankingNum = 15, //一页上的排名数

        ranking = {
            'all-users': '300',
            'my-ranking': '9'
        }
    ;

    /***********************************************************************************************/
    /*初始化base*/
    initBase($username, $currentUserDiv, $changeUserDiv, $anotherUsername, $anotherPassword, $changeUserBtn);
    console.log(projectId);
    /***********************************************************************************************/
    /*图片翻页*/
    picDuoPagination($previousPicBtn, $nextPicBtn, $projectPic, '/worker/images',
        eachPageRowNum, eachRowPicNum, $currentPicPointer, $.cookie('username'), projectId);
    /*************************************************************************************************/
    /*积分排名翻页*/
    rankingDuoPagination($previousRankingBtn, $nextRankingBtn, $rankingList, '/rank/list',
        eachPageRankingNum, $currentRankingPointer, $.cookie('username'), projectId);
    /*************************************************************************************************/
    /*提交或继续认领图片*/
    $claimNumberText.bind('propertychange input', function () {
        //用户编辑认领数量，错误提示就收起
        $claimNumberError.slideUp();
    });

    $taskBtn.on('click', function () {
        if ($taskBtn.text() === 'Claim more'){
            $.ajax({
                type: 'get',
                url: '/proj/imageleft?username=' + $.cookie('username') + '&projectId=' + projectId,
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function(num){
                    $imageLeft.text(num);
                }
            });
        }
    });

    $claimMoreBtn.on('click', function () {
        var claimNumber = $claimNumberText.val();

        var isValid = String(parseInt(claimNumber)) === claimNumber;

        var imageLeft = parseInt($imageLeftFirst.text());

        if (isValid && claimNumber <= imageLeft && claimNumber > 0) {

            $.ajax({
                type: 'get',
                url: '/worker/ask?username=' + $.cookie('username') + '&projectId=' + projectId + '&claimNumber=' + claimNumber,
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function(isClaimed){
                    if(isClaimed){
                        $claimMoreModal.modal('hide');
                        $taskBtn.attr('data-target', '#submit-now-modal');
                        $taskBtn.text('Submit now');

                        window.location = '/workerproject?proId=' + projectId;
                    }else{
                        $claimNumberError.html('Server error').slideDown();
                    }
                }
            });

        } else {
            $claimNumberError.slideDown();
        }

    });

    $submitNowBtn.on('click', function () {
        $submitNowModal.modal('hide');

        $.ajax({
            type: 'get',
            url: '/worker/closeBatch?username=' + $.cookie('username') + '&projectId=' + projectId,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function(isSubmitted){
                //if(isSubmitted){
                    $taskBtn.attr('data-target', '#claim-more-modal');
                    $taskBtn.text('Claim more');

                    window.location = '/workerproject?proId=' + projectId;
                //}
            }
        });
    });
    /*************************************************************************************************/
    /*得分比*/
    $.ajax({
        type: 'get',
        url: 'stat/userCreditsInProjPolar?username=' + $.cookie('username') + '&projectId=' + projectId,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(data){
            var polar = new Chart($creditsRatioCanvas, {
                type: 'polarArea',
                data: {
                    labels: ['Excellent:'+data[0].credit, 'Good:'+data[1].credit, 'Under average:'+data[2].credit, 'Negative:'+data[3].credit],
                    datasets: [
                        {
                            data: [data[0].count, data[1].count, data[2].count, data[3].count],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)'
                            ]
                        }
                    ]
                }
            });
        }
    });
    /*************************************************************************************************/
    /*搜索排名*/
    $searchRankingBtn.on('click', function () {
        
    });
    /*************************************************************************************************/
    /*向后端获取信息并初始化项目信息*/
    $.ajax({
        type: 'get',
        url: '/worker/proj/projInfo?username=' + $.cookie('username') + '&projectId=' + projectId,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(projectInfo){
            $projectName.html(projectInfo.projectName);   //项目名
            $projectDescription.html(projectInfo.description);    //项目描述

            $imgNum.attr('value', projectInfo.totalNumOfImgs);

            //项目信息加载完后允许用户开始工作
            $startWorkBtn.on('click', function () {
                window.open('/markPicture?proId=' + projectId + '&markType=' + projectInfo.markType);
            });

            if (projectInfo.canAskMore){
                $taskBtn.text('Claim more').attr('data-target', '#claim-more-modal');
            }else{
                $taskBtn.text('Submit now').attr('data-target', '#submit-now-modal');
            }
        }
    });

    $.ajax({  //当前获得的积分
        type: 'get',
        url: '/worker/proj/credit?username=' + $.cookie('username') + '&projectId=' + projectId,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(myCredit){
            $currentCreditsCanvas.drawImage({
                layer: true,
                name: 'background',
                source: '/img/project-credit-bg.png',
                x: 0,
                y: 0,
                fromCenter: false
            }).drawText({
                layer: true,
                name: 'credit',
                x: 900,
                y: 80,
                text: myCredit,
                fillStyle: 'white',
                fontSize: 100
            });
        }
    });

    $previousPicBtn.click(); //显示项目图片

    $myRanking.html(ranking["my-ranking"]);  //该用户的积分排名

    $previousRankingBtn.click(); //积分排名列表

    /*************************************************************************************************/
});