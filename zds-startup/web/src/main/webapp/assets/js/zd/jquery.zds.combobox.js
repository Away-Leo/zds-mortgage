define(function (require, exports, module) {

	var $ = require('jquery');
	var callback = require('zd/jquery.zds.page.callback');

	//内部构造方法
	function ZCombobox() {
		this.name = "ZCombobox";
		this.version = '1.0.0';
	}


	//定义基本对象
	$.ZCombobox = new ZCombobox();

	// 插件的定义
	$.fn.ZCombobox = function (options, param) {
		//如果为string，表示是方法调用
		if (typeof options == 'string') {
			var method = $.fn.ZCombobox.methods[options];
			if (method) {
				return method(this, param);
			}
		}
		options = options || {};

		var multiple = this.data('multiple'),
			valueField = this.data('valuefield'),
			textField = this.data('textfield'),
			defaultvalue = this.data('defaultvalue'),
			onSelect = this.data('callback'),
			initValue = this.val(),
			opts = {};

		if (!options.width) {
			var _width = $(this).data('width');
			if (_width) {
				if (typeof _width != "number") {
					_width = _width.replace("px", "");
				}
				options.width = _width;
			}
		}
		if (!options.height) {
			var _height = $(this).data('height');
			if (_height) {
				if (typeof _height != "number") {
					_height = _height.replace("px", "");
				}
				options.height = _height;
			}
		}

		if (onSelect) {
			options.onSelect = callback[onSelect];
		}

		if (multiple && multiple === true) {
			options.multiple = true;
		}

		if (!options.valueField) {
			options.valueField = valueField;
			options.textField = textField;
		}

		// 如果value有值，优先取 value的值,原因是因为修改了下拉值，初始值不再是defaultvalue
		if (!options.value) {
			if (typeof initValue != "undefined" && initValue != "" && initValue != null) {
				options.value = initValue;
			} else {
				options.value = defaultvalue;
			}
		}

		opts = $.extend({}, $.fn.ZCombobox.defaults, options);
		opts.target = this;

		//判断是否已渲染
		var _opts= $(this).data("options");
		if(_opts){
			$(_opts.dlTarget).remove();
		}

		//if:不存在本地数据，远程请求数据
		//else:初始化化本地数据
		if (!opts.data) {
			if (!opts.url) {
				opts.url = this.data('url');
			}
			if (opts.url) {
				$.ajaxSettings.async = false;
				$.ajax({
					async: true,
					type: "get",
					dataType: "jsonp",
					url: opts.url,//要访问的后台地址
					data: opts.queryParams,
					crossDomain: true,
					complete: function () {
						//关闭遮挡层
						//Loading.hide(options.loading);
					},
					success: function (data) {
						opts.data = data;
						opts.target.data("options", opts);
						opts.target.hide();
						return opts.target.each(function () {
							$.fn.ZCombobox.methods.init(opts);
							opts.onLoadSuccess(data);
						});
					}
				});
				$.ajaxSettings.async = true;
			} else {
				opts.data = eval(this.data('data'));
				this.data("options", opts);
				return this.each(function () {
					$(this).hide();
					$.fn.ZCombobox.methods.init(opts);
				});
			}
		} else {
			this.data("options", opts);
			return this.each(function () {
				$(this).hide();
				$.fn.ZCombobox.methods.init(opts);
			});
		}
	};

	$.fn.ZCombobox.methods = {
		getValue: function (jq) {
			return $(jq).next().data("val");
		},
		getText: function (jq) {
			return $(jq).next().data("text");
		},
		setValue: function (jq, value) {
			var opts = $(jq).data("options");
			//循环所有选项
			$(jq).next().find('dd').find('p').each(function () {
				var selfVal = $(this).data('name'),
					text = $(this).text();
				if (selfVal == value) {
					$(this).parent().hide().siblings().children('a').text(text);
					$(this).parent().parent().data({
						'val': selfVal,
						'text': text
					});
					$(this).parent().parent().prev().attr("value", selfVal);
					$(opts.target).attr("value", selfVal);
					$(this).addClass("zui-option-selected").siblings().removeClass("zui-option-selected");
					opts.onSelect(selfVal, text, $(this).index());
					try {
						$.ZUI.cleanValidateText(opts.target.parent());
					} catch (e) {
					}
					return false;
				}
			});
		},
		disable: function (jq) {
			$(jq).data("choose", "disable");
		},
		enabled: function (jq) {
			$(jq).data("choose", "enabled");
		},
		loadData:function(jq,data){
			var opts=$(jq).next().data('options');
			if(Object.prototype.toString.call(opts) === '[object Array]'){
				opts.data=data;
				$.fn.ZCombobox.methods.init(opts);
			}
		},
		init: function (opts) {
			var val = opts.valueField,
				text = opts.textField,
				data = opts.data,
				html = '<dl class="zui-select-panel">',
				dtHtml = '',
				ddHtml = '',
				dText = '',
				isDefaultValue = '',
				choose = opts.target.data("choose");

			if(opts.width && choose && choose == 'disable'){
				dtHtml = ' <dt><a class="zui-mselect zui-disabled"  href="javascript:void (0);" style="width: '+opts.width+'px;">';
			}else if (choose && choose == 'disable') {
				dtHtml = ' <dt><a class="zui-mselect zui-disabled"  href="javascript:void (0);">';
			}else if(opts.width){
				dtHtml = ' <dt><a class="zui-mselect" href="javascript:void (0);" style="width: '+opts.width+'px;">';
			}else{
				dtHtml = ' <dt><a class="zui-mselect" href="javascript:void (0);">';
			}

			if(opts.height){
				ddHtml = '<dd class="zui-option" style="max-height: '+opts.height+'px;">';
			}
			if(opts.height && opts.width){
				ddHtml='<dd class="zui-option" style="max-height: '+opts.height+'px;width:'+opts.width+'px;">';
			}else if(opts.height){
				ddHtml='<dd class="zui-option" style="max-height: '+opts.height+'px;">';
			}else if(opts.width){
				ddHtml='<dd class="zui-option" style="width:'+opts.width+'px;">';
			}else {
				ddHtml='<dd class="zui-option">';
			}
			$.each(data, function (i, item) {
				var itemVal = item[val];

				//if:如果存在界面默认值 value与defaultvalue
				//else:isDefault
				if (opts.value != null && typeof(clearMh(JSON.stringify(opts.value))) != "undefined") {
					var t_values = JSON.stringify(opts.value).split(",");
					var _flog = false;
					for (var _i = 0; _i < t_values.length; _i++) {
						if (clearMh(itemVal) == clearMh(t_values[_i])) {
							_flog = true;
							break;
						}
					}
					if (_flog) {
						ddHtml += '<p href="javascript:void(0)" class="zui-option-selected" data-name="' + itemVal + '">' + item[text] + '</p>';
						if (dText) {
							dText += ",";
						} else {
							dText = "";
						}
						dText += item[text];
					} else {
						ddHtml += '<p  data-name="' + itemVal + '">' + item[text] + '</p>';
					}
				} else {
					if (item['isDefault']) {
						ddHtml += '<p  class="zui-option-selected" data-name="' + itemVal + '">' + item[text] + '</p>';
						if (dText) {
							dText += ",";
						} else {
							dText = "";
						}
						dText += item[text];
						if (isDefaultValue) {
							isDefaultValue += ",";
						} else {
							isDefaultValue = "";
						}
						isDefaultValue += itemVal;
					} else {
						ddHtml += '<p  data-name="' + itemVal + '">' + item[text] + '</p>';
					}
				}
			});

			dtHtml += dText + '</a></dt>';
			ddHtml += '</dd>';
			html += dtHtml + ddHtml + '</dl>';
			opts.target.after(html);

			if (!opts.value) {
				opts.value = isDefaultValue;
			}

			opts.dlTarget = opts.target.next();
			opts.target.data("options", opts);
			opts.target.attr("value", opts.value);
			opts.dlTarget.data({
				'val': opts.value,
				'text': dText
			});

			opts.dlTarget.find('dt').children('a').on('click', function () {
				var choose = opts.target.data('choose');
				if (choose && choose == 'disable') {
					return;
				}
				$(this).addClass('focus');
				$(this).parent('dt').parent('dl').css({
					zIndex: '100'
				});
				$(this).parent('dt').siblings('dd').slideDown();
			});
			
			opts.dlTarget.find("p").on('click', function () {
				var choose = opts.target.data('choose');
				if (choose && choose == 'disable') {
					return;
				}
				var self = $(this),
					selfVal = self.data('name'),
					selfText = self.text();
				if (opts.multiple) {
					//获取之前的值
					var oldVal = opts.dlTarget.data('val');
					var oldText = opts.dlTarget.data('text');
					if (!oldText) {
						oldText = self.parent().hide().siblings().children('a').text();
					}
					//判断之前是否选中
					if (self.hasClass("zui-option-selected")) {//移除
						selfVal = removeItem(oldVal, selfVal);
						selfText = removeItem(oldText, selfText);

						self.removeClass("zui-option-selected");
					} else {//添加
						if (oldVal) {
							oldVal += ","
						} else {
							oldVal = "";
						}
						selfVal = oldVal + selfVal;
						if (oldText) {
							oldText += ",";
						} else {
							oldText = "";
						}
						selfText = oldText + selfText;
						self.addClass("zui-option-selected");
					}

				} else {
					self.addClass("zui-option-selected").siblings().removeClass("zui-option-selected");
				}
				self.parent().hide().siblings().children('a').text(selfText);
				opts.onSelect(selfVal, text, $(this).index());
				opts.dlTarget.data({
					'val': selfVal,
					'text': selfText
				});
				opts.target.attr("value", selfVal);
				//清除验证失败提示信息
				if (selfVal !== 'undefined' || selfVal.length > 0) {
					try {
						$.ZUI.cleanValidateText(opts.target.parent());
					} catch (e) {
					}
				}
			});

			opts.dlTarget.on('mouseleave', function () {
				var self = $(this);
				self.children('dt').children('a').removeClass('focus');
				self.find('dd').slideUp();
				self.css({
					zIndex: '1'
				});
			});
		}
	};

	// 插件的defaults
	$.fn.ZCombobox.defaults = {
		id: null,//唯一标识
		valueField: "value",//值字段
		textField: "text",//文本字段
		url: null,//远程数据地址
		data: null,//本地数据
		multiple: false,//是否多选
		width: null,//下拉框宽度
		height: null,//下拉选项高度
		value: null,//默认值
		target: null,
		onSelect: function (value, text, index) {

		},
		onLoadSuccess:function(data){

		}
	};
	/**
	 * 移除oldVal中的val
	 * @param oldVal
	 * @param val
	 */
	function removeItem(oldVal, val) {
		if (oldVal) {
			oldVal = oldVal.replace(val, "");
			//最前面的,
			if (oldVal.length > 0) {
				if (oldVal.substr(0, 1) == ",") {
					oldVal = oldVal.substr(1, oldVal.length);
				}
				if (oldVal.substr(oldVal.length - 1, oldVal.length) == ",") {
					oldVal = oldVal.substr(0, oldVal.length - 1);
				}
			}
			//最后面的,
			return oldVal.replace(",,", ",");
		}
		return "";
	}

	/**
	 * 清除通过JOSN.stringify()解析出字符串中的引号
	 * @param str
	 */
	function clearMh(str) {
		if ((typeof str) != "string")return JSON.stringify(str);
		str = str.replace("\"", "");
		str = str.replace("\"", "");
		return str;
	}

});
