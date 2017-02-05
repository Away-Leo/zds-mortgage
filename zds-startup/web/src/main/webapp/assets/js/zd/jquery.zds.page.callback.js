define(function (require, exports, module) {
	var $ = require('jquery');

	function CallBack() {
		this.version = "1.0.0";
		this.name = "callBack";
	}

	module.exports = new CallBack();

});