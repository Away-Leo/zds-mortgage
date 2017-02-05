define(function (require, exports, module) {
    var $ = require('jquery');
    var callBack = require('zd/jquery.zds.page.callback');

    function Switch(config) {
        this.config = $.extend({}, Switch.defaultConfig, config);
        this.init();
    }

    Switch.defaultConfig = {
        tabsItem: '.tabs li',
        closeItem: '.tabs li .dynamic-close',
        movePrev: '.tabs-prev',
        moveNext: '.tabs-next'
    };

    Switch.prototype = {
        init: function () {
            var that = this;
            //tab标签切换
            $(that.config.tabsItem).off().on({
                click: function () {
                    var self = $(this),
                        index = self.index();
                    self.addClass('tabs-on').siblings().removeClass('tabs-on');
                    if (self.parent().hasClass('dynamic')) {
                        self.parent().parent().siblings().children().eq(index).removeClass('hide').siblings().addClass('hide');
                    } else {
                        self.parent().siblings().children().eq(index).removeClass('hide').siblings().addClass('hide');
                    }
                    //添加回调函数
                    var formatter = self.attr('formatter');
                    if (typeof formatter != 'undefined') {
                        return callBack[formatter]();
                    }
                }
            });

            //关闭图标
            if ($(that.config.closeItem).length > 0) {
                $(that.config.closeItem).off().on({
                    click: function () {
                        var self = $(this),
                            curLi = self.parent().parent(),
                            prevLi = curLi.prev(),
                            index = curLi.index(),
                            rmContent = curLi.parent().parent().siblings();

                        prevLi.trigger('click');
                        curLi.remove();
                        rmContent.children().eq(index).remove();

                    }
                })
            }

            //左箭头
            $(that.config.movePrev).off().on({
                click: function () {
                    var ul = $(this).parent().children('ul');
                    //ul的scrollLeft的旧值
                    var oldLeftWidth = ul.get(0).scrollLeft;
                    //ul的款的
                    var ulWidth = $(ul).width();
                    //初始化ul的lis的宽度为0
                    var liWidths = 0;
                    //计算lis的宽度
                    $(ul).children('li').each(function (i) {
                        liWidths += $(this).width();
                    });
                    //ul的scrollLeft的新值
                    ul.get(0).scrollLeft -= 100;
                    //箭头的状态
                    that.changeStatus(ul);

                }
            });

            //右箭头
            $(that.config.moveNext).off().on({
                click: function () {
                    var ul = $(this).parent().children('ul');
                    //ul的scrollLeft的旧值
                    var oldLeftWidth = ul.get(0).scrollLeft;
                    //ul的款的
                    var ulWidth = $(ul).width();
                    //初始化ul的lis的宽度为0
                    var liWidths = 0;
                    //计算lis的宽度
                    $(ul).children('li').each(function (i) {
                        liWidths += $(this).width();
                    });
                    //ul的scrollLeft的新值
                    ul.get(0).scrollLeft += 100;
                    //箭头的状态
                    that.changeStatus(ul);
                }
            });

        },
        create: function (tabSelector, tabItem) {
            var ul = $(tabSelector),
                a = ul.find('a[id=zd' + tabItem.id + ']'),//a标签
                li = a.parent('li'),//tab标签
                siblis = li.siblings(li),//其他tab标签

                liHtml = '',//tab标签的html
                formatter,//回调函数
                ulWidth = ul.width(),
                liWidths = 0;


            if (li.length > 0) {
                //tab标签已经存在
                li.trigger('click');
                //li.addClass('tabs-on');
                //siblis.removeClass('tabs-on');
                //确定tab标签的位置
                $(li).prevAll('li').each(function (i) {
                    liWidths += $(this).width();
                });
                //tab标签在可视区域(ul)的前面
                if (liWidths < ul.get(0).scrollLeft) {
                    ul.get(0).scrollLeft = liWidths + 20;
                }
                //tab标签在可视区域(ul)的后面
                liWidths += $(li).width();
                if (liWidths > ul.get(0).scrollLeft + ulWidth) {
                    ul.get(0).scrollLeft = liWidths - ulWidth + 200;
                }
                //箭头的状态
                this.changeStatus(ul);
            } else {
                //tab标签不存在,新增tab标签
                if (tabItem.formatter) {
                    formatter = 'formatter="' + tabItem.formatter + '"';
                }

                liHtml = '<li ' + formatter + '><a href="javascript:void(0);" data-path="' + tabItem.path + '" id="zd' + tabItem.id + '">';
                liHtml += '<span class="tabs-text">' + tabItem.label + '</span>';
                liHtml += '<span class="dynamic-close"> <i class="icon-dynaclose"></i></span>';

                liHtml += '</a></li>';
                ul.append(liHtml);

                ul.find('li').last().addClass('tabs-on');
                ul.find('li').last().siblings().removeClass('tabs-on');
                var divHtml = '<div><iframe  width="100%" frameborder="0" allowtransparency="true" src="' + tabItem.path + '"class="zui-iframe"></iframe></div>';
                ul.parent().next().children().addClass('hide');
                ul.parent().next().append(divHtml);

                //确定tab标签的位置
                ulWidth = $(ul).width();
                ul.children('li').each(function (i) {
                    liWidths += $(this).width();
                });
                ul.get(0).scrollLeft = liWidths - ulWidth + 200;
                //箭头的状态
                this.changeStatus(ul);
            }
            //初始化事件
            if ($(this.config.tabsItem).length === 0) {
                var obj = {
                    config: {
                        tabsItem: $('.tabs li', parent.document),
                        closeItem: $('.tabs li .dynamic-close', parent.document),
                        movePrev: $('.tabs-prev', parent.document),
                        moveNext: $('.tabs-next', parent.document)
                    }
                };
                this.init.call(obj);
                return;
            }
            this.init();
        },
        changeStatus: function (ul) {
            var ul = $(ul);
            var ulWidth = ul.width();
            var liWidths = 0;
            $(ul).children('li').each(function (i) {
                liWidths += $(this).width();
            });
            //控制左箭头的状态
            if (ul.get(0).scrollLeft === 0) {
                ul.parent().find('.tabs-prev').hide();
            }
            else {
                ul.parent().find('.tabs-prev').show();
            }
            //控制右箭头的状态
            if (liWidths <= ulWidth + ul.get(0).scrollLeft) {
                ul.parent().find('.tabs-next').hide();
            } else {
                ul.parent().find('.tabs-next').show();
            }
        },
        close: function (tabSelector, id) {
            var ul = $(tabSelector),
                a = ul.find('a[id=zd' + id + ']');//a标签
            if (a.length > 0) {
                var rmLi = a.parent(),
                    prevLi = rmLi.prev(),
                    index = rmLi.index(),
                    rmContent = rmLi.parent().parent().siblings();
                prevLi.trigger('click');
                rmLi.remove();
                rmContent.children().eq(index).remove();
            }
        }
    };

    module.exports = new Switch();

});
