define(function (require, exports, module) {
	var $ = require('jquery');
	var callBlack = require('zd/jquery.zds.page.callback');
	require('zd/jquery.zds.message');
	require('zd/jquery.zds.validate');
	require('zd/jquery.zds.combobox');
	require('zd/jquery.zds.combotree');
	require('zd/jquery.zds.checkbox');
	require('datepicker');
	require('upload');

	//构造函数
	function ZUI() {
		this.version = '1.0.0';
		this.name = 'zui-from';
		this.options = null;
	}

	//实例化对象，对外提供接口
	$.ZUI = new ZUI();
	module.exports = $.ZUI;

	String.prototype.replaceAll = function (s1, s2) {
		return this.replace(new RegExp(s1, "gm"), s2);
	};
	//内部定义自身方法
	$.extend(ZUI.prototype, {
		init: function (div) {
			if (!div) {
				div = "";
			}
			//初始化页面
			$.ZUI.initForms(div);
			$.ZUI.initGrid(div);
			$.ZUI.strToDate();

		},
		/**
		 * 1、初始化一个或多个表单
		 * 2、绑定表单提交
		 * */
		initForms: function (div) {
			$(div + " form[class^=zui-form]").each(function () {
				// 绑定表单提交
				$(this).unbind();
				$(this).submit(function () {
					var optObj = $.ZUI.bindOptions(this);
					if ($.ZUI.validateForm(this)) {
						var params = $(this).serialize();
						try {
							$.ajax({
								url: optObj.url,
								type: "post",
								data: params,
								jsonp: "jsoncallBack",
								dataType: "jsonp",
								success: function (data) {
									callBlack[optObj.callBack](data);
								}
							});
						} catch (e) {

						}
					} else {
						$.ZMessage.error("错误", "数据验证失败", function () {
						});
					}
					return false;
				});
				$(this).find("[class*=zui-]").each(function () {
					var _class_names = $(this).attr("class").split(" ");
					var that = this;
					for (var _i = 0; _i < _class_names.length; _i++) {
						var _this_class = _class_names[_i];
						var flag = $(that).data('multiple');
						switch (_this_class) {
							case "zui-validatebox":
								$(that).ZDSValidatebox('bindBox', null);
								break;
							case "zui-combobox":
								$(that).ZCombobox({multiple: flag});
								break;
							case "zui-combotree":
								$(that).ZComboTree({multiple: flag});
								break;
							case "zui-checkbox":
								$(that).ZCheckbox({multiple: flag});
								break;
							case "zui-upload":
								$(that).uploadify({
									height: 30,
									width: 120,
									swf: '../assets/js/vendor-modules/uploadify//uploadify.swf',
									uploader: '../assets/js/vendor-modules/uploadify/uploadify.php'
								});
								break;
						}
					}
				});
			});
		},
		dialogInit: function () {
			$("form[class^=zui-]").each(function () {

				// 绑定表单提交
				$(this).unbind().bind("submit", function () {
					var optObj = $.ZUI.bindOptions(this);
					if ($.ZUI.validateForm(this)) {
						var params = $(this).serialize();
						$.ajax({
							url: optObj.url,
							type: "post",
							data: params,
							jsonp: "jsoncallBlack",
							dataType: "jsonp",
							success: function (data) {
								// 加载成功之前
								//$.ZUI.bindcallBlack(optObj.callBlack, data).func();
								try {
									callBlack[optObj.callBack](data);
								} catch (e) {

								}
							}
						})
					} else {
						$.ZMessage.error("错误", "数据验证失败", function () {
						});
					}
					return false;
				});
				$(this).find("[class*=zui-]").each(function () {
					var _class_names = $(this).attr("class").split(" ");
					var that = this;
					// 这里可以写成target,className为参数进行绑定
					for (var _i = 0; _i < _class_names.length; _i++) {
						var _this_class = _class_names[_i];
						switch (_this_class) {
							case "zui-validatebox":
								$(that).ZDSValidatebox('bindBox', null);
								break;
							case "zui-combobox":
								var that = this;
								var dialogInt = $(that).attr('dialogInit');
								if (typeof dialogInt != 'undefined') {
									try {
										$(that).ZCombobox();
									} catch (e) {

									}
									break;
								}


						}
					}

				});
			});
		},
		validateSubmit: function (target, status) {
			var checked = true;
			var comboChecked = true;
			var finalResult = $(target).ZDSValidatebox('validateForm', $(target), status);
			if (!finalResult) {
				checked = false;
			}
			return checked;
		},
		bindcallBlack: function (methodName, data) {//回调成功所调函数
			if (arguments.length > 2) {
				var args = [];
				for (var i = 2; i < arguments.length; i++) {
					args.push(arguments[i]);
				}
			}
			if (methodName) {

				function zcallBlackMethod(methodName, data, args) {
					data = JSON.stringify(data);
					for (var i = 0; args && i < args.length; i++) {
						data += "," + JSON.stringify(args[i]);
					}
					if (data) {
						this.func = new Function("return " + methodName + "(" + data + ");");
					} else {
						this.func = new Function("return " + methodName + "();");
					}

				}

				return new zcallBlackMethod(methodName, data, args);
			}
		},
		initGrid: function (div) {
			$(div + " div[class*=zui-datagrid]").each(function () {
				//$(this).addClass('zd-table');
				var finalPoint = this;
				var opjObj = $.ZUI.bindOptions(this);
				var outArray = [];
				var inArray = [];
				var toolbars = [];
				$(this).find('th').each(function () {
					var that = this;
					var test = $(that).attr('data-options');
					var testArray = test.split(',');
					var testObj = new Object();
					for (var i = 0; i < testArray.length; i++) {
						var lastThings = testArray[i].split(':');
						var tempName = lastThings[0];
						testObj[tempName] = lastThings[1];
					}
					var formatter = $(that).attr('formatter');

					if (typeof formatter != 'undefined') {

						testObj.formatter = function (arg1, arg2) {
							return callBlack[formatter](arg1, arg2);
							//return $.ZUI.bindcallBlack(formatter, arg1, arg2).func();
						};


					}

					testObj.title = $(that).html();
					inArray.push(testObj);
				});
				outArray.push(inArray);
				opjObj.columns = outArray;
				var toolbarId = opjObj.toolbar;
				$(toolbarId).find("a[class*=zui-toolbar]").each(function () {
					var toolbar = new Object();
					var that = this;
					var id = $(that).attr('id');
					var text = $(that).attr('text');
					var iconCls = $(that).attr('iconCls');
					var buttonCls = $(that).attr('buttonCls');
					var handler = $(that).attr('handler');
					toolbar.id = id;
					toolbar.text = text;
					toolbar.iconCls = iconCls;
					toolbar.buttonCls = buttonCls;
					toolbar.handler = function (event) {
						try {
							var _resultValue = callBlack[handler]();

							return false;
						} catch (e) {
							return false;
						}

						//  $.ZUI.bindcallBlack(handler).func();
					};
					toolbars.push(toolbar);
				});
				opjObj.toolbar = toolbars;
				var test = opjObj.columns;
				$(finalPoint).html('');
				$(toolbarId).html('');
				$(finalPoint).ZTable(opjObj);

			});
		},

		/**
		 * 本地表单验证
		 * @return true|false
		 * */
		validateForm: function (target) {
			var checked = true,
				finalResult = $(target).ZDSValidatebox('validateAll', $(target));
			if (!finalResult) {
				checked = false;
			}
			return checked;
		},

		/**
		 * 获取form上面zdata-options绑定的数据
		 * @param target 如：form
		 * @return dataObj JSON数据
		 * */
		bindOptions: function (target) {
			//绑定zdata-option
			var opts = $(target).attr("zdata-options");
			opts = opts.replaceAll("'", "\"");
			return $.parseJSON(opts);
		},

		/**
		 * 清除验证提示信息
		 * @param target  $('dd.detail')
		 * */
		cleanValidateText: function (target) {
			$(target).find('span[class*="zd-validation"]').remove();
			$(target).find('.zui-mselect').removeClass('error');
			$(target).find('.zui-date').removeClass('error');
		},

		/**
		 * 日期格式转换
		 * 20160426==>2016-04-26
		 * */
		strToDate: function () {
			$("input[class*=strToDate]").each(function () {
				var that = this,
					oldValue = $(that).val(),
					oldYear,
					oldMonth,
					oldDay;
				if ('' != oldValue && null != oldValue && oldValue.indexOf("-") == -1) {
					oldValue = oldValue.replace(/(^\s*)|(\s*$)/g, "");
					oldYear = oldValue.substr(0, 4);
					oldMonth = oldValue.substr(4, 2);
					oldDay = oldValue.substr(6, 2);
					$(that).val(oldYear + '-' + oldMonth + '-' + oldDay);
				}
			});
		},
		dateToStr: function () {
			$("dd[class*=dateToStr]").each(function () {
				var that = this;
				var _oldValue = $(that).text();
				if (_oldValue.indexOf("-") == -1) {
					_oldValue = _oldValue.replace(/(^\s*)|(\s*$)/g, "");
					var _oldYear = _oldValue.substr(0, 4);
					var _oldMonth = _oldValue.substr(4, 2);
					var _oldDay = _oldValue.substr(6, 2);
					$(that).text(_oldYear + '-' + _oldMonth + '-' + _oldDay);
				}

			});
		}

	});
});
