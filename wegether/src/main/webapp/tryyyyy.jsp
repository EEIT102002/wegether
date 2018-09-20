<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="js/jquery-2.1.4.min.js"></script>
    <style>
        .btn {
            border-radius: 0px;
            font-weight: 100;
            cursor: pointer;
            display: inline-block;
            padding: 5px;
            font-size: 14px;
            font-family: '微软雅黑'
        }
        .btn-primary {
            color: #fff;
            text-shadow: 0 1px rgba(0,0,0,.1);
            background-image: -webkit-linear-gradient(top,#4d90fe 0,#4787ed 100%);
            background-image: -o-linear-gradient(top,#4d90fe 0,#4787ed 100%);
            background-image: -webkit-gradient(linear,left top,left bottom,from(#4d90fe),to(#4787ed));
            background-image: linear-gradient(to bottom,#4d90fe 0,#4787ed 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff4d90fe', endColorstr='#ff4787ed', GradientType=0);
            filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
            background-repeat: repeat-x;
            border: 1px solid #3079ed;
        }
        .btn-success {
            color: #fff;
            text-shadow: 0 1px rgba(0,0,0,.1);
            background-image: -webkit-linear-gradient(top,#35aa47 0,#35aa47 100%);
            background-image: -o-linear-gradient(top,#35aa47 0,#35aa47 100%);
            background-image: -webkit-gradient(linear,left top,left bottom,from(#35aa47),to(#35aa47));
            background-image: linear-gradient(to bottom,#35aa47 0,#35aa47 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff35aa47', endColorstr='#ff35aa47', GradientType=0);
            filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
            background-repeat: repeat-x;
            border: 1px solid #359947;
        }
         .gallery .img-item{
              position: relative;
          }
          .gallery .img-item .delete{
              position: absolute;
              display: block;
              width: 20px;
              height:20px;
              color: #fff;
              background: rgba(0,0,0,0.7);
              line-height: 20px;
              text-align: center;
              border-radius: 50%;
              top: 25px;
              right: 25px;
              cursor: pointer;
          }
          .img{
            width: 300px;
            margin: 20px;
          }
    </style>
</head>
<body>
<div>
    <form action="" id="formdata">
        <div id="upload" class="btn btn-primary">选择图片</div>
        <div class="btn btn-success" id="uploadImg">上传</div>
        <input id="file" type="file" multiple style="display:none">
        <div class="gallery" id="gallery"></div>
    </form>
</div>
<script>
    var files=[];
    var that = this;
    $("#upload").click(function(){
        $("#file").trigger("click");
    })

    $("#file").change(function(){
        document.getElementById("gallery").innerHTML="";
        var img=document.getElementById("file").files; 
        var div=document.createElement("div");
        for(var i=0;i<img.length;i++){
            var file=img[i]; var url=URL.createObjectURL(file); 
            var box=document.createElement("img"); 
            box.setAttribute("src",url); 
            box.className='img';

            var imgBox=document.createElement("div");
            imgBox.style.display='inline-block';
            imgBox.className='img-item';

            var deleteIcon = document.createElement("span");
            deleteIcon.className = 'delete';
            deleteIcon.innerText = 'x';
            deleteIcon.dataset.filename = img[i].name;
            imgBox.appendChild(deleteIcon);

            imgBox.appendChild(box);
            var body=document.getElementsByClassName("gallery")[0]; 
            body.appendChild(imgBox);

            that.files = img;
            $(deleteIcon).click(function () {
                var filename = $(this).data("filename");
                $(this).parent().remove();
                var fileList = Array.from(that.files);

                for(var j=0;j<fileList.length;j++){
                    if(fileList[j].name = filename){
                        fileList.splice(j,1);
                        break;
                    }
                }
                that.files = fileList
            })
        }
    })
    $("#uploadImg").click(function(){
                var files = that.files;
                var uploadFile = new FormData($("#formdata")[0]);
                for(var i=0;i<files.length;i++){
                    uploadFile.append('imgs[]',files[i]);
                }
                if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){
                    $.ajax({
                        url:'index.php',
                        type:'POST',
                        data:uploadFile,
                        async: false,
                        cache: false,
                        contentType: false, //不设置内容类型
                        processData: false, //不处理数据
                        success:function(data){

                        },
                        error:function(){
                            alert("上传失败！");
                        }
                    })
                }else {

                }
            })


    </script>
</body>
</html>