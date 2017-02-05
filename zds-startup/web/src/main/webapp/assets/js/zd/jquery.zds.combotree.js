define(function (require, exports, module) {

	var $ = require('jquery');
	require('ztree');
	//内部构造方法
	function ZComboTree() {
		this.name = "ZComboTree";
		this.version = '1.0.0';
	}

	//定义基本对象
	$.ZComboTree = new ZComboTree();

	// 插件的定义
	$.fn.ZComboTree = function (options, param) {
		//如果为string，表示是方法调用
		if (typeof options == 'string') {
			var method = $.fn.ZComboTree.methods[options];
			if (method) {
				return method(this, param);
			}
		}
		options = options || {};

		var opts,
			valueField,
			textField,
			defaultvalue,
			expandall;

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

		if (!options.valueField) {
			valueField = $(this).data('valuefield');
			textField = $(this).data('textfield');
			options.valueField = valueField;
			options.textField = textField;
		}

		if (!options.value) {
			defaultvalue = $(this).data('defaultvalue');
			options.value = defaultvalue;
		}

		if (!options.expandAll) {
			expandall = $(this).data('expandall');
			if (expandall) {
				options.expandAll = expandall;
			}
		}
		opts = $.extend({}, $.fn.ZComboTree.defaults, options);
		opts.target = this;

		//判断是否已渲染
		var _opts= $(this).data("options");
		if(_opts){
			$(_opts.dlTarget).remove();
		}

		$(opts.target).data("options", opts);

		if (!opts.data) {
			if (!opts.url) {
				opts.url = $(this).data('url');
			}
			if (opts.url) {
				$.ajax({
					async: false,//默认为true，异步
					type: "get",
					dataType: "jsonp",
					url: opts.url,//要访问的后台地址
					data: opts.queryParams,
					crossDomain: true,
					success: function (data) {//msg为返回的数据，在这里做数据绑定
						opts.data = data;
						opts.target.each(function () {
							var method = $.fn.ZComboTree.methods["init"];
							method(this);
						});
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
						alert("请求出错：XMLHttpRequest.status=" + XMLHttpRequest.status + "\n XMLHttpRequest.readyState=" + XMLHttpRequest.readyState + "\n textStatus=" + textStatus);
					}
				});
				return;
			} else {
				opts.data = eval($(this).data('data'));
			}
		}
		return this.each(function () {
			var method = $.fn.ZComboTree.methods["init"];
			method(this);
		});


	};
	$.fn.ZComboTree.methods = {
		getValue: function (jq) {
			var _value = $(jq).val();
			if (!_value) {
				_value = $(jq).data('val');
			}
			return _value;
		},
		getText: function (jq) {
			return $(jq).data('text');
		},
		setValue: function (jq, value) {
			var opts = $(jq).next().find("ul").data("options");
			if (!opts) {
				return;
			}
			var treeObj = opts.treeObj,
				nodes = treeObj.transformToArray(treeObj.getNodes()),
				saveData = [],
				values = "",
				texts = "",
				_i = 0;
			$.each(nodes, function (i, item) {
				var val = item[opts.valueField];
				treeObj.checkNode(item, false, true);
				treeObj.cancelSelectedNode(item);
				if (val == value) {
					//如果是父节点并且不获取父节点的值 则跳过
					if (item.isParent && (!opts.parentValue)) {
						return true;
					}
					if (_i > 0) {
						values += ",";
						texts += ",";
					}
					if (opts.multiple) {
						treeObj.checkNode(item, true, true);
						saveData.push(item);
					} else {
						treeObj.selectNode(item, true);
						saveData = item;
					}
					values += val;
					texts += item[opts.textField];
					_i++;
				}
			});
			$(opts.treeTarget).data("treeData", opts.treeData);
			$(opts.target).attr("value", values);
			$(opts.treeTarget).parent().prev().children('a').text(texts);
		},
		init: function (jq) {
			var opts = $(jq).data('options'),
				text = opts.textField,
				dtHtml = '<dt><a class="zui-mselect" href="javascript:void (0);"></a></dt>',
				ulHtml = '';

			if(opts.width){
				dtHtml = '<dt><a class="zui-mselect" href="javascript:void (0);" style="width: '+opts.width+'px;"></a></dt>';
			}
			if(opts.height && opts.width){
				ulHtml='<dd class="zui-option" style="max-height: '+opts.height+'px;width:'+opts.width+'px;">';
			}else if(opts.height){
				ulHtml='<dd class="zui-option" style="max-height: '+opts.height+'px;">';
			}else if(opts.width){
				ulHtml='<dd class="zui-option" style="width:'+opts.width+'px;">';
			}else {
				ulHtml='<dd class="zui-option">';
			}
			ulHtml+='<ul id="zd' + getRandom(999) + '" class="ztree" style=" overflow:auto;overflow-x:hidden"></ul>';

			var html = '<dl class="zui-select-panel">' + dtHtml + ulHtml + '</dd></dl>';

			$(jq).hide();
			$(jq).after(html);
			opts.dlTarget = $(jq).next();
			opts.treeTarget = $(jq).next().find("ul");


			//创建ztree
			var checkStatus = false;
			if (opts.multiple) {
				checkStatus = true;
			}
			var setting = {
				check: {
					enable: checkStatus
				},
				data: {
					simpleData: {
						enable: true
					},
					key: {
						name: text
					}
				},
				callback: {
					onCheck: treeNodesCheck,
					onClick: treeNodeClick
				}
			};

			opts.setting = setting;
			opts.treeObj = $.fn.zTree.init(opts.treeTarget, setting, opts.data);

			//缓存options
			opts.target.data("options", opts);
			opts.treeTarget.data("options", opts);

			//展开ztree
			if (opts.expandAll) {
				opts.treeObj.expandAll(true);
			}

			//展开ztree
			opts.dlTarget.find('dt').find('a').on('click', function () {
				$(this).addClass('focus');
				$(this).parent('dt').parent('dl').css({
					zIndex: '100'
				});
				$(this).parent('dt').siblings('dd').slideDown();
			});

			//收起ztree
			opts.dlTarget.mouseleave(function () {
				var self;
				$(this).children('dt').children('a').removeClass('focus');
				self = $(this);
				self.find('dd').slideUp();
				self.css({
					zIndex: '1'
				});
			});

			//设置默认的值
			var method = $.fn.ZComboTree.methods["setValue"];
			method(opts.target, opts.value);
		}
	};

	// 插件的defaults
	$.fn.ZComboTree.defaults = {
		id: '',//唯一标识
		valueField: "id",//值字段
		textField: "text",//文本值
		url: null,//远程数据地址
		data: null,//本地数据
		multiple: false,//是否多选
		width: null,//下拉框宽度
		height: null,//下拉选项高度
		parentValue: true,//是否获取父节点的值
		expandAll: false,//是否展开全部
		value: null,//默认值
		treeData: {},//选中的节点集合
		treeObj: null,//构建ztree返回的对象
		target: null,//$('input[type=hidden]')
		dlTarget: null,//$('dl')
		treeTarget: null,//$('ul')
		setting: null//配置
	};

	/**
	 * ztree节点点击事件
	 * */
	function treeNodeClick(event, treeId, treeNode) {
		var opts = $(event.currentTarget).data('options');
		if (!opts.multiple) {
			//如果是父节点并且不获取父节点的值 则跳过
			if (treeNode.isParent && (!opts.parentValue)) {
				opts.treeData = null;
				$(opts.target).attr("value", "");
				$(opts.treeTraget).parent().prev().children('a').text("");
				return true;
			}
			var val = treeNode[opts.valueField];
			var text = treeNode[opts.textField];
			opts.treeData = treeNode;
			$(opts.treeTarget).data("treeData", opts.treeData);
			$(opts.target).attr("value", val);
			$(opts.treeTarget).parent().prev().children('a').text(text);
			$(opts.treeTarget).parent().hide();

			//清除验证失败提示信息
			if (val !== 'undefined' || val.length > 0) {
				try {
					$.ZUI.cleanValidateText(opts.target.parent());
				} catch (e) {
				}
			}

		}
	}

	/**
	 * ztree节点check事件
	 * */
	function treeNodesCheck(event, treeId, treeNode) {
		var opts = $(event.currentTarget).data('options');
		opts.treeData = opts.treeObj.getCheckedNodes(true);
		var values = "";
		var texts = "";
		var _i = 0;
		var saveData = [];
		$.each(opts.treeData, function (i, item) {
			//如果是父节点并且不获取父节点的值 则跳过
			if (item.isParent && (!opts.parentValue)) {
				return true;
			}
			if (_i > 0) {
				values += ",";
				texts += ",";
			}
			values += item[opts.valueField];
			texts += item[opts.textField];
			saveData.push(item);
			_i++;
		});
		opts.treeData = saveData;
		$(opts.treeTarget).data("options", opts);

		$(opts.target).attr("value", values);
		$(opts.treeTarget).parent().prev().children('a').text(texts);
		//清除验证失败提示信息
		if (values !== 'undefined' || values.length > 0) {
			try {
				$.ZUI.cleanValidateText(opts.target.parent());
			} catch (e) {
			}
		}
	}

	/**
	 * 获取随机数
	 * 1）获取0-100的随机数——getRandom(100);
	 * 2）获取0-999的随机数——getRandom(999);
	 * 3）以此类推…
	 * */
	function getRandom(n) {
		return Math.floor(Math.random() * n + 1)
	}

});

