$(function(){
    var click = false;
    var method;
    var cropBox = $('#cropBox');
    var cutBox = $('#cutBox');
    var rightdown = $('#rightdown');
    var crop={};
    var cut={};
    var maxX;
    var maxY;
    var mx;
    var my;
    var opaque = $('#opaque').hide()[0];
    var ctx=opaque.getContext("2d"); 
    $('#opaque').hide()

    $('#photo').change(function () {
        fileViewer($('#photo')[0].files[0], $('#photoUpdateShow'),cropSize);
    });
    
    $('#photoUpdata').click(memberphotoUpdata);

    function cropSize(div){
        var canvas = div.find('canvas')[0];
        console.log(div.html())
        cropBox.height(canvas.height).width(canvas.width);
        cutBox.show();
        cutBox.css('top',0).css('left',0).height(150).width(150);
        opaque.width = canvas.width ;
        opaque.height =canvas.height; 
        $('#opaque').show();
        opaqueSize(0,0,152,152);
    }

    function opaqueSize(top,left,side){
        ctx.clearRect(0,0,cropBox.width(),cropBox.height()); 
        ctx.globalAlpha=0.7;
        ctx.fillStyle="black";   
        ctx.fillRect(0,0,cropBox.width(),cropBox.height());
        ctx.clearRect(top,left,side,side); 
    }

    cutBox.mousedown(function(e){
        e.stopPropagation();
        click = true;
        maxX =cropBox.width()-cutBox.width();
        maxY =cropBox.height()-cutBox.height();
        mx = e.clientX;
        my = e.clientY;
        crop.h = cropBox.height();
        crop.w = cropBox.width();
        method='moveBox';
    })

    rightdown.mousedown(function(e){
        e.stopPropagation();
        click = true;
        mx = e.clientX;
        my = e.clientY;
        crop.h = cropBox.height();
        crop.w = cropBox.width();
        cut.w = cutBox.width();
        cut.h = cutBox.height();
        method='area';
    })
    
    $(document).mouseup(function(){
        click = false;
        method ='';
    })

    $('.modal-body').mousemove(function(e){
        e.stopPropagation();
        if(click){
            if(e.which == 0){
                click = false
                method=''; 
            }
            switch(method){
                case 'moveBox':
                    moveBox(e);
                    break;
                case 'area':
                    area(e);
                    break;
            }
        }
    })

    function area(e){
        var m={} ;
        m.x = e.clientX-mx+cut.w;
        m.y = e.clientY-my+cut.h;
        m.x = m.x > 0? m.x:0;
        m.y = m.y > 0? m.y:0;
        var side = m.x > m.y? m.y:m.x;
        var pos = cutBox.position(); 
        if(pos.top+side > crop.h || pos.left+side > crop.w){
            var h = crop.h-pos.top;
            var w = crop.w-pos.left
            side = h > w ? w:h;
        }
        cutBox.css('width', side+'px');
        cutBox.css('height',side+'px');
        opaqueSize(pos.left,pos.top,side,side);

    }
    function moveBox(e){
        var m={} ;
        var pos = cutBox.position(); 
        m.x = e.clientX-mx+pos.left;
        m.y = e.clientY-my+pos.top;
        
        if(m.x> maxX){
            m.x = maxX;
        }else if(m.x <0){
           m.x = 0;
        }
        cutBox.css('left',m.x+'px');
        if(m.y> maxY){
            m.y = maxY;
        }else if(m.y <0){
            m.y=0;
        }
        cutBox.css('top',m.y+'px');
        opaqueSize(m.x,m.y,cutBox.height()+2);
        mx = e.clientX;
        my = e.clientY;
    }

    function memberphotoUpdata() {
        var file = $('#photo')[0].files[0];

        if (file != null){
            var pos = cutBox.position(); 
            fileResizetoFile(file, 0.6,pos.top,pos.left,cutBox.height(), function (file) {
                photoUpdate('/wegether/member/Info/photo', file,
                    function (data) {
                        var photoSrc = data["photoSrc"];
                        if (photoSrc != null) {
                        	window.parent.location.reload() 
//                            $('#photoShow').html($('<img/>').attr('src', photoSrc));
                        }
                    })
            })
        }
    }
})

