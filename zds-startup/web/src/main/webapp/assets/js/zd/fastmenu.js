define(function (require, exports, module) {
    var $ = require('jquery');
    var tabSwitch = require('zd/switch');
    var callBack = require('zd/jquery.zds.page.callback');

    function FastMenu(config) {
        this.config = $.extend({}, FastMenu.defaultConfig, config);
        this.init();
    }

    FastMenu.defaultConfig = {
        fastNav: $('#fastNav'),
        fastAdd: $('#fastAdd'),
        fastBox: $('#fastBox'),
        fastIcon: $('#fastIcon'),
        target: 'index'
    };

    FastMenu.prototype = {
        init: function () {
            var that = this;
            //创建快捷标签
            that.config.fastNav.children('li').children('a').off().on('click', function () {
                var self = $(this),
                    tabItem = {
                        id: self.attr('id'),
                        label: self.text(),
                        path: self.data('path')
                    };
                tabSwitch.create('#dynamic-tabs', tabItem);
            });
            //删除快捷菜单项
            that.config.fastNav.find('.icon-menuclose').off().on('click', function (e) {
                //e.stopPropagation();
                //e.preventDefault();
                var curLi = $(this).parent().parent(),
                    curLiClass = curLi.attr('class'),
                    formatter = curLi.attr('formatter'),
                    topLi,
                    lis;
                if (curLiClass && curLiClass.indexOf('topmenu') >= 0) {
                    curLi.remove();
                    topLi = $('#fastNav').children('li:lt(4)');
                    topLi.addClass('topmenu');
                    topLi.show();
                } else {
                    curLi.remove();
                }

                lis = that.config.fastNav.children('li');
                if (lis.length < 5) {
                    that.config.fastNav.addClass('bg');
                } else {
                    that.config.fastNav.removeClass('bg');
                }
                if (lis.length < 1) {
                    that.config.fastAdd.addClass('place');
                } else {
                    that.config.fastAdd.removeClass('place');
                }

                if (typeof formatter != 'undefined') {
                    return callBack[formatter]();
                }

                return false;
            });
            //展开菜单
            that.config.fastIcon.off().on('click', function () {
                $(this).addClass('active');
                that.config.fastNav.children('li').not('.topmenu').slideDown();
                that.config.fastAdd.slideDown();
                that.config.fastNav.addClass('active');
            });
            //收起菜单
            that.config.fastBox.off().on('mouseleave', function () {
                that.config.fastIcon.removeClass('active');
                that.config.fastNav.children('li').not('.topmenu').slideUp();
                that.config.fastAdd.slideUp();
                that.config.fastNav.removeClass('active');
            });
            //清除和添加按钮
            that.config.fastAdd.children('a').off().on('click', function () {
                var formatter = $(this).attr('formatter');
                if (typeof formatter != 'undefined') {
                    return callBack[formatter]();
                }
            });
        },
        create: function (id, label, path, formatter) {
            var lis = this.config.fastNav.children('li');
            var newLi = '';

            //判断id是否重复
            for (var i = 0, len = lis.length; i < len; i++) {
                var zdsId = $(lis[i]).children().attr('id');
                if(zdsId == id){
                    return false;
                }
            }

            if (lis.length < 4) {
                newLi = '<li class="topmenu" formatter="' + formatter + '"><a id="' + id + '" data-path="' + path + '" href="javascript:void(0);">' + label + ' <i class="icon-menuclose"></i></a></li>';
                this.config.fastNav.addClass('bg');
            } else {
                newLi = '<li formatter="' + formatter + '"><a id="' + id + '" data-path="' + path + '" href="javascript:void(0);">' + label + ' <i class="icon-menuclose"></i></a></li>';
                this.config.fastNav.removeClass('bg');
            }

            this.config.fastNav.append(newLi);

            lis = this.config.fastNav.children('li');

            if (lis.length < 1) {
                this.config.fastAdd.addClass('place');
            } else {
                this.config.fastAdd.removeClass('place');
            }

            this.init();
            return true;
        },
        clear: function () {
            this.config.fastNav.children().remove();

            var lis = this.config.fastNav.children('li');

            if (lis.length < 5) {
                this.config.fastNav.addClass('bg');
            } else {
                this.config.fastNav.removeClass('bg');
            }

            if (lis.length < 1) {
                this.config.fastAdd.addClass('place');
            } else {
                this.config.fastAdd.removeClass('place');
            }
        }
    };

    module.exports = new FastMenu();

});
