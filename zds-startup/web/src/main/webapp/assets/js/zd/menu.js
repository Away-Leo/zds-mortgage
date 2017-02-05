define(function (require, exports, module) {
	var $ = require('jquery');
	var tabSwitch = require('zd/switch');

	function Menus(config) {
		this.config = $.extend({}, Menus.defaultConfig, config);
		this.init();
	}

	Menus.defaultConfig = {
		id: null,
		data: null,
		name: 'create',
		tabs: '#dynamic-tabs'
	};

	Menus.prototype = {
		init: function () {
			var that = this;
			$('#' + that.config.id).html('<ul class="first-menu"></ul>');

			//一级菜单
			$.each(that.config.data, function (i, item) {
				var html = [];
				if (!item.children) {
					html.push('<li>');
					html.push('<a id="zds' + item.id + '" data-path="' + item.path + '"  name="' + that.config.name + '" style="background-image:none">');
				} else {
					if (item.open) {
						html.push('<li class="active">');
					} else {
						html.push('<li>');
					}
					html.push('<a href="javascript:void(0);">');
				}
				if (item.iconCls) {
					html.push('<i class="' + item.iconCls + '"></i>');
				}
				html.push(item.label);
				html.push('</a>');

				//二级菜单
				if (item.children && item.children.length > 0) {
					if (item.open) {
						html.push('<ul class="second-menu" style="display: block;" >');
					} else {
						html.push('<ul class="second-menu" >');
					}
					$.each(item.children, function (j, app) {
						if (!app.children) {
							html.push('<li>');
							html.push('<a id="zds' + app.id + '" data-path="' + app.path + '" name="' + that.config.name + '" style="background-image:none">');
						} else {
							if (app.open) {
								html.push('<li class="active">');
							} else {
								html.push('<li>');
							}
							html.push('<a href="javascript:void(0);">');
						}
						if (app.iconCls) {
							html.push('<i class="' + app.iconCls + '"></i>');
						}
						html.push(app.label);
						html.push('</a>');

						//三级菜单
						if (app.children && app.children.length > 0) {
							if (app.open) {
								html.push('<ul class="three-menu" style="display: block;" >');
							} else {
								html.push('<ul class="three-menu" >');
							}
							$.each(app.children, function (k, _menu) {

								if (!_menu.children) {
									html.push('<li>');
									html.push('<a id="zds' + _menu.id + '" data-path="' + _menu.path + '" name="' + that.config.name + '" style="background-image:none">');
								} else {
									if (item.open) {
										html.push('<li class="active">');
									} else {
										html.push('<li>');
									}
									html.push('<a href="javascript:void(0);">');
								}
								if (_menu.iconCls) {
									html.push('<i class="' + _menu.iconCls + '"></i>');
								}
								html.push(_menu.label);
								html.push('</a>');


								//四级菜单
								if (_menu.children && _menu.children.length > 0) {
									if (_menu.open) {
										html.push('<ul class="four-menu" style="display: block;" >');
									} else {
										html.push('<ul class="four-menu" >');
									}
									$.each(_menu.children, function (l, _link) {

										if (!_link.children) {
											html.push('<li>');
											html.push('<a id="zds' + _menu.id + '" data-path="' + _link.path + '" name="' + that.config.name + '" style="background-image:none">');
										} else {
											if (_link.open) {
												html.push('<li class="active">');
											} else {
												html.push('<li>');
											}
											html.push('<a href="javascript:void(0);">');
										}
										if (_link.iconCls) {
											html.push('<i class="' + _menu.iconCls + '"></i>');
										}
										html.push(_link.label);
										html.push('</a>');
										html.push('</li>');
									});
									html.push('</ul>');
								}
								html.push('</li>');
							});
							html.push('</ul>');
						}
						html.push('</li>');
					});
					html.push('</ul>');
				}
				html.push('</li>');
				$('#' + that.config.id + ' ul[class="first-menu"]').append(html.join(""));
			});


			$('#' + that.config.id).find('a').click(function () {
				$(this).parent('li').children('ul').slideToggle('normal', 'linear');
				var li = $(this).parent('li');
				//li.addClass('menu-on').siblings('li').removeClass('menu-on');
				li.toggleClass('active');
			});


			$('#' + that.config.id).find('a[name=' + that.config.name + ']').on('click', function () {
				var self = $(this),
					tabItem = {
						id: self.attr('id').replace('zds',''),
						label: self.text(),
						path: self.data('path')
					};
				tabSwitch.create(that.config.tabs, tabItem);
			});

		}
	};

	module.exports = Menus;
});