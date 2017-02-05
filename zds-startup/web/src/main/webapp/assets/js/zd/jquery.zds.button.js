define(function (require, exports, module) {

	var $ = require('jquery');

	function ZButton() {
		this.name = "ZButton";
		this.version = '1.0.0';
	}

	//定义基本对象
	$.ZButton = new ZButton();

	$.fn.ZButton = function (options, param) {
		if (typeof options === 'string') {
			var method = $.fn.ZButton.methods[options];
			if (method) {
				return method(this, param);
			}
		}
		options = options || {};
		var opts = $.extend({}, $.fn.ZButton.defaults, options);

		return this.each(function () {
			var oldOptions = null,
				num = "",//随机数，设置id
				i = 0;//for循环变量

			//根据群组按钮还是普通按钮
			//获取oldOptions的值
			if (opts.groupDiv) {
				if (!opts.id) {
					for (i = 0; i < 6; i++) {
						num += Math.floor(Math.random() * 10);
					}
					opts.id = "zbt" + num;
				}
				oldOptions = $(opts.groupDiv).data(opts.id);
			} else {
				if (!opts.id) {
					for (i = 0; i < 6; i++) {
						num += Math.floor(Math.random() * 10);
					}
					opts.id = "zbt" + num;
				}
				oldOptions = $(this).data("zbutton");
			}

			//获取到oldOptions的值，直接返回
			if (oldOptions != null) {
				return;
			}

			//如果是群组则将数据存在群组
			//否则，保存在zbutton
			if (opts.groupDiv) {
				$(opts.groupDiv).data(opts.id, opts);
			} else {
				opts.target = $(this);
				$(this).data("zbutton", opts);
			}

			//创建按钮
			createButton(opts);
		});
	};
	$.fn.ZButton.methods = {
		/**
		 * 获取zbutton存放的数据
		 * */
		options: function (jq) {
			return $.data(jq[0], "zbutton");
		},
		/**
		 * 启动按钮
		 * */
		enable: function (jq) {
			return jq.each(function () {
				var opts = $.data(jq[0], "zbutton");
				opts.disabled = false;
				setDisabled(opts);
			});
		},
		/**
		 * 禁用按钮
		 * */
		disable: function (jq) {
			return jq.each(function () {
				var opts = $.data(jq[0], "zbutton");
				opts.disabled = true;
				setDisabled(opts);
			});
		}
	};

	$.fn.ZButton.defaults = {
		id: null,//id
		text: "",//按钮内容
		buttonCls: null,//按钮样式
		iconCls: null,//按钮图标
		disabled: false,//是否可用
		target: null,
		groupDiv: null,//群组所在的DIV
		isInit: false,//是否初始化
		handler: function () {
		}
	};

	/**
	 * 创建按钮
	 * @param options
	 */
	function createButton(options) {
		var target = null,
			  html = '';//按钮代码片段

		//创建按钮
		if (options.groupDiv) {
			target = options.groupDiv;
			html += ('<button id="zds_btn_' + options.id + '" class="mr10 mb15 ' + options.buttonCls + '">');
			if (options.iconCls) {//按钮图标
				html += ('<i  class="' + options.iconCls + '"></i>');
			}
			html += (options.text + '</button>');
			$(target).append(html);
		} else {
			target = options.target;
			html += ('<button id="zds_btn_' + options.id + '" class="mr10 ' + options.buttonCls + '">');
			if (options.iconCls) {//按钮图标
				html += ('<i  class="' + options.iconCls + '"></i>');
			}
			html += (options.text + '</button>');
			$(target).after(html);
			$(target).hide();
		}

		//设置按钮状态
		setDisabled(options);
	}

	/**
	 * 设置按钮状态（disabled：true|false）
	 * @param options
	 */
	function setDisabled(options) {
		var target = null;
		if (options.groupDiv) {
			target = options.groupDiv;
			if (options.disabled) {
				$(target).find("#zds_btn_" + options.id).unbind("click");
				$(target).find("#zds_btn_" + options.id).addClass("disabled");
			} else {
				$(target).find("#zds_btn_" + options.id).unbind("click");
				$(target).find("#zds_btn_" + options.id).click(function (e) {
					options.handler();
					e.preventDefault();
				});
				$(target).find("#zds_btn_" + options.id).removeClass("disabled");
			}
		} else {
			target = options.target;
			if (options.disabled) {
				$(target).next("#zds_btn_" + options.id).unbind("click");
				$(target).next("#zds_btn_" + options.id).addClass("disabled");
			} else {
				$(target).next("#zds_btn_" + options.id).unbind("click");
				$(target).next("#zds_btn_" + options.id).click(function (e) {
					options.handler();
					e.preventDefault();
				});
				$(target).next("#zds_btn_" + options.id).removeClass("disabled");
			}
		}
	}
});