function urltoImage(url, fn) {
    var img = new Image();
    img.src = url;
    img.onload = function () {
        fn(img);
    }
};

function imagetoCanvas(image) {
    var cvs = document.createElement("canvas");
    var ctx = cvs.getContext('2d');
    var width = image.width;
    var height = image.height;
    var qrt = width < height ? width/300 : height/300;
    cvs.width = width/qrt;
    cvs.height = height/qrt;
    ctx.drawImage(image, 0, 0,width,height,0,0, width/qrt, height/qrt);
    return cvs;
};


function imagetoCanvasCut(image,top,left,side) {
    var cvs = document.createElement("canvas");
    var ctx = cvs.getContext('2d');
    var width = image.width;
    var height = image.height;
    var qrt = width < height ? width/300 : height/300;
    cvs.width = side;
    cvs.height = side;
    ctx.drawImage(image, left*qrt, top*qrt,side*qrt,side*qrt,0,0,side,side);
    return cvs;
};

function canvasResizetoFile(canvas, quality, fn) {
    canvas.toBlob(function (blob) {
        fn(blob);
    }, 'image/jpeg', quality);
};

function filetoDataURL(file, fn) {
    var reader = new FileReader();
    reader.onloadend = function (e) {
        fn(e.target.result);
    };
    reader.readAsDataURL(file);
};

function dataURLtoImage(dataurl, fn) {
    var img = new Image();
    img.onload = function () {
        fn(img);
    };
    img.src = dataurl;
};

function fileResizetoFile(file, quality,top,left,side, fn) {
    filetoDataURL(file, function (dataurl) {
        dataURLtoImage(dataurl, function (image) {
            canvasResizetoFile(imagetoCanvasCut(image,top,left,side), quality, fn);
        })
    })
}

function fileViewer(file,div,fn) {
    //1. 建立FileReader物件
    var rd = new FileReader();
    //2. 使用readAsDataURL方法，讀取檔案內容
    //3.onload資料讀取成功完成時觸發
    rd.onload = function (e) {
    //4. 將檔案內容暫存
        var img = e.target.result;
        dataURLtoImage(img, function (image) {
            div.html(imagetoCanvas(image));
            fn(div);
        })
    };
    rd.readAsDataURL(file);
}


function photoUpdate(url,file, fn) {//上傳到server
    var data = new FormData();
    data.append('photo', file);
    $.ajax({
        url: url,
        data: data,// Add as Data the Previously create formData
        type: "POST",
        contentType: false,
        processData: false,
        cache: false,
        dataType: "json", // Change this according to your response from the server.
        error: function (err) {
            console.error(err);
        },
        success: function (data) {
            fn(data);
        },
        complete: function () {
            console.log("Request finished.");
        }
    });
}
