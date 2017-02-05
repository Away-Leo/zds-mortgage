define(function (require, exports, module) {
    var $ = require('jquery');
    var Loading = require('zd/jquery.zds.loading');
    require('zd/jquery.zds.button');
    require('zd/drag');


    //内部构造方法
    function ZWindow() {
        this.name = "ZWindow";
        this.version = "1.0.0";
    }

    //定义基本对象
    $.ZWindow = new ZWindow();

    // 插件的定义
    $.fn.ZWindow = function (options) {
        //options如果为string，表示是方法调用
        if (typeof options == 'string') {
            var method = $.fn.ZWindow.methods[options];
            if (method) {
                return method(this);
            }
        }
        //获取合并options
        options = options || {};
        var _opts = this.data("options");
        if (_opts) {
            options = $.extend({}, _opts, options);
        }
        var opts = $.extend({}, $.fn.ZWindow.defaults, options);

        //是否新创建
        if (_opts && (!_opts.createNew)) {
            $(this).data("options", _opts);
            $(opts.target).css("width", _opts.width);
            $(opts.target).css("height", _opts.height);
            $(opts.target).find(".zd-window-title span").html(_opts.title);
            //$(opts.target).find(".zd-window-body").css("height",(_opts.height-35));
            if (!opts.closed) {
                $.fn.ZWindow.methods.open($(this));
                return;
            } else {
                return;
            }
        }

        this.each(function () {
            var html = [],//弹出框的html
                width = $(window).width(),//可视区域宽度
                height = $(window).height(),//可视区域高度
                left = (width - opts.width) / 2,//弹出框的left值
                top = (height - opts.height) / 2,//弹出框的top值
                window_width = opts.width,//弹出框的宽度
                window_height = opts.height,//弹出框的高度
                window_id = "ZUIWIN" + Math.floor(Math.random() * 99999 + 1),//弹出框的id属性值
                display = 'none', //弹出框的display属性值
                zindex = 0, //弹出框与遮罩层的z-index属性值
                body = $('body');//body对象

            if (left < 0) {
                left = 10;
            }
            if (top < 0) {
                top = 10;
            }
            if (opts.width > width) {
                window_width = width - 20;
            }
            if (opts.height > height) {
                window_height = height - 20;
            }

            //closed的值为false，打开弹出框
            if (!opts.closed) {
                display = 'block';
                //设置遮罩层的z-index的值
                //z-index的值，要比已打开的弹出框的值高，让它显示在最前面
                $("div[class='zd-window']:visible").each(function () {
                    if (zindex < $(this).css("z-index")) {
                        zindex = $(this).css("z-index");
                    }
                });
                //创建遮罩层
                opts.loading = Loading.show(null, false, ++zindex);

                //设置弹出框的z-index值
                if (zindex == 0) {
                    zindex = 9000;
                } else {
                    zindex += 2;
                }
                html.push('<div  tabindex="-1"  id="' + window_id
                    + '" class="zd-window" style="width: ' + window_width + 'px;height: ' + window_height
                    + 'px;top:' + top + 'px;left:' + left + 'px;display:' + display
                    + ' z-index:' + (zindex) + ';" >');
            } else {
                html.push('<div  tabindex="-1"   id="' + window_id
                    + '" class="zd-window"  style="width: ' + window_width + 'px;height: ' + window_height
                    + 'px;top:' + top + 'px;left:' + left + 'px;display:' + display + '" >');
            }

            html.push($(this).html());
            html.push('</div>');
            body.append('<div>' + html.join("") + '</div>');

            $(this).html("");
            opts.divTarget = $(this);
            opts.target = body.children("div:last-child").find(".zd-window");
            opts.isInit = true;
            opts.windowId = window_id;
            $(this).data("options", opts);
        });
    };
    $.fn.ZWindow.defaults = {
        width: 400,//宽度
        height: 150,//高度
        //title: "",//标题
        isDrag: true,//是否可以拖拽移动
        closed: false,//是否关闭
        isInit: false,//是否初始化
        isIframe: true,//是否是在iframe中创建
        bodyHtml: null,//内容
        createNew: false,//是否新建
        target: null,
        divTarget: null,
        onClose: function () {

        },
        onOpen: function () {

        }

    };
    $.fn.ZWindow.methods = {
        /**
         * 打开弹出框
         * @param jq
         * */
        open: function (jq) {
            var opts = $(jq).data("options");
            if (opts) {
                var width = $(window).width(),//可视区域宽度
                    height = $(window).height(),//可视区域高度
                    left = (width - opts.width) / 2,//弹出框的left属性值
                    top = (height - opts.height) / 2,//弹出框的top属性值
                    zindex = 0;//弹出框的z-index属性值

                if (opts.isIframe) {
                    left = left - 90;
                    top = top - 60;
                }

                //设置弹出框的宽高以及主体内容的高度
                if (opts.width > width) {
                    opts.width = width - 20;
                }
                if (opts.height > height) {
                    opts.height = height - 20;
                }
                opts.target.css("width", opts.width);
                opts.target.css("height", opts.height);
                opts.target.find(".dialog-content").css("height", opts.height - 100);
                //设置弹出框的偏移量

                if (left < 0) {
                    left = 10;
                }
                if (top < 0) {
                    top = 10;
                }
                opts.target.css("top", top);
                opts.target.css("left", left);


                //拖拽效果
                if (opts.isDrag) {
                    if (opts.target.find(".dialog-title")) {
                        opts.target.find(".dialog-title").css('cursor', 'move');
                        var maxWidth = width - opts.width;
                        var maxHeight = height - opts.height;
                        iDrag({
                            target: opts.target.find(".dialog-title"),
                            root: opts.target,
                            min: {x: 0, y: 0},
                            max: {x: maxWidth, y: maxHeight}
                        });
                    }
                }

                //添加 try 以防没有该方法
                try {
                    opts.onOpen();
                } catch (e) {
                }

                //设置弹出框遮罩层的z-index
                $("div[class='zd-window']:visible").each(function () {
                    if (zindex < $(this).css("z-index")) {
                        zindex = $(this).css("z-index");
                    }
                });
                if (opts.loading) {
                    Loading.hide(opts.loading);
                }
                /*if (!opts.isParentLoading) {
                    $('body', parent.document).prepend('<div class="zds-super-loading super-loading" ></div>');
                    $('body', parent.document).find('iframe').css('z-index', 100);
                    opts.isParentLoading = true;
                }*/

                opts.loading = Loading.show(null, false, ++zindex);
                if (zindex > 8888) {
                    opts.target.css("z-index", zindex + 2);
                }
                //绑定数据
                $(jq).data("options", opts);
                //显示弹出框
                opts.target.show();
            }
        },
        /**
         * 关闭弹出框
         * @param jq
         * */
        close: function (jq) {
            var opts = $(jq).data("options");
            if (opts) {

                try {
                    opts.onClose();
                } catch (e) {
                }

               /* var superLoading = $('body', parent.document).find('.zds-super-loading');
                if (superLoading.length <= 1) {
                    $('body', parent.document).find('iframe').css('z-index', 1);
                    superLoading.remove();
                } else {
                    //superLoading[0].remove();
                    $(superLoading[0]).remove();
                }*/

                opts.isParentLoading = false;

                Loading.hide(opts.loading);
                $("body").attr("scoll", null);
                $(opts.target).hide();
            }
        }

        ,
        /**
         * 获取options的值
         * */
        getOptions: function (jq) {
            var opts = $(jq).data("options");
            return opts;
        }

    };
})
;
