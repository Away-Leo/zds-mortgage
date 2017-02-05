define(function (require, exports, module) {

    var $ = require('jquery');
    require('zd/jquery.zds.message');

    //内部构造方法
    function Zbothselecter() {
        this.name = "ZCombobox";
        this.version = '1.0.0';
    }


    //定义基本对象
    $.Zbothselecter = new Zbothselecter();

    // 插件的定义
    $.fn.Zbothselecter = function (options, param) {
        //如果为string，表示是方法调用
        if (typeof options == 'string') {
            var method = $.fn.Zbothselecter.methods[options];
            if (method) {
                return method(this, param);
            }
        }
        options = options || {};

        var valueField, textField, url, divTarget;

        options.title = options.title || '角色名称';

        if (!options.valueField) {
            valueField = this.data('valuefield');
            textField = this.data('textfield');
            options.valueField = valueField;
            options.textField = textField;
        }

        if (!options.data) {
            options.data = eval(this.data('data'));
        }

        if (!options.url) {
            url = this.data('url');
            options.url = url;
        }

        if (!options.divTarget) {
            divTarget = this.data('divTarget');
            options.divTarget = divTarget;
        }

        opts = $.extend({}, $.fn.Zbothselecter.defaults, options);
        opts.target = this;

        //判断是否已渲染
        //var _opts = $(this).data("options");
        //if (_opts) {
        //	$(_opts.divTarget).html('');
        //}

        //if:不存在本地数据，远程请求数据
        //else:初始化化本地数据
        if (!opts.data) {
            if (opts.url) {
                $.ajaxSettings.async = false;
                $.ajax({
                    async: true,
                    type: "get",
                    dataType: "jsonp",
                    url: opts.url,//要访问的后台地址
                    data: opts.queryParams,//请求参数
                    crossDomain: true,
                    success: function (data) {
                        opts.data = data;
                        opts.target.data("options", opts);
                        return opts.target.each(function () {
                            $.fn.Zbothselecter.methods.init(opts);
                        });
                    }
                });
                $.ajaxSettings.async = true;
            } else {
                this.data("options", opts);
                return this.each(function () {
                    $.fn.Zbothselecter.methods.init(opts);
                });
            }
        } else {
            this.data("options", opts);
            return this.each(function () {
                $.fn.Zbothselecter.methods.init(opts);
            });
        }
    };

    $.fn.Zbothselecter.methods = {
        getOptions: function (jq, selector) {
            var opts = $(jq).data("options") || $(selector).data("options");
            return opts;
        },
        init: function (opts) {
            /*var val = opts.valueField,
             text = opts.textField,
             selectData = opts.data[0].selectData,
             unSelectData = opts.data[0].unSelectData,
             selectTitle =opts.data[0].selectTitle || '拥有权限',
             unSelectTitle =opts.data[0].unSelectTitle || '可用权限',
             html = '';

             opts.selectData = selectData;
             opts.unSelectData = unSelectData;

             html += '<h1 class="zd-bs-title power-content-title">' + opts.title + '</h1>';

             html += '<div class="zd-bs-body">';

             html += '<div class="power-own"><h1 class="power-select-title">'+selectTitle+'</h1>';
             html += '<ul class="zd-bs-select power-select-list">';
             $.each(selectData, function (i, item) {
             var itemVal = item[val],
             itemText = item[text];
             html += '<li  class="zd-bs-sitem" data-value="' + itemVal + '" onselectstart="return false" >' + itemText + '</li>'
             });
             html += '</ul>';
             html += '</div>';

             html += '<div class="power-use"><h1 class="power-select-title">'+unSelectTitle+'</h1>';
             html += '<ul class="zd-bs-unselect power-select-list" >';
             $.each(unSelectData, function (i, item) {
             var itemVal = item[val],
             itemText = item[text];
             html += '<li class="zd-bs-unsitem" data-value="' + itemVal + '" onselectstart="return false">' + itemText + '</li>'
             });
             html += '</ul>';
             html += '</div>';

             html += '</div>';

             html+='<div class="zd-bs-btn power-btn">' +
             '<a href="javascript:void(0);" class="zd-bs-delete power-btn-gray">删除 <i class="icon-power-delete ml5"></i></a> ' +
             '<a href="javascript:void(0);" class="zd-bs-add power-btn-blue"><i class="icon-power-add mr5"></i>添加</a></div>';*/

            /*===================*/
            var val = opts.valueField,
                text = opts.textField,
                html = '',
                selectData, unSelectData, selectTitle, unSelectTitle;
            $.each(opts.data, function (i, item) {
                selectData = item.selectData;
                unSelectData = item.unSelectData;
                selectTitle = item.selectTitle || '拥有权限';
                unSelectTitle = item.unSelectTitle || '可用权限';
                opts.selectData = selectData;
                opts.unSelectData = unSelectData;
            });

            html += '<h1 class="zd-bs-title power-content-title">' + opts.title + '</h1>';

            html += '<div class="zd-bs-body">';

            html += '<div class="power-own"><h1 class="power-select-title">' + selectTitle + '</h1>';
            html += '<ul class="zd-bs-select power-select-list">';
            $.each(selectData, function (i, item) {
                var itemVal = item[val],
                    itemText = item[text];
                html += '<li  class="zd-bs-sitem" data-value="' + itemVal + '" onselectstart="return false" >' + itemText + '</li>'
            });
            html += '</ul>';
            html += '</div>';

            html += '<div class="power-use"><h1 class="power-select-title">' + unSelectTitle + '</h1>';
            html += '<ul class="zd-bs-unselect power-select-list" >';
            $.each(unSelectData, function (i, item) {
                var itemVal = item[val],
                    itemText = item[text];
                html += '<li class="zd-bs-unsitem" data-value="' + itemVal + '" onselectstart="return false">' + itemText + '</li>'
            });
            html += '</ul>';
            html += '</div>';

            html += '</div>';

            html += '<div class="zd-bs-btn power-btn">' +
                '<a href="javascript:void(0);" class="zd-bs-delete power-btn-gray">删除 <i class="icon-power-delete ml5"></i></a> ' +
                '<a href="javascript:void(0);" class="zd-bs-add power-btn-blue"><i class="icon-power-add mr5"></i>添加</a></div>';


            /*===================*/


            $(opts.divTarget).find('.zd-bs-title').remove();
            $(opts.divTarget).find('.zd-bs-body').remove();
            $(opts.divTarget).find('.zd-bs-btn').remove();

            $(opts.divTarget).prepend(html);

            $(opts.target).data("options", opts);
            $(opts.divTarget).data("options", opts);

            var divTarget = $(opts.divTarget);
            var times = null;

            divTarget.find('.zd-bs-select').on('click', '.zd-bs-sitem', function () {
                clearTimeout(times);
                var that = $(this);
                times = setTimeout(function () {
                    that.toggleClass('selected');
                }, 300);
            });
            divTarget.find('.zd-bs-unselect').on('click', '.zd-bs-unsitem', function () {
                clearTimeout(times);
                var that = $(this);
                times = setTimeout(function () {
                    that.toggleClass('selected');
                }, 300);
            });

            divTarget.find('.zd-bs-select').on('dblclick', '.zd-bs-sitem', function () {
                clearTimeout(times);
                //alert('拥有权限dbclick')
                var selVal = $(this).data('value');
                var selText = $(this).text();
                var selObj = {};
                selObj[opts.valueField] = selVal;
                selObj[opts.textField] = selText;

                for (var k = 0; k < selectData.length; k++) {
                    if (selectData[k][val] == selVal) {
                        selectData.splice(k, 1);
                    }
                }
                unSelectData.splice(-1, 0, selObj);
                divTarget.find('.zd-bs-unselect').append('<li class="zd-bs-unsitem" data-value="' + selVal + '" onselectstart="return false" >' + selText + '</li>');
                $(this).remove();

                opts.selectData = selectData;
                opts.unSelectData = unSelectData;
                opts.target.data('options', opts);
                opts.divTarget.data('options', opts);

            });
            divTarget.find('.zd-bs-unselect').on('dblclick', '.zd-bs-unsitem', function () {
                clearTimeout(times);
                //alert('可用权限dbclick')
                var selVal = $(this).data('value');
                var selText = $(this).text();
                var selObj = {};
                selObj[opts.valueField] = selVal;
                selObj[opts.textField] = selText;

                for (var k = 0; k < unSelectData.length; k++) {
                    if (unSelectData[k][val] == selVal) {
                        unSelectData.splice(k, 1);
                    }
                }
                selectData.splice(-1, 0, selObj);
                divTarget.find('.zd-bs-select').append('<li class="zd-bs-sitem" data-value="' + selVal + '" onselectstart="return false" >' + selText + '</li>');
                $(this).remove();

                opts.selectData = selectData;
                opts.unSelectData = unSelectData;
                opts.target.data('options', opts);
                opts.divTarget.data('options', opts);
            });

            divTarget.find('.zd-bs-add').on('click', function () {
                var selectRows = divTarget.find('.zd-bs-unselect').find('.selected');
                if (selectRows.length === 0) {
                    $.ZMessage.info('提示', '请选择项目！')
                }
                $.each(selectRows, function (i, item) {
                    var selVal = $(selectRows[i]).data('value');
                    var selText = $(selectRows[i]).text();
                    var selObj = {};
                    selObj[opts.valueField] = selVal;
                    selObj[opts.textField] = selText;

                    for (var k = 0; k < unSelectData.length; k++) {
                        if (unSelectData[k][val] == selVal) {
                            unSelectData.splice(k, 1);
                        }
                    }

                    selectData.splice(-1, 0, selObj);

                    divTarget.find('.zd-bs-select').append('<li class="zd-bs-sitem" data-value="' + selVal + '" onselectstart="return false" >' + selText + '</li>');
                });

                selectRows.remove();
                opts.selectData = selectData;
                opts.unSelectData = unSelectData;
                opts.target.data('options', opts);
                opts.divTarget.data('options', opts);

            });
            divTarget.find('.zd-bs-delete').on('click', function () {
                var selectRows = divTarget.find('.zd-bs-select').find('.selected');
                if (selectRows.length === 0) {
                    $.ZMessage.info('提示', '请选择项目！')
                }
                $.each(selectRows, function (i, item) {
                    var selVal = $(selectRows[i]).data('value');
                    var selText = $(selectRows[i]).text();
                    var selObj = {};
                    selObj[opts.valueField] = selVal;
                    selObj[opts.textField] = selText;

                    for (var k = 0; k < selectData.length; k++) {
                        if (selectData[k][val] == selVal) {
                            selectData.splice(k, 1);
                        }
                    }

                    unSelectData.splice(-1, 0, selObj);

                    divTarget.find('.zd-bs-unselect').append('<li class="zd-bs-unsitem" data-value="' + selVal + '" onselectstart="return false" >' + selText + '</li>');
                });

                selectRows.remove();
                opts.selectData = selectData;
                opts.unSelectData = unSelectData;
                opts.target.data('options', opts);
                opts.divTarget.data('options', opts);
            });

        }
    };

    // 插件的defaults
    $.fn.Zbothselecter.defaults = {
        id: null,//唯一标识
        title: "",
        valueField: "value",//值字段
        textField: "text",//文本字段
        url: null,//远程数据地址
        data: null,//本地数据
        target: null,
        divTarget: null,
        selectData: null,
        unSelectData: null
    };
});
