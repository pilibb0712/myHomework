
$(document).ready(function () {
    var $picThumbsGrid = $('#pic-thumbs'),
        $picModal = $('#pic-modal'),
        $canvas = $('#canvas'),

        projectId = $('#page-title').attr('name'),

        imageRoot = '/data/ImageFlow/',

        images
    ;


    var test = [
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg',
        'human3.jpg', 'human4.jpg', 'human5.jpg', 'human6.jpg', 'human7.jpg','human3.jpg', 'human4.jpg', 'human5.jpg'
    ];

    function getDimension(src) {
        var img = new Image();
        img.src = src;

        return {
            width: img.width,
            height: img.height
        }
    }

    $.ajax({
        type: 'get',
        url: '/launcher/listMarkedImgs?projectId=' + projectId,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function(imgs){
            images = imgs;
        }
    });

    console.log(images);

    if (images){
        var count = 0;
        $.each(images, function (index, content) {
            var mod = count % 4;

            switch (mod){
                case 0:
                    $picThumbsGrid.append(
                        '<div class="row">' +
                            '<div class="col-md-3">' +
                                '<img id="' + index +'" src="' + imageRoot + content.fileName + '" class="img-responsive">' +
                            '</div>'
                    );
                    break;
                case 3:
                    $picThumbsGrid.append(
                            '<div class="col-md-3">' +
                                '<img id="' + index +'" src="' + imageRoot + content.fileName + '" class="img-responsive">' +
                            '</div>' +
                        '</div>'
                    );
                    break;
                default:
                    $picThumbsGrid.append(
                        '<div class="col-md-3">' +
                            '<img id="' + index +'" src="' + imageRoot + content.fileName + '" class="img-responsive">' +
                        '</div>'
                    );
                    break;
            }

            count ++;
        });

        $('img').on('click', function () {
            var $pic = $(this);
            var index = $pic.attr('id');
            var src = $pic.attr('src');
            var mark = images[index].mark;

            $picModal.modal();

            var dimension = getDimension(src);
            $canvas.attr('width', dimension.width);
            $canvas.attr('height', dimension.height);

            $canvas.drawImage({
                source: src,
                layer: true,
                x: 0,
                y: 0,
                fromCenter: false
            });

            if (mark){
                if (mark.singleRecMark){
                    //如果单框模式存在
                    $canvas.drawRect({
                        layer: true,
                        x: mark.singleRecMark.startDot.x,
                        y: mark.singleRecMark.startDot.y,
                        width: mark.singleRecMark.width,
                        height: mark.singleRecMark.height,
                        strokeStyle: 'red',
                        strokeWidth: 4,
                        fromCenter: false,
                        draggable: false
                    });
                }

                if (mark.recGroupMark){
                    //多框模式
                    $.each(mark.recGroupMark.rectangleMarks, function (positionIndex, positionContent) {
                        //遍历每个多框标记中框的位置数组

                        $canvas.drawRect({
                            layer: true,
                            x: positionContent.startDot.x,
                            y: positionContent.startDot.y,
                            width: positionContent.width,
                            height: positionContent.height,
                            strokeStyle: 'red',
                            strokeWidth: 4,
                            fromCenter: false,
                            draggable: false
                        });
                    });
                }
            }

        });

    }

});