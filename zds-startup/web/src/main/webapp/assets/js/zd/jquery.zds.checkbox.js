define(function (require, exports, module) {

	var $ = require('jquery');

	//内部构造方法
	function ZCheckbox() {
		this.name = "ZCheckbox";
		this.version = '1.0.0';
	}

	//定义基本对象
	$.ZCheckbox = new ZCheckbox();

	// 插件的定义
	$.fn.ZCheckbox = function (options) {
		//如果为string，表示是方法调用
		if (typeof options == 'string') {
			var method = $.fn.ZCheckbox.methods[options];
			if (method) {
				return method(this);
			} else {
				return
			}
		}
		options = options || {};
		//multiple为true，是复选框
		//multiple为false，是单选框
		var multiple = this.data('multiple');
		if (multiple && multiple === true) {
			options.multiple = true;
		}
		if (!options.valueField) {
			var valueField = $(this).data('valuefield');
			var textField = $(this).data('textfield');
			options.valueField = valueField;
			options.textField = textField;
		}
		//value为默认值
		if (!options.value) {
			// 修改获取初始值代码，如果value有值，优先取 value的值
			var defaultvalue = this.data('defaultvalue');
			var initValue = this.val();
			if (typeof initValue != "undefined" && initValue != "" && initValue != null) {
				//优先取value的值，原因是因为修改了下拉值，初始值不再是defaultvalue
				options.value = initValue;
			} else {
				options.value = defaultvalue;
			}
		}
		var opts = $.extend({}, $.fn.ZCheckbox.defaults, options);
		opts.target = this;

		//判断是否已渲染
		var _opts= $(this).data("options");
		if(_opts){
			$(_opts.dlTarget).remove();
		}

		//if：不存在本地数据，向服务器端请求数据初试化
		//else：存在data，直接初试化
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
					success: function (data) {
						opts.data = data;
						opts.target.data("options", opts);
						opts.target.hide();
						return opts.target.each(function () {
							$.fn.ZCheckbox.methods.init(opts);
						});
					}
				});
				$.ajaxSettings.async = true;
			} else {
				opts.data = eval(this.data('data'));
				this.data("options", opts);
				return this.each(function () {
					$(this).hide();
					$.fn.ZCheckbox.methods.init(opts);
				});
			}
		} else {
			this.data("options", opts);
			return this.each(function () {
				$(this).hide();
				$.fn.ZCheckbox.methods.init(opts);
			});
		}
	};

	$.fn.ZCheckbox.methods = {
		getValue: function (jq) {
			return $(jq).next().data("val");
		},
		getText: function (jq) {
			return $(jq).next().data("text");
		},
		init: function (opts) {
			var val = opts.valueField,
				text = opts.textField,
				data = opts.data,
				html = '',//html代码
				dText = '',//选中文本
				isDefaultValue = '';//本地数据项中默认选中的value

			//if:多选框
			//else:单选框
			if (opts.multiple) {
				html += '<ul class="form-checkbox">';
				$.each(data, function (i, item) {
					var _itemVal = item[val];

					//if：如果存在界面默认值 value与data-defaultvalue
					//else:isDefault
					if (opts.value != null && typeof(clearMh(JSON.stringify(opts.value))) != "undefined") {
						var t_values = JSON.stringify(opts.value).split(",");
						var _flog = false;
						for (var _i = 0; _i < t_values.length; _i++) {
							if (clearMh(_itemVal) == clearMh(t_values[_i])) {
								_flog = true;
								break;
							}
						}
						if (_flog) {
							html += '<li class="selected" data-name="' + _itemVal + '">' +
								'<i class="single-checkbox"></i>' + item[text] + '</li>';
							//存储选中的文本项
							if (dText) {
								dText += ",";
							} else {
								dText = "";
							}
							dText += item[text];
						} else {
							html += '<li  data-name="' + _itemVal + '">' +
								'<i class="single-checkbox"></i>' + item[text] + '</li>';
						}
					} else {
						if (item['isDefault']) {
							html += '<li  class="selected" data-name="' + _itemVal + '">' +
								'<i class="single-checkbox"></i>' + item[text] + '</li>';
							//存储选中的文本项
							if (dText) {
								dText += ',';
							} else {
								dText = '';
							}
							dText += item[text];
							//存储选中项的值
							if (isDefaultValue) {
								isDefaultValue += ',';
							} else {
								isDefaultValue = '';
							}
							isDefaultValue += _itemVal;
						} else {
							html += '<li  data-name="' + _itemVal + '">' +
								'<i class="single-checkbox"></i>' + item[text] + '</li>';
						}
					}
				});
			} else {
				html += '<ul class="form-radio">';
				$.each(data, function (i, item) {
					var _itemVal = item[val];

					//if:如果存在界面默认值 value与defaultvalue
					//else:有多个isDefault为true，最后一个默认选中
					if (opts.value != null && typeof(clearMh(JSON.stringify(opts.value))) != "undefined") {
						var t_values = JSON.stringify(opts.value).split(",");
						if (clearMh(_itemVal) == clearMh(t_values[0])) {
							var _flog = true;
						}
						if (_flog) {
							html += '<li class="selected" data-name="' + _itemVal + '">' +
								'<i class="single-radio"></i>' + item[text] + '</li>';
							if (dText) {
								dText += ",";
							} else {
								dText = "";
							}
							dText += item[text];
						} else {
							html += '<li  data-name="' + _itemVal + '">' +
								'<i class="single-radio"></i>' + item[text] + '</li>';
						}
					} else {
						if (item['isDefault']) {
							html += '<li  class="selected" data-name="' + _itemVal + '">' +
								'<i class="single-radio"></i>' + item[text] + '</li>';
							dText = item[text];
							isDefaultValue = _itemVal;
						} else {
							html += '<li  data-name="' + _itemVal + '"><i class="single-radio">' +
								'</i>' + item[text] + '</li>';
						}
					}
				});
			}
			html += '</ul>';
			opts.target.after(html);

			//如果value没值，取data.isDefault的值
			if (!opts.value) {
				opts.value = isDefaultValue;
			}

			opts.dlTarget = opts.target.next();
			opts.target.data("options", opts);

			//设置input元素value属性的值
			opts.target.attr("value", opts.value);

			//在选项的容器上，存储数据
			opts.dlTarget.data({
				'val': opts.value,
				'text': dText
			});

			opts.dlTarget.find("li").click(function () {
				var self = $(this),
					selfVal = self.data('name'),
					selfText = self.text();
				if (opts.multiple) {
					//获取之前的值
					var oldVal = opts.dlTarget.data('val');
					var oldText = opts.dlTarget.data('text');
					//判断之前是否选中
					if (self.hasClass("selected")) {
						//移除
						selfVal = removeItem(oldVal, selfVal);
						selfText = removeItem(oldText, selfText);
						self.removeClass("selected");
					} else {
						//添加
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
						self.addClass("selected");
					}

				} else {
					self.addClass("selected").siblings().removeClass("selected");
				}

				//绑定新数据
				opts.dlTarget.data({
					'val': selfVal,
					'text': selfText
				});

				//设置input元素value属性的值
				opts.target.attr("value", selfVal);

				//清除验证失败提示信息
				if (selfVal !== 'undefined' || selfVal.length > 0) {
					try {
						$.ZUI.cleanValidateText(opts.target.parent());
					} catch (e) {
					}
				}
			});
		}
	};
	$.fn.ZCheckbox.defaults = {
		id: '',//唯一标识
		valueField: "value",//值字段
		textField: "text",//文本字段
		url: null,//远程数据地址
		data: null,//本地数据
		multiple: false,//是否多选
		value: null,//默认值或选中项
		target: null
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
		if ((typeof str) != "string") {
			str = JSON.stringify(str);
		}
		str = str.replace("\"", "");
		str = str.replace("\"", "");
		return str;
	}
});
