(function(global) {
	function fileQueueError(file, errorCode, message) {
		try {
			alert(errorCode);
			if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
				alert("You have attempted to queue too many files.\n"
						+ (message ===

						0 ? "You have reached the upload limit."
								: "You may select " + (message > 1 ? "up to " +

								message + " files." : "one file.")));
				return;
			}

			var progress = new FileProgress(file,
					this.customSettings.progressTarget);
			progress.setError();
			progress.toggleCancel(false);

			switch (errorCode) {
			case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
				progress.setStatus("File is too big.");
				this
						.debug("Error Code: File too big, File name: "
								+ file.name + ", File size: " + file.size
								+ ", Message: " + message);
				break;
			case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
				progress.setStatus("Cannot upload Zero Byte files.");
				this.debug("Error Code: Zero byte file, File name: "
						+ file.name +

						", File size: " + file.size + ", Message: " + message);
				break;
			case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
				progress.setStatus("Invalid File Type.");
				this
						.debug("Error Code: Invalid File Type, File name: "
								+ file.name

								+ ", File size: " + file.size + ", Message: "
								+ message);
				break;
			default:
				if (file !== null) {
					progress.setStatus("Unhandled Error");
				}
				this
						.debug("Error Code: " + errorCode + ", File name: "
								+ file.name

								+ ", File size: " + file.size + ", Message: "
								+ message);
				break;
			}
		} catch (ex) {
			this.debug(ex);
		}
	}

	function fileDialogComplete(numFilesSelected, numFilesQueued) {
		try {
			if (numFilesSelected > 0) {
				document.getElementById(this.customSettings.cancelButtonId).disabled

				= false;
			}

			/* I want auto start the upload and I can do that here */
			this.startUpload();
		} catch (ex) {
			this.debug(ex);
		}
	}
	function uploadError(file, errorCode, message) {
		// console.log('uploadError');
		alert(errorCode);
		try {
			switch (errorCode) {
			case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
				// console.log("Upload Error: " + message);
//				console.log("Error Code: HTTP Error, File name: " + file.name
//						+ ", Message: " + message);
				break;
			case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
				// console.log("Upload Failed.");
//				console.log("Error Code: Upload Failed, File name: "
//						+ file.name + ", File size: " + file.size
//						+ ", Message: " + message);
				break;
			case SWFUpload.UPLOAD_ERROR.IO_ERROR:
				// console.log("Server (IO) Error");
				// console.log("Error Code: IO Error, File name: " + file.name +
				// ",Message: " + message);
				break;
			case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
				// console.log("Security Error");
				// console.log("Error Code: Security Error, File name: " +
				// file.name +

				// ", Message: " + message);
				break;
			case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
				// console.log("Upload limit exceeded.");
				// console.log("Error Code: Upload Limit Exceeded, File name: "
				// +

				// file.name + ", File size: " + file.size + ", Message: " +
				// message);
				break;
			case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
				// console.log("Failed Validation. Upload skipped.");
				// console.log("Error Code: File Validation Failed, File name: "
				// +

				// file.name + ", File size: " + file.size + ", Message: " +
				// message);
				break;
			case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
				// If there aren't any files left (they were all cancelled)
				// disable the cancel button
				if (this.getStats().files_queued === 0) {
					document.getElementById

					(this.customSettings.cancelButtonId).disabled = true;
				}
				break;
			case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
				// console.log("Stopped");
				break;
			default:
				// console.log("Unhandled Error: " + errorCode);
				// console.log("Error Code: " + errorCode + ", File name: " +
				// file.name

				// + ", File size: " + file.size + ", Message: " + message);
				break;
			}
		} catch (ex) {
			this.debug(ex);
		}
	}
	if (typeof Array.isArray === 'undefined') {
		Array.isArray = function(arg) {
			return Object.prototype.toString.call(arg) === '[object Array]';
		};
	}
	if (typeof Array.remove === 'undefined') {
		Array.prototype.remove = function(dx) {
			if (isNaN(dx) || dx > this.length) {
				return false;
			}
			for ( var i = 0, n = 0; i < this.length; i++) {
				if (this[i] != this[dx]) {
					this[n++] = this[i];
				}
			}
			this.length -= 1;
		};
	}

	if (!window.ZDS) {
		window.ZDS = {};
	}

	/**
	 * 是否可用
	 */
	ZDS.isComptiable = function() {
		return true && jQuery;
	};

	/**
	 * 解析json string到object
	 */
	ZDS.parseJson = function(json_str) {
		if (JSON && typeof JSON.parese === 'function') {
			return JSON.parse(json_str);
		} else if (ZDS.isComptiable()) {
			return jQuery.parseJSON(json_str);
		}
		return 'undefined';
	};

	/**
	 * 解析json object到string
	 */
	ZDS.jsonStringify = function(json_obj) {
		if (JSON && typeof JSON.stringify === 'function') {
			return JSON.stringify(json_obj);
		} else if (ZDS.isComptiable()) {
			return jQuery.stringifyJSON(json_obj);
		}
		return 'undefined';
	};

	/**
	 * 命名空间函数
	 */
	ZDS.namespace = function(ns_string) {
		var parts = ns_string.split('.'), parent = ZDS, i, partsLen = parts.length;
		if (parts[0] === 'ZDS') {
			parts = parts.slice(1);
		}
		for (i = 0; i < partsLen; i += 1) {
			if (typeof parent[parts[i]] === 'undefined') {
				parent[parts[i]] = {};
			}
			parent = parent[parts[i]];
		}
		return parent;
	};

	/**
	 * 按需加载函数
	 */
	ZDS.require = function(file, callback, params) {
		var script = document.getElementsByTagName('script')[0], newjs = document
				.createElement('script');
		/* for IE */
		newjs.onreadystatechange = function() {
			if (newjs.readyState === 'loaded' || newjs.readState === 'complete') {
				newjs.onreadystatechange = null;
				callback.call(this, params);
			}
		};
		/* for other */
		newjs.onload = function() {
			callback.call(this, params);
		};
		newjs.src = file;
		script.parentNode.insertBefore(newjs, script);
	};

	/**
	 * @class 属性复制函数
	 * @constructor
	 * @param {Object}
	 *            原始对象
	 * @param {Object}
	 *            覆盖对象
	 * @param {Object}
	 *            默认值独享
	 * @return 覆盖后的原始对象
	 */
	ZDS.apply = function(o, c, defaults) {
		var args = Array.prototype.slice.call(arguments, 0);
		var arglen = args.length;
		var execludes = null;
		if (arglen == 4 && args[3]) {
			execludes = args[3];
		}
		if (defaults) {
			ZDS.apply(o, defaults);
		}
		if (o && c && typeof c == 'object') {
			for ( var p in c) {
				/* for IE type problem */
				if (execludes) {
					if (execludes.indexOf(p) == -1) {
						o[p] = c[p];
					}
				} else {
					// console.log('p:'+p+" pv:"+c[p]);swfUrl
					o[p] = c[p];
				}
			}
		}
		return o;
	};

	/**
	 * @class easyui datagrid表头右键菜单
	 * @constructor
	 * @return null
	 */
	function createColumnMenu() {
		var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo(
				'body');
		var fields = $('#center_table').datagrid('getColumnFields');
		for ( var i = 0, fieldsLen = fields.length; i < fieldsLen; i++) {
			$('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
		}
		tmenu.menu({
			onClick : function(item) {
				if (item.iconCls == 'icon-ok') {
					$('#center_table').datagrid('hideColumn', item.text);
					tmenu.menu('setIcon', {
						target : item.target,
						iconCls : 'icon-empty'
					});
				} else {
					$('#center_table').datagrid('showColumn', item.text);
					tmenu.menu('setIcon', {
						target : item.target,
						iconCls : 'icon-ok'
					});
				}
			}
		});
	}

	/* 开始上传 */
	function fileDialogComplete() {
		this.startUpload();
	}
	/* 上传中 */
	function uploadProgress() {
		var args = Array.prototype.slice.call(arguments, 0);
		var file = args[0];
		var bytesLoaded = args[1];
		var bytesTotal = args[2];
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);

		var createAble = !document.getElementById('p_' + file.name);
		var title = (percent === 100) ? '上传完成' : '正在上传';
		var uploadFilesHidden = document.getElementById('uploadFiles');
		var hasFiles = uploadFilesHidden.value;
		var idx = 0;
		if (hasFiles) {
			idx = ZDS.parseJson(hasFiles).length;
		}
		if (createAble) {
			var r = $('<div class="upload-label"></div>');
			a = $('<span id="pt_' + file.name + '" class="">' + file.name
					+ '</span>');
			var b = $('<span id="ps_' + file.name
					+ '" class="bd_upload attchUploadstyle"></span>');
			var pbody = $('<div id="p_' + file.name + '" class="fdbody"></div>');
			var c = $('<a id="r_' + file.name + '" idx="' + idx
					+ '" style="display:none;" href="#multi_file_wrap">删除</a>');

			b.append(pbody);
			b.css('display', 'block');
			$('#attach_res_list').append(r.append(c, ' ', a, ' ', b));
		}
		document.getElementById('pt_' + file.name).setAttribute('title', title);
		document.getElementById('p_' + file.name).style.width = percent + '%';
	}

	/* 上传完成 */
	function uploadSuccess() {
		var args = Array.prototype.slice.call(arguments, 0);
		var file = args[0], fileName = file.name;
		var data = args[1];
		// var response = args[2];
		var uploadFilesHidden = document.getElementById('uploadFiles');
		var fileValue = uploadFilesHidden.value;
		var hasFiles = Array();
		if (fileValue) {
			var hasFilesJson = ZDS.parseJson(fileValue);
			hasFiles = hasFiles.concat(hasFilesJson);
		}
		hasFiles.push(ZDS.parseJson(data)[0]);
		uploadFilesHidden.value = ZDS.jsonStringify(hasFiles);
		var progress = document.getElementById('ps_' + fileName);
		var removeBtn = document.getElementById('r_' + fileName);
		var removeIdx = parseInt(removeBtn.getAttribute('idx'), 10);
		if (progress) {
			progress.style.display = 'none';
			removeBtn.style.display = 'inline';
			var removeFunc = function() {
				var fu = document.getElementById('uploadFiles');
				var fv = fu.value;
				var fvArray = Array();
				if (fv) {
					fvArray = ZDS.parseJson(fv);
				}
				(delete fvArray[removeIdx]);
				fu.value = ZDS.jsonStringify(fvArray);
				var attachList = removeBtn.parentNode.parentNode;
				var uploadLabel = removeBtn.parentNode;
				attachList.removeChild(uploadLabel);
			};
			removeBtn.onclick = removeFunc;
		}
	}

	/**
	 * @class 基对象，提供isType函数判断是否为一个类型
	 * @constructor
	 * @return
	 */
	var obj = {
		isType : function(aType) {
			var self = this;
			while (self) {
				if (self == aType) {
					return true;
				}
				self = self.Type;
			}
			return false;
		}
	};
	window.ZDS.obj = obj;

	/**
	 * @class 可覆盖Class函数
	 * @constructor
	 * @param {Object}
	 *            基对象
	 * @param {Object}
	 *            继承对象
	 * @return 实现继承的Class
	 */
	function Class(baseClass, classDefine) {
		function class_() {
			this.Type = baseClass;
			for ( var member in classDefine) {
				this[member] = classDefine[member];
			}
		}
		class_.prototype = baseClass;
		return new class_();
	}
	window.ZDS.Class = Class;

	/**
	 * @class 实例化函数
	 * @constructor
	 * @param {Object}
	 *            Class
	 * @param {Object}
	 *            构造参数
	 * @return Class实例
	 */
	function New(aClass, params) {
		function new_() {
			this.Type = aClass;
			if (aClass.init) {
				aClass.init.apply(this, params);
			}
		}
		new_.prototype = aClass;
		return new new_();
	}
	window.ZDS.New = New;

	/**
	 * @class 作用域传递代理函数
	 * @constructor
	 * @param {Object}
	 *            调用函数
	 * @param {Object}
	 *            传递this
	 * @return 传递后的函数
	 */
	var proxy = function(func, thisObject) {
		return function() {
			return func.apply(thisObject, arguments);
		};
	};

	/**
	 * @class Dom构造工厂
	 * @constructor
	 * @return 实现继承的Class
	 */
	var DomFactory = {
		defaultsAttributes : {
			'form' : {
				method : 'post',
				action : 'getFile.do',
				target : 'ajaxUpload'
			},
			'text' : {
				'class' : 'form-text easyui-validatebox',
				className : 'form-text easyui-validatebox'
			},
			'hidden' : {
				type : 'hidden'
			},
			'select' : {},
			'checkbox' : {
				className : 'form-checkbox'
			},
			'radio' : {},
			'textarea' : {},
			'file' : {
				id : 'spanButtonPlaceHolder',
				className : 'file',
				placeholder : ''
			},
			'button' : {
				type : 'button'
			},
			'submit' : {
				type : 'submit'
			},
			'iframe' : {
				name : 'ajaxUpload',
				'style.display' : 'none',
				width : 0,
				height : 0
			},
			'date' : {
				className : 'easyui-datebox',
				type : 'text',
				editable : false,
				formatter : function(date) {
					return date.getFullYear() + '-' + (date.getMonth() + 1)
							+ '-' + date.getDay();
				},
				parser : function(date) {
					return new Date(Date.parse(date.replace(/-/g, "/")));
				}

			},
			'datetime' : {
				type : 'text',
				className : 'easyui-datetimebox',
				editable : false
			},
			'linkbutton' : {
				type : 'a',
				className : 'easyui-linkbutton'
			},
			'multifile' : {
				id : 'spanButtonPlaceHolder',
				placeholder : '',
				className : 'multi'
			}
		},
		/**
		 * method 构造DOM函数
		 * 
		 * @constructor
		 * @param {Object}
		 *            元素类型，提供基本Dom节点和easyuidom节点类型
		 * @param {Object}
		 *            属性对象
		 * @return 创建后的dom元素
		 */
		createDom : function(et, attributes) {
			var elementType = et == 'hidden' || et == 'button' || et == 'date'
					|| et == 'datetime' || et == 'submit' || et == 'text'
					|| et == 'radio' || et == 'checkbox' ? 'input' : et;
			if (elementType == 'linkbutton') {
				elementType = 'a';
			}
			if (elementType == 'file' || elementType == 'multifile') {
				elementType = 'span';
			}
			var dom = document.createElement(elementType);
			ZDS.apply(dom, attributes, this.defaultsAttributes[et],
					et == 'select' || et == 'textarea' || et == 'multifile'
							|| et == 'date' || et == 'datetime'
							|| et == 'linkbutton' ? 'type' : null);
			// dom.setAttribute('validType','maxLen[5]');
			// dom.getAttribute('validType')!='undefined' &&
			// alert(dom.validType);
			if (et == 'select') {
				if (attributes.url) {
					if (attributes.store && attributes['nullPropertyName']) {
						if (attributes.store.constructor === Array) {
							for ( var i = 0, storeLen = attributes.store.length; i < storeLen; i++) {
								var item = attributes.store[i];
								if (item[attributes['nullPropertyName']]) {
									var op = new Option(
											item[attributes.displayField],
											item[attributes.valueField]);
									dom.options.add(op);
								}
							}
						}
					}
					jQuery
							.getJSON(
									attributes.url,
									proxy(
											function(data) {
												var rootData = data;
												if (attributes
														.hasOwnProperty('root')) {
													rootData = data[attributes['root']];
												}
												if (rootData) {
													for ( var i = 0, storeLen = rootData.length; i < storeLen; i++) {
														var item = rootData[i];
														var op = new Option(
																item[attributes.displayField],
																item[attributes.valueField]);
														this.options.add(op);
													}
												}
											}, dom));
				} else if (attributes.store) {
					if (attributes.store.constructor === Array) {
						for ( var i = 0, storeLen = attributes.store.length; i < storeLen; i++) {
							var item = attributes.store[i];
							var op = new Option(item[attributes.displayField],
									item[attributes.valueField]);
							dom.options.add(op);
						}
					}
				}
			} else if (et == 'radio') {

			} else if (et == 'checkbox') {

			} else if (et == 'file' || et == 'multifile') {
				var attachResDiv = this.createDom('span', {
					id : 'attach_res_list'
				});
				var attachDom = this.createDom('span', {
					id : 'attach_div'
				});
				var attachHidden = this.createDom('hidden', {
					id : 'uploadFiles',
					name : 'uploadFiles'
				});
				attachDom.appendChild(attachHidden);
				attachDom.appendChild(dom);
				attachDom.appendChild(attachResDiv);
				dom = attachDom;
			} else if (et == 'linkbutton') {
				$(function() {
					$('#' + dom.id).linkbutton(attributes);
				});
			} else if (et == 'date') {
			} else if (et == 'datetime') {
			}
			if (attributes && !attributes.separateLabel && attributes['label']) {
				var spanDom = this.createDom('span');
				var labelDom = this.createDom('label', {
					'for' : dom['name'],
					'innerHTML' : attributes['label']
				});
				spanDom.appendChild(labelDom);
				spanDom.appendChild(dom);
				dom = spanDom;
			}
			/* 绑定事件 */
			if (attributes && attributes['handler']) {
				dom.onclick = attributes['handler'];
			}
			// console.log('',dom.getAttribute('validType'));
			return dom;
		},
		createField : function(queryFields) {
			var dom = null;
			if (queryFields && Array.isArray(queryFields)) {
				dom = this.createDom('div', {
					id : 'query_fields_div'
				});
				for ( var i = 0, fieldsLen = queryFields.length; i < fieldsLen; i++) {
					var item = queryFields[i];
					var field = this.createDom(item.type, item);
					dom.appendChild(field);
				}
			} else {
				dom = this.createDom(queryFields.type, queryFields);
			}
			return dom;
		},
		createFormItem : function(field) {
			var fieldConf = field;
			var formItemDiv = null;
			// ** 创建FieldDiv *//*
			var fieldDiv = this.createDom('div', {
				className : 'form-field'
			});

			/* 创建Field */
			if (fieldConf['type'] == 'checkbox' || fieldConf['type'] == 'radio') {
				formItemDiv = this.createDom('div', {
					className : 'form-item form-radio-checkbox-wrap'
				});
			} else {
				formItemDiv = this.createDom('div', {
					className : 'form-item'
				});
			}
			var field = this.createDom(fieldConf.type, fieldConf);
			fieldDiv.appendChild(field);
			if (fieldConf['type'] == 'file' || fieldConf['type'] == 'multifile') {
				var swfConfig = {};
				swfConfig.jsUrl = fieldConf['jsURL'];
				swfConfig.swfUrl = fieldConf['swfURL'];
				swfConfig.uploadUrl = fieldConf['uploadURL'];
				swfConfig.sessionId = fieldConf['sessionId'];
				$(function(glb, df) {
					// DomFactory.initSwfUpload(swfUrl,uploadUrl,sessionId);
					ZDS.require(swfConfig.jsUrl, df.initSwfUpload, swfConfig);
				}(window, this));
			} else if (fieldConf['type'] == 'multifile') {
				var jsUrl = fieldConf['jsURL'];
				var swfUrl = fieldConf['swfURL'];
				var uploadUrl = fieldConf['uploadURL'];
				var sessionId = fieldConf['sessionid'];
				ZDS.require(jsUrl, this.initSwfUpload(swfUrl, uploadUrl,
						sessionId));
			}
			formItemDiv.appendChild(fieldDiv);
			return formItemDiv;
		},
		createLayout : function(type, config) {
			var layoutConfig = config ? config : {};
			var layout = null;
			if (type === 'border') {
				var mainContainer = document.createElement('div');
				mainContainer.id = this.centerLayoutId;
				//mainContainer.className = 'easyui-layout';
				mainContainer.style.width = layoutConfig.width;
				mainContainer.style.height = layoutConfig.height;
				
				var naviDiv = this.createDom('div',{className:'combg wsubnav'});
				var naviSpan = this.createDom('span',{innerHTML:config.descriptionName});
				var naviLink = this.createDom('a',{className:'comicon'});
				naviDiv.appendChild(naviSpan);
				naviDiv.appendChild(naviLink);
				
				var northDiv =  this.createDom('div',{className:'s_area'});
				northDiv.id = CommonPage.northLayoutId;
				northDiv.style.overflow = 'hidden';
				
				var centerDiv = document.createElement('div');

				var centerTbl = document.createElement('table');
				var centerTblBody = document.createElement('tbody');
				
				centerTblBody.id = CommonPage.centerTableBodyId;
				centerTbl.appendChild(centerTblBody);

				centerTbl.id = CommonPage.centerTableId;
				var toolbarDiv = document.createElement('div');
				toolbarDiv.id = CommonPage.topBarId;
				toolbarDiv.className = 'chose_btn';
				
				centerDiv.appendChild(toolbarDiv);
				centerDiv.appendChild(centerTbl);

				mainContainer.appendChild(naviDiv);
				mainContainer.appendChild(northDiv);
				mainContainer.appendChild(centerDiv);
				layout = mainContainer;
			} else if (type === 'column') {
				var p = config.width, pw = p;
				var fields = config.fields;
				var fieldsLen = fields.length;
				var targetId = config.renderTo || config.applyTo;
				var target = targetId ? document.getElementById(targetId)
						: null, columnCt = target;
				if (Array.isArray(fields)) {
					for ( var i = 0; i < fieldsLen; i++) {
						var field = fields[i];
						if (!field.columnWidth) {
							pw -= field.width;
						}
					}
					if (!columnCt) {
						columnCt = this.createDom('div', {
							className : 'x-column-inner',
							width : config.width
						});
					}
					for ( var i = 0; i < fieldsLen; i++) {
						field = fields[i];
						var fieldDom = this.createDom('div', {
							className : 's-column'
						});
						if (field.columnWidth) {
							fieldDom.style.width = (Math
									.floor(field.columnWidth * pw));
						} else if (field.width) {
							fieldDom.style.width = field.width;
						}
						var fds = field.fields;
						if (fds) {
							for ( var j = 0; j < fds.length; j++) {
								var fd = fds[j];
								var fdDom = this.createFormItem(fd);
								fieldDom.appendChild(fdDom);
							}
						}
						columnCt.appendChild(fieldDom);
					}
				}
				layout = columnCt;
			} else if (type === 'table') {
				var tabColumns = config.columns?config.columns:2;
				var fields = config.fields;
				var rows = Math.floor(fields.length / tabColumns);
				var hasActions = Array.isArray(config.actions) && config.actions.length > 0 && config.bindAction; 
				if (hasActions) {
					rows += 1;
				}
				var tblCt = this.createDom('table');
				var tblTHead = this.createDom('thead');
				var tblTBody = this.createDom('tbody');

				var fieldPosition = 0;
				var preRows = hasActions?rows-1:rows;
				for ( var i = 0; i < preRows; i++) {
					var row = this.createDom('tr');
					for ( var j = 0; j < tabColumns; j++) {
						var col = this.createDom('td');
						var tdCls = fieldPosition%2===0?'conright':'conleft';
						col.className = tdCls;
						if(fieldPosition<fields.length)
						{
							var fieldConf = fields[fieldPosition];
							if(fieldConf.type=='hidden')
							{
								fieldPosition += 1;
								fieldConf = fields[fieldPosition];
							}
							var field = this.createField(fieldConf);
							col.appendChild(field);
							fieldPosition += 1;
						}
						row.appendChild(col);
					}
					tblTBody.appendChild(row);
				}
				if(hasActions)
				{
					/*查询按钮*/
					var actions = config.actions;
					var actionRow = this.createDom('tr');
					var actionCol = this.createDom('td',{className:'s_btn',colspan:tabColumns});
					actionCol.setAttribute('colspan',tabColumns);
					for(var i=0,actionLen = actions.length;i<actionLen;i++)
					{
						var actionConf = actions[i];
						var action = this.createDom('a',actionConf);	
						action.className = 'comicon';
						actionCol.appendChild(action);
					}
					actionRow.appendChild(actionCol);
					tblTBody.appendChild(actionRow);
					//alert(tblTBody.innerHTML);
				}
				tblCt.appendChild(tblTHead);
				tblCt.appendChild(tblTBody);
				return tblCt;
			}
			/*
			 * if(layoutConfig.renderTo) { $(function(layoutConfig,layout){ var
			 * renderTo = document.getElementById(layoutConfig.renderTo);
			 * if(renderTo) { renderTo.appendChild(layout); }
			 * }(layoutConfig,layout)); }
			 */
			return layout;
		},
		loadSwfUpload : function() {
			/* 加载swfupload js文件 */
			var script = this.createDom('script', {
				src : 'http://localhost:8080/cashback/static/js/swfupload.js'
			});
			document.documentElement.firstChild.appendChild(script);
		},
		initSwfUpload : function(flashURL, uploadURL, sessionId) {
			var args = Array.prototype.slice.call(arguments, 0);
			var swfURL = args[0].swfUrl;
			var uploadURL = args[0].uploadUrl;
			var sessionId = args[0].sessionId;
			var settings = {
				flash_url : swfURL,
				upload_url : uploadURL,
				post_params : {
					"jsessionid" : sessionId
				},
				file_size_limit : "2040MB",
				file_types : "*.*",
				file_types_description : "All Files",
				file_upload_limit : 100,
				file_queue_limit : 0,
				prevent_swf_caching : false,
				preserve_relative_urls : false,
				custom_settings : {
					progressTarget : "fsUploadProgress",
					cancelButtonId : "btnCancel"
				},
				debug : false,
				// Button settings
				button_image_url : "/zoa/common/image/attach.gif",
				button_width : "65",
				button_height : "29",
				button_placeholder_id : "spanButtonPlaceHolder",
				button_text : '<span class="theFont">添加附件</span>',
				button_text_style : ".theFont { font-size: 12;background-color:red;}",
				button_text_left_padding : 14,
				button_text_top_padding : 2,
				// The event handler functions are defined in handlers.js
				file_queued_handler : function() {
				},// fileQueued,
				file_queue_error_handler : fileQueueError,// function(){},//fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,// fileDialogComplete,
				upload_start_handler : function() {
				},// uploadStart,
				upload_progress_handler : uploadProgress,// uploadProgress,
				upload_error_handler : uploadError,// function(){},//uploadError,
				upload_success_handler : uploadSuccess,// uploadSuccess,
				upload_complete_handler : function() {
				},// uploadComplete,
				queue_complete_handler : function() {
				}
			};
			swfu = new SWFUpload(settings);
		}
	};

	

	/**
	 * method jsonp请求url构建函数
	 * 
	 * @constructor
	 * @param {Object}
	 *            原始请求url地址
	 * @return 构建完成后的jsonp请求url
	 */
	CommonPage.genericJsonpUrl = function(url) {
		if (url.indexOf('?') == -1) {
			url += '?jsoncallback=?';
		} else {
			url += '&jsoncallback=?';
		}
		return url;
	};

	/**
	 * method ajax请求方法
	 * 
	 * @constructor
	 * @param {String}
	 *            请求地址
	 * @param {String}
	 *            请求方法
	 * @param {Object}
	 *            请求数据
	 * @param {Function)
	 *            回调函数
	 * @return null
	 */
	CommonPage.request = function(url, method, data, callback) {
		$.ajax({
			type : method ? method : 'post',
			url : url,
			dataType : 'json',
			data : data,
			success : function(result) {
				callback.call(this, result);
			}
		});
	};

	CommonPage.removePreHandle = null;

	/**
	 * method ajax jsonp请求方法
	 * 
	 * @constructor
	 * @param {String}
	 *            请求地址
	 * @param {String}
	 *            请求方法
	 * @param {Object}
	 *            请求数据
	 * @param {Function)
	 *            回调函数
	 * @return null
	 */
	CommonPage.jsonpRequest = function(url, method, data, callback) {
		url = CommonPage.genericJsonpUrl(url);
		CommonPage.request(url, method, data, callback);
	};

	/* 作用域问题，提供静态查询保存方法，安全范围内可以重载 */
	// 保存请求地址
	CommonPage.saveUrlSuffix = '';
	// 查询单个对象请求地址
	CommonPage.loadUrlSuffix = '';
	// 分页查询请求地址
	CommonPage.loadPageUrlSuffix = '';
	// 删除单个对象地址
	CommonPage.removeUrlSuffix = '';
	// 请求地址前缀
	CommonPage.requestContext = '';

	/**
	 * method 静态条件查询方法
	 * 
	 * @return null
	 */
	CommonPage.staticQueryRecord = function() {
		var form = document.forms['query_fields_form'];
		if (form) {
			var queryType = 'filter';
			/* 过滤型 */
			var queryParam = {};
			var record = {};
			var els = form.elements;
			for ( var i = 0; i < els.length; i++) {
				if (els[i].type != 'select') {
					record[els[i].name] = els[i].value;
					var rule = els[i].rule ? els[i].rule : 'ALLLIKES';
					queryParam[queryType + '_' + rule + '_' + els[i].name] = els[i].value;
				} else if (els[i].type == 'select') {
				}
			}
			/*
			 * like this
			 * filter_ALLLIKES_JGH="+escape(escape(jgh))+"&filter_ALLLIKES_XM="+escape(escape(xm))+"&filter_EQUALI_YF="+yf+"&filter_EQUALI_NF="+nf
			 */
			CommonPage.queryGrid(queryParam);
		}
	};

	/**
	 * method 静态保存方法
	 * 
	 * @return null
	 */
	CommonPage.staticSaveRecord = function() {
		/* 验证 */
		var form = document.forms['save_edit_form'];
		var formId = form.getAttribute('id');
		if (!$(formId).form("validate")) {
			return;
		}
		if (form) {
			var record = {};
			var els = form.elements;
			for ( var i = 0, elsLen = els.length; i < elsLen; i++) {
				var el = els[i];
				if (el.type != 'select' && el.type != 'radio'
						&& el.type != 'checkbox') {
					if (el.name) {
						if (el.id && el.id === 'uploadFiles') {
							var uploadFilesValue = el.value;
							var filterArray = Array();
							var valueArray = ZDS.parseJson(uploadFilesValue);
							if (valueArray) {
								for ( var j = 0, valLen = valueArray.length; j < valLen; j++) {
									var val = valueArray[j];
									if (val) {
										filterArray.push(val);
									}
								}
								el.value = ZDS.jsonStringify(filterArray);
							}
						}
						record[el.name] = el.value;
					}
				}
			}
			var operURL = '';
			var form = document.forms['save_edit_form'];
			if (form.getAttribute('edit') == 'true') {
				operURL = CommonPage.editUrlSuffix;
			} else {
				operURL = CommonPage.saveUrlSuffix;
			}
			CommonPage.jsonpRequest(CommonPage.requestContext + operURL,
					'post', record, function(data) {
						CommonPage.refreshGrid();
					});
		}
	};

	/**
	 * method 静态刷新方法
	 * 
	 * @return null
	 */
	CommonPage.refreshGrid = function() {
		$('#center_table').datagrid('reload');
	};
	CommonPage.queryGrid = function(params) {
		$('#center_table').datagrid('load', params);
	};
	window.ZDS.CommonPage = CommonPage;
})(window);