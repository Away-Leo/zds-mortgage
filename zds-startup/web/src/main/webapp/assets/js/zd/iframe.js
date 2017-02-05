define(function (require, exports, module) {

	var $ = require('jquery');

	function ZIframe() {
		this.name = "ZIframe";
		this.version = '1.0.0';
	}


	ZIframe.prototype = {
		/**
		 * ����iframe�����ݣ�����Ӧ�߶�
		 * */
		autoHeight: function (iframeId, minHeight) {
			var iframe = window.parent.document.getElementById(iframeId),
				height;
			if(!iframe){
				iframe = window.document.getElementById(iframeId);
			}
			height=iframe.contentWindow.document.documentElement.scrollHeight || iframe.contentWindow.document.documentElement.offsetHeight || iframe.contentWindow.document.documentElement.scrollHeight;

			if (height < minHeight){
				height = minHeight;
			}
			iframe.style.height = height + "px";
		},
		/**
		 * ��ʼ����ҳiframe�ĸ߶�
		 * */
		reinitIframe: function (iframeId,dHeight) {
			var iframe = window.parent.document.getElementById(iframeId);
			var clientHeight;
			if(!iframe){
				iframe = window.document.getElementById(iframeId);
			}
			clientHeight = window.innerHeight ||document.documentElement.clientHeight|| window.document.body.clientHeight;
			iframe.style.height = clientHeight - dHeight + "px";
			iframe.style.overflow = 'auto';
		}
	};
	module.exports = new ZIframe();

});
