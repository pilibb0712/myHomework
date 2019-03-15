
$(document).ready(function () {
    'use strict';

    var $username = $('#username'),
        $currentUserDiv = $('#current-username-div'),
        $changeUserDiv = $('#change-user-div'),
        $anotherUsername = $('#another-username'),
        $anotherPassword = $('#another-password'),
        $changeUserBtn = $('#change-user-btn'),

        $proNameInput = $('#pro-name-input'),  //项目名称
        $projectDescriptionInput = $('#pro-description-textarea'), //项目描述
        $currentLength = $('#current-length'),  //展示项目名称字数的地方

        $rewardChoicesRadio = $(':radio[name="reward-choices"]'),  //选择积分模式的radio按钮

        $rewardAnywayFieldset = $('#reward-anyway-fieldset'),  //不评价就奖励模式
        $rewardAnywayCredit = $('#reward-anyway-credit'),

        $autoEstimateFieldset = $('#auto-estimate-fieldset'),   //自动评价模式
        $rewardAnywayFieldsetLabel = $('#reward-anyway-fieldset-label'),
        $autoEstimateFieldsetLabel = $('.auto-estimate-fieldset-label'),
        $excellentCredit = $('#excellent-credit'),
        $goodCredit = $('#good-credit'),
        $underAverageCredit = $('#under-average-credit'),

        $tagModeFieldset = $('#tag-mode-fieldset'),
        $tagModeFieldsetLabel = $('#tag-mode-fieldset-label'),

        $addTagChoiceBtn = $('#add-tag-choice'),
        $tagChoicesList = $('#tag-choices-list'),
        $tagChoiceContent = $('#tag-choice-content'),

        //警告条们
        $rewardAnywayCreditAlert = $('#reward-anyway-credit-alert'),
        $autoEstimateExcellentAlert = $('#auto-estimate-excellent-alert'),
        $autoEstimateGoodAlert = $('#auto-estimate-good-alert'),
        $autoEstimateUnderaverageAlert = $('#auto-estimate-underaverage-alert'),
        $eachPicMarkerAlert = $('#each-pic-marker-alert'),
        $launchAlert = $('#launch-alert'),

        $eachPicMarker = $('#each-pic-marker'),
        $markModeRadio = $(':radio[name="mark-mode"]'),   //选择标记模式的radio按钮

        $launchBtn = $('#launch-btn'),   //发布项目按钮

        $markRefBtn = $('#mark-ref-btn'),
        $markRef = $('#mark-ref'),
        $refCanvas = $('#ref-canvas'),

        $minSize = $('#minSize'),
        $maxSize = $('#maxSize'),
        $minWorkers = $('#minWorkers'),
        $maxWorkers = $('#maxWorkders'),
        $refBtn = $('#ref-btn'),

        //数据是否有效
        rewardAnywayCreditIsValid = false,
        autoEstimateExcellentIsValid = false,
        autoEstimateGoodIsValid = false,
        autoEstimateUnderaverageIsValid = false,
        eachPicMarkerIsValid = false,

        rewardMode = 'auto-estimate',
        markMode = 'DOTS'
        ;

    /*******************************************************************************************************************/
    /*初始化base*/
    initBase($username, $currentUserDiv, $changeUserDiv, $anotherUsername, $anotherPassword, $changeUserBtn);
    /*********************************************************************************************/
    /*更改fieldset状态*/
    //使fieldset可以使用
    function enableFieldset($fieldset, $fieldsetLabel) {
        $fieldset.attr('disabled', false);
        $fieldsetLabel.removeClass('text-lighter');
    }

    //使fieldset无法使用
    function disableFieldset($fieldset, $fieldsetLabel) {
        $fieldset.attr('disabled', true);
        $fieldsetLabel.addClass('text-lighter');
    }
    /*********************************************************************************************/
    /*监听项目名称输入长度*/
    $proNameInput.bind("propertychange input", function () {

        var len = $(this).val().length;
        $currentLength.text(len);
    });
    /*********************************************************************************************/
    /*监听评价模式radio按钮的变化*/
    /*当选择某一模式时，该模式需填写的信息设为可用，其他模式的信息设为不可用*/
    $rewardChoicesRadio.change(function () {
        rewardMode = $(this).val();
        if (rewardMode === 'reward-anyway'){
            enableFieldset($rewardAnywayFieldset, $rewardAnywayFieldsetLabel);
            disableFieldset($autoEstimateFieldset, $autoEstimateFieldsetLabel);
            $autoEstimateExcellentAlert.fadeOut();
            $autoEstimateGoodAlert.fadeOut();
            $autoEstimateUnderaverageAlert.fadeOut();
        }else if (rewardMode === 'auto-estimate'){
            disableFieldset($rewardAnywayFieldset, $rewardAnywayFieldsetLabel);
            enableFieldset($autoEstimateFieldset, $autoEstimateFieldsetLabel);
            $rewardAnywayCreditAlert.fadeOut();
        }
    });
    /*********************************************************************************************/
    /*监听标注模式radio按钮的变化*/
    $markModeRadio.change(function () {
        markMode = $(this).val();
        console.log(markMode);
        if (markMode === 'TAG'){
            enableFieldset($tagModeFieldset, $tagModeFieldsetLabel);
        }else{
            disableFieldset($tagModeFieldset, $tagModeFieldsetLabel);
        }
    });
    /*********************************************************************************************/
    /*输入规范性监控*/
    $rewardAnywayCredit.blur(function () {
        var val = parseInt($(this).val());
        if (!val && val !== 0){
            $rewardAnywayCreditAlert.fadeIn();
            rewardAnywayCreditIsValid = false;
        }else{
            $rewardAnywayCreditAlert.fadeOut();
            rewardAnywayCreditIsValid = true;
        }
        $launchAlert.slideUp();
    });

    $excellentCredit.blur(function () {
        var val = parseInt($(this).val());
        if (!val && val !== 0){
            $autoEstimateExcellentAlert.fadeIn();
            autoEstimateExcellentIsValid = false;
        }else{
            $autoEstimateExcellentAlert.fadeOut();
            autoEstimateExcellentIsValid = true;
        }
        $launchAlert.slideUp();
    });

    $goodCredit.blur(function () {
        var val = parseInt($(this).val());
        if (!val && val !== 0){
            $autoEstimateGoodAlert.fadeIn();
            autoEstimateGoodIsValid = false;
        }else{
            $autoEstimateGoodAlert.fadeOut();
            autoEstimateGoodIsValid = true;
        }
        $launchAlert.slideUp();
    });

    $underAverageCredit.blur(function () {
        var val = parseInt($(this).val());
        if (!val && val !== 0){
            $autoEstimateUnderaverageAlert.fadeIn();
            autoEstimateUnderaverageIsValid = false;
        }else{
            $autoEstimateUnderaverageAlert.fadeOut();
            autoEstimateUnderaverageIsValid = true;
        }
        $launchAlert.slideUp();
    });

    $eachPicMarker.blur(function () {
        var val = parseInt($(this).val());
        if (!val && val !== 0){
            $eachPicMarkerAlert.fadeIn();
            eachPicMarkerIsValid = false;
        }else{
            $eachPicMarkerAlert.fadeOut();
            eachPicMarkerIsValid = true;
        }
        $launchAlert.slideUp();
    });
    /*********************************************************************************************/
    /*根据*/
    var maxSize = 100;
    var minSize = 0;
    var maxWorkers = 10;
    var minWorkers = 1;

    $markRefBtn.on('click', function () {
        $markRef.slideToggle();
    });

    $.ajax({
        type: 'get',
        url: 'stat/projectNeedTime?maxSize=' + maxSize + '&minSize=' + minSize + '&maxWorkers=' + maxWorkers + '&minWorkers=' + minWorkers,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(data){
            console.log(data);
            var labels = [];
            var min = data.minTime;
            var max = data.maxTim;
            var interval = (max - min) / 10;

            while(min <= max){
                min += interval;
                labels.push(String(min));
            }

            var refCanvas = new Chart($refCanvas, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets:[
                        {
                            label: 'History datas',
                            data: data.numsOfDiffTimes,
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)',
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255,99,132,1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)',
                                'rgba(255,99,132,1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                            ],
                            borderWidth: 1
                        }
                    ]
                }
            });
        }
    });

    $refBtn.on('click', function () {
        maxSize = $maxSize.val();
        minSize = $minSize.val();
        maxWorkers = $maxWorkers.val();
        minWorkers = $minWorkers.val();

        $.ajax({
            type: 'get',
            url: 'stat/projectNeedTime?maxSize=' + maxSize + '&minSize' + minSize + '&maxWorkers=' + maxWorkers + '&minWorkers=' + minWorkers,
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            success: function(data){
                console.log(data);
                var labels = [];
                var min = data.minTime;
                var max = data.maxTim;
                var interval = (max - min) / 10;

                while(min <= max){
                    min += interval;
                    labels.push(String(min));
                }

                var refCanvas = new Chart($refCanvas, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets:[
                            {
                                label: 'History datas',
                                data: data.numsOfDiffTimes
                            }
                        ]
                    }
                });
            }
        });
    });

    /*********************************************************************************************/
    /*添加tag选项按钮监听*/
    $addTagChoiceBtn.on('click', function () {
        var choice = $tagChoiceContent.val();

        if (choice){
            $tagChoicesList.append(
                '<li class="list-group-item">' +
                    choice +
                    '<button class="close" aria-hidden="true">&times;</button>' +
                '</li>'
            );
            
            $('.close').on('click', function () {
                $(this).parent().remove();
            });

            $tagChoiceContent.val('');
        }
    });
    /*********************************************************************************************/
    /*发布项目按钮监听*/
    $launchBtn.on('click', function () {
        var projectName = $proNameInput.val();
        var projectDescription = $projectDescriptionInput.val();

        var eachPicMarkerNum = parseInt($eachPicMarker.val());  //每张图片标注人数

        var markChoices;
        if (markMode === 'TAG'){
            markChoices = [];
            $.each($tagChoicesList.contents(), function (index, content) {
                var choice = content.textContent;
                markChoices.push(choice.substr(0, choice.length - 1));
            });
        }
        console.log(markMode);

        if(projectName !== '' && projectDescription !== '' && eachPicMarkerIsValid){
            if (rewardMode === 'reward-anyway'){
                    if (rewardAnywayCreditIsValid){
                        var rewardAnywayCredit = parseInt($rewardAnywayCredit.val());

                    if (rewardAnywayCredit > 0){
                        var simpleProject =
                            {
                                'projectName': projectName,
                                'description': projectDescription,
                                'launcher':  $.cookie('username'),
                                'projectType': 'SIMPLE',
                                'canBeJoined': true,
                                'eachCredit': rewardAnywayCredit,
                                'upUserLimit': eachPicMarkerNum,
                                'markType': markMode,
                                'tags': markChoices
                            };

                        $.ajax({
                            data: JSON.stringify(simpleProject),
                            type: 'post',
                            url: '/launcher/launch/simple?username=' + $.cookie('username'),
                            contentType: 'application/json; charset=utf-8',
                            dataType: 'text',
                            success: function(projectId){
                                window.location = '/launcherproject?proId=' + projectId;
                            }
                        });
                    }else {

                    }
                }else{
                    $launchAlert.slideDown();
                }
            }else if (rewardMode === 'auto-estimate'){
                var credits = [$excellentCredit.val(), $goodCredit.val(), $underAverageCredit.val()];

                var multiProject =
                    {
                        'projectName': projectName,
                        'description': projectDescription,
                        'launcher':  $.cookie('username'),
                        'projectType': 'MULTI',
                        'canBeJoined': true,
                        'upUserLimit': eachPicMarkerNum,
                        'markType': markMode,
                        'credits': credits,
                        'tags': markChoices
                    };

                $.ajax({
                    data: JSON.stringify(multiProject),
                    type: 'post',
                    url: '/launcher/launch/multi?username=' + $.cookie('username'),
                    contentType: 'application/json; charset=utf-8',
                    dataType: 'text',
                    success: function(projectId){
                        window.location = '/launcherproject?proId=' + projectId;
                    }
                });
            }
        }else{
            $launchAlert.slideDown();
        }
    });
});