define(function (require, exports, module) {

    var $ = require('jquery');

    function ZTools() {
        this.name = "ZTools";
        this.version = '1.0.0';
    }


    ZTools.prototype = {
        constructor: ZTools,
        treeUpOrDown: function (selector) {
            $(selector).children('li').children('a').click(function () {
                $(this).parent().find('.sdu-list').slideToggle();
                $(this).toggleClass('active');
            });
        },
        layoutResize: function (selector, height) {
            height = height || 0;
            var window_height = (window.document.documentElement.clientHeight || window.document.body.clientHeight || window.innerHeight);
            $(selector).height(window_height - height);
            $(selector).css('overflow','auto');
        }
    };
    module.exports = new ZTools();

});
