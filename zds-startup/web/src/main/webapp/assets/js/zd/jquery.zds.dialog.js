define(function (require, exports, module) {

    var $ = require('jquery');
    require("zd/jquery.zds.window");

    //初始化对象
    function Zdialog() {
        this.version = "1.0.0";
        this.name = "Zdialog";
    }

    //定义基本对象
    var _z = new Zdialog();
    $.Zdialog = _z;
    module.exports = _z;
    //定义插件
    $.fn.Zdialog = function (options) {
        //如果为string，表示是方法调用
        if (typeof options == 'string') {
            var method = $.fn.Zdialog.methods[options];
            if (method) {
                return method(this);
            } else {
                method = $.fn.ZWindow.methods[options];
                if (method) {
                    return method(this);
                }
            }
        }
        options = options || {};

        var _opts = this.data("options");

        //判断是否已渲染
        if (_opts && _opts.isInit) {
            options = $.extend({}, _opts, options);
        }

        var opts = $.extend({}, $.fn.Zdialog.defaults, options);
        opts = $.extend({}, $.fn.ZWindow.defaults, opts);

        if (_opts && (!_opts.createNew)) {
            $(this).ZWindow(_opts);
            return;
        }

        $(this).each(function () {

            var html = [],
                bodyHtml = '',
                dialog_btn = '';

            //判断是否已渲染
            if (_opts && _opts.isInit) {
                bodyHtml = $(opts.target).find(".zd-window").html();
            } else {
                bodyHtml = $(this).html();
            }

            //标题
            html.push('<div class="zd-window-title dialog-title "><span> ' + opts.title + '</span><a class="dialog-close"><i class="icon-dclose"></i></a> </div>');

            //内容
            html.push('<div class="zd-window-body dialog-content">');
            html.push(bodyHtml);
            html.push('</div>');

            //按钮
            if (opts.buttons) {
                dialog_btn += ('<div class="zd-window-bottom dialog-bottom">');
                $.each(opts.buttons, function (i, item) {
                    dialog_btn += ('<a href="javascript:void(0);">' + item.text + '</a>');
                });
                dialog_btn += ('</div>');
                html.push(dialog_btn);
            }

            $(this).html(html.join(""));

            //创建弹出框
            $(this).ZWindow({
                width: opts.width,
                height: opts.height,
                title: opts.title,
                closed: opts.closed,
                isIframe: opts.isIframe,
                isDrag: opts.isDrag
            });

            //创建按钮
            var w_opts = $(this).data("options");
            w_opts.target.find(".dialog-bottom a").each(function (i) {
                $(this).ZButton(opts.buttons[i]);
            });

            //关闭图标
            w_opts.target.find(".dialog-title .dialog-close").click(function () {
                opts.target.Zdialog("close");
            });

            opts.target = $(this);
        });
    };
    $.fn.Zdialog.methods = {
        /**
         * 打开弹出框
         * @param jq
         * */
        open: function (jq) {
            $.fn.ZWindow.methods["open"](jq);
        },
        /**
         * 关闭弹出框
         * @param jq
         * */
        close: function (jq) {
            $.fn.ZWindow.methods["close"](jq);
        }
    };
    $.fn.Zdialog.defaults = {
        width: 500,
        height: 250,
        title: '',
        isDrag: true,//是否可以拖拽移动
        target: null,
        createNew: false
    };


});