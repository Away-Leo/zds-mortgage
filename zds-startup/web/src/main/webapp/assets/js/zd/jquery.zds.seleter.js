/**
 * Created by mapleRan on 2015/5/01.
 */
/**
 * add by mapleRan
 */

define(function (require, exports, module) {

    var $ = require('jquery');


    require("zd/jquery.zds.dialog");
    require("zd/jquery.zds.table");
    require("zd/jquery.zds.combobox");
    require("zd/jquery.zds.combotree");
    //初始化对象
    function Zseleter() {
        this.version = "1.0.0";
        this.name = "Zseleter";
    }
    //定义基本对象
    var _z=new Zseleter();
    $.Zseleter =_z ;

    $.fn.Zseleter = function (options, param) {
        //如果为string，表示是方法调用
        if (typeof options == 'string') {
            var method = $.fn.Zseleter.methods[options];
            if (method) {
                return method(this, param);
            }
            return;
        }
        options = options || {};
        var _opts=$(this).data("options");
        if(_opts&&_opts.isInit){
            if(!_opts.createNew){
                return;
            }
            options = $.extend({}, _opts, options);
        }
        var opts = $.extend({}, $.fn.Zseleter.defaults, options);
        opts.idField=opts.key;
        $(this).each(function(){
            var html=[];
            var height=opts.height;
            var width=opts.width;
            html.push('<div id="zd-seleter-dialog" style="display: none;">');
            if(opts.type=='org'){
                var ulHtml = '<ul  class="ztree mt5" id="zd-seleter-tree"  style="width:100%; overflow:auto;overflow-x:hidden"></ul>';
                html.push('<div style="position: relative;float: left;width: 75%;padding-right: 10px;padding-left: 10px;">'+ulHtml+'</div>');
            }else{
                html.push(' <form id="zd-seleter-search" onSubmit="return false;" class="zui-form form-search">');
                //
                if(opts.defSearchForm&&opts.defSearchForm[opts.type]){
                    $.each(opts.defSearchForm[opts.type],function(i,item){
                        html.push(' <dl class="form-item"> <dt class="title">'+item.label+'：</dt>');
                        switch(item.type){
                            case "input":
                                if(!item.defaultvalue){
                                    item.defaultvalue="";
                                }
                                html.push('<dd class="detail"><label><input type="text" class="zui-input zui-validatebox" name="'+item.name+'" value="'+item.defaultvalue+'" ></label></dd>');
                                break;
                            case "checkbox":
                                if(!item.value){
                                    item.value="";
                                }
                                html.push('<dd class="detail"><label><input   type="checkbox" class="zui-validatebox" value="'+item.value+'"     name="'+item.name+'"/></label></dd>');

                                break;
                            case "combobox":
                                if(!item.defaultvalue){
                                    item.defaultvalue="";
                                }
                                if(item.url){
                                    html.push('<dd class="detail"><input class="zd-combobox" data-url="'+item.url+'" data-valuefield="'+item.valueField+'" data-emptyValue="'+item.emptyValue+'" data-height="'+item.height+'"  data-textfield="'+item.textField+'" data-callback="'+item.callback+'" data-defaultvalue="'+item.defaultvalue+'" value="'+item.defaultvalue+'" type="hidden" class="zui-validatebox" name="'+item.name+'"/></dd>');
                                }else{
                                    html.push('<dd class="detail"><input class="zd-combobox" data-data="'+item.data+'" data-valuefield="'+item.valueField+'" data-textfield="'+item.textField+'" data-height="'+item.height+'"  data-callback="'+item.callback+'" data-defaultvalue="'+item.defaultvalue+'" value="'+item.defaultvalue+'" type="hidden" class="zui-validatebox" name="'+item.name+'"/></dd>');
                                }

                                break;
                            case "combotree":
                                if(!item.defaultvalue){
                                    item.defaultvalue="";
                                }
                                if(item.url){
                                    html.push('<dd class="detail"><input class="zd-combotree" data-url="'+item.url+'" data-height="'+item.height+'"  data-expandall="'+item.expandAll+'" data-valuefield="'+item.valueField+'" data-textField="'+item.textField+'" data-defaultvalue="'+item.defaultvalue+'" data-height="'+item.height+'" value="'+item.defaultvalue+'" type="hidden" data-callback="'+item.callback+'" class="zui-validatebox" name="'+item.name+'"/></dd>');
                                }else{
                                    html.push('<dd class="detail"><input class="zd-combotree" data-data="'+item.data+'" data-height="'+item.height+'"  data-expandall="'+item.expandAll+'" data-valuefield="'+item.valueField+'" data-textField="'+item.textField+'" data-defaultvalue="'+item.defaultvalue+'"  data-height="'+item.height+'" value="'+item.defaultvalue+'" type="hidden" data-callback="'+item.callback+'" class="zui-validatebox" name="'+item.name+'"/></dd>');
                                }

                                break;
                            default:
                                html.push('<dd class="detail"><label><input type="text" class="zui-input zui-validatebox" name="'+item.name+'"/></label></dd>');
                                break;
                        }
                        html.push('</dl>');
                    });
                    html.push('<div class="form-btn"><button type="button" class="btn-search-blue zd-select-btn" id="btn-submit">查询</button></div>');
                }
                html.push('</form>');
                var _id=$(this).attr("id");
                if(_id){
                    _id="zd-seleter-table"+_id;
                }else{
                    _id="zd-seleter-table";
                }
                opts.tableId=_id;
                html.push('<div class="os-content"><div id="'+opts.tableId+'" class="os-content-in"></div></div>');
            }
            var selectedAraeHeight=162;

            html.push('<div class="os-sidebar"><div class="borderAll mb20"><div class="zd-select-body" ><div class="zd-select-title selTitle">选中项</div><div class="selDetail" style="height: '+selectedAraeHeight+'px;overflow-y: auto"></div></div></div></div></div>');
            
            if(_opts&&_opts.isInit){
                $(_opts.target).remove();
            }

            $("body").append('<div>'+html.join("")+'</div>');

            opts.divTarget=$(this);
            opts.target=$("body").children("div:last-child");

            var dialogTarget=$(opts.target).find("div[id='zd-seleter-dialog']");
            $(dialogTarget).Zdialog({width:width,height:height,title:opts.title,onOpen:opts.onOpen,onClose:opts.onClose,createNew:false,closed:true,buttons:[{
                id:'message-btn',
                text:'确定',
                buttonCls: 'mr10 btn-blue',
                handler:function(){
                    var data= $(opts.target).Zseleter("getData");
                    opts.onOk(data);
                    $(dialogTarget).Zdialog("close");
                    $(opts.divTarget).after("<input id='zds_focus_temp' type='text'>");
                    //alert("123");
                    $("#zds_focus_temp").focus();
                    $("#zds_focus_temp").remove();
                }
            },{
                id:'message-btn',
                text:'取消',
                buttonCls: 'mr10 btn-gray',
                handler:function(){
                    $(dialogTarget).Zdialog("close");
                }
            }]});
            opts.dialogTarget=$(dialogTarget).Zdialog("getOptions").target;

            //alert($(opts.dialogTarget).html());
            //$(opts.target).find('#zd-seleter-table').ZTable("unselectRow",data[opts.key]);
            // $(this).data("options",opts);
            opts.isInit=true;
            $(opts.target).data("options",opts);
            $(this).data("options",opts);
            $(this).click(function(){
                //绑定事件
                if(opts.defSearchForm&&opts.defSearchForm[opts.type]){
                    $(opts.dialogTarget).find('#zd-seleter-search .zd-combobox').each(function(){
                        $(this).ZCombobox();
                    });
                    $(opts.dialogTarget).find('#zd-seleter-search .zd-combotree').each(function(){
                        $(this).ZComboTree();
                    });
                    $(opts.dialogTarget).find('#zd-seleter-search .zd-select-btn').click(function(){
                        var _data=$(opts.dialogTarget).find('#zd-seleter-search').serializeArray();
                        var params={};
                        $.each(_data, function(i, field){
                            params[field.name]=field.value;
                        });
                        params["page"]=1;
                        $(opts.dialogTarget).find('#'+opts.tableId).ZTable("reload",params);
                    });
                }
				//hlf 注释掉 因为jquery.zds.window.js重复打开了onOpen 事件。 20160524
                /*try{
                    opts.onOpen();
                }catch(e){

                }*/
                $(opts.target).Zseleter("open");

            });
            $("#"+opts.btnId).click(function(){
                //绑定事件
                if(opts.defSearchForm&&opts.defSearchForm[opts.type]){
                    $(opts.dialogTarget).find('#zd-seleter-search .zd-combobox').each(function(){
                        $(this).ZCombobox();
                    });
                    $(opts.dialogTarget).find('#zd-seleter-search .zd-combotree').each(function(){
                        $(this).ZComboTree();
                    });
                    $(opts.dialogTarget).find('#zd-seleter-search .zd-select-btn').click(function(){
                        var _data=$(opts.dialogTarget).find('#zd-seleter-search').serializeArray();
                        var params={};
                        $.each(_data, function(i, field){
                            params[field.name]=field.value;
                        });
                        params["page"]=1;
                        $(opts.dialogTarget).find('#'+opts.tableId).ZTable("reload",params);
                    });
                }
                //hlf 注释掉 因为jquery.zds.window.js重复打开了onOpen 事件。 20160524
                /*try{
                    opts.onOpen();
                }catch(e){

                }*/
                $(opts.target).Zseleter("open");
            });




        });
    };
    // 插件的defaults
    $.fn.Zseleter.methods = {
        addLabel:function(jq,data){
            var options= $(jq).data("options");
            var flog=$(options.target).Zseleter("hasLabel",data[options.key]);
            if(flog){
                return;
            }
            var _div=$(options.dialogTarget).find(".zd-select-body div:last-child");
            $(_div).append('<span id="'+data[options.key]+'" class="zd-label selItem" ><i class="label-close icon-menuclose-a"></i>'+data[options.value]+'</span>');
            $(_div).find("span:last-child i").click(function(){

                $(options.target).Zseleter("removeLabel",data[options.key]);
                if(options.type=='org'){
                    //var treeNode=options.zTreeObj.getNodeByTId(data[options.key]);
                    //var nodes = options.zTreeObj.getSelectedNodes();
                    options.zTreeObj.checkNode(data,false,true);
                    options.zTreeObj.cancelSelectedNode();
                }else{
                    $(options.dialogTarget).find('#'+options.tableId).ZTable("unselectRow",data[options.key]);
                }
            });
            $(_div).find("#"+data[options.key]).data("data",data);
        },
        removeLabel:function(jq,id){
            var options= $(jq).data("options");

            $(options.dialogTarget).find(".zd-select-body div:last-child span[id*='"+id+"']").remove();
        },
        hasLabel:function(jq,id){
            var options= $(jq).data("options");
            return $(options.dialogTarget).find(".zd-select-body div:last-child span[id*='"+id+"']").length;
        },
        clearLabel:function(jq){
            var options= $(jq).data("options");
            $(options.dialogTarget).find(".zd-select-body div:last-child span").remove();
        },
        getData:function(jq){
            var options= $(jq).data("options");
            var _data=[];
            $(options.dialogTarget).find(".zd-select-body div:last-child span").each(function(){
                var key=$(this).attr("id");
                var value=$(this).text();
                var data=$(this).data("data");
                var _obj={};
                _obj[options.key]=key;
                _obj[options.value]=value;
                _obj["_data"]=data;
                _data.push(_obj);
            });

            return _data;
        },
        open:function(jq){

            var opts= $(jq).data("options");

            $(opts.target).find("div[id='zd-seleter-dialog']").Zdialog("open");

            if(opts.type=='org'){
                var text = opts.value;
                var setting = {
                    async: {
                        enable: false,
                        contentType: "application/json",
                        url:opts.url,
                        dataType:"jsonp"
                    },
                    view: {
                        dblClickExpand: false,
                        showLine: true,
                        selectedMulti: false
                    },
                    data: {
                        simpleData: {
                            enable: true,
                            idKey: "id",
                            pIdKey: "pId",
                            rootPId: ""
                        }, key: {
                            name: text
                        }
                    },
                    callback: {
                        onCheck: function (event, treeId, treeNode) {
                            //alert("12313chek");
                            $(opts.target).Zseleter("clearLabel");
                            var nodes = opts.zTreeObj.getCheckedNodes(true);
                            $.each(nodes,function(i,item){
                                if (item.isParent && (opts.onlyLeafCheck)) {//如果是父节点并且不获取父节点的值 则跳过
                                    return true;
                                }
                                $(opts.target).Zseleter("addLabel",item);
                            });
                            // opts.treeData = saveData;

                        },
                        onClick: function (event, treeId, treeNode) {
                            if (treeNode.isParent && (opts.onlyLeafCheck)) {//如果是父节点并且不获取父节点的值 则跳过
                                return true;
                            }
                            if(opts.singleSelect){
                                $(opts.target).Zseleter("clearLabel");
                                $(opts.target).Zseleter("addLabel",treeNode);
                            }else{

                                if(!treeNode.checked){
                                    $(opts.target).Zseleter("addLabel",treeNode);
                                }else{
                                    $(opts.target).Zseleter("removeLabel",treeNode[opts.key]);
                                    opts.zTreeObj.cancelSelectedNode();
                                }
                                opts.zTreeObj.checkNode(treeNode,!treeNode.checked,true);
                            }
                        }
                    }
                };
                var check={};
                if (!opts.singleSelect) {
                    check.enable=true;
                }
                if(!opts.cascadeCheck){
                    check.chkboxType={ "Y": "", "N": "" };
                }
                setting.check=check;
                $.ajax({
                    async: false,//默认为true，异步
                    type: "get",
                    dataType: "jsonp",
                    url: opts.url,//要访问的后台地址
                    //每页条数，当前请求页数
                    data: opts.queryParams,
                    complete: function () {
                        //关闭遮挡层
                        // Loading.hide(options.id);
                    },
                    success: function (data) {
                        var zTreeObj= $(opts.dialogTarget).find('#zd-seleter-tree').ZDSTree(setting,data);
                        opts.zTreeObj=zTreeObj;

                        $(opts.target).data("options",opts);
                        data=opts.defaultValue();

                        if(data){
                            data=data.split(",");
                            var nodes=opts.zTreeObj.transformToArray(opts.zTreeObj.getNodes());

                            $.each(data,function(i,item){
                                $.each(nodes,function(k,treeNode){
                                    if(treeNode[opts.key]==item){
                                        opts.zTreeObj.checkNode(treeNode,true,true);
                                        $(opts.target).Zseleter("addLabel",treeNode);
                                    }
                                });
                            });
                        }
                    }
                });
            }else{
                var _data=$(opts.dialogTarget).find('#zd-seleter-search').serializeArray();
                var params={};
                $.each(_data, function(i, field){

                    params[field.name]=field.value;
                });
                $(opts.dialogTarget).find('#'+opts.tableId).ZTable({
                    columns:opts.columns[opts.type],
                    idField:opts.idField,
                    rowHeight:opts.rowHeight,
                    queryParams:params,//分页业务参数
                    url:opts.url,
                    rows:5,
                    rowcheck:false,
                    tableCls: 'table-index',
                    rownumbers:true,
                    singleSelect:opts.singleSelect,
                    pagination:true,
                    onSelect:function(index,data){
                        if(opts.singleSelect){
                            $(opts.target).Zseleter("clearLabel");
                        }
                        $(opts.target).Zseleter("addLabel",data);
                    },
                    onUnselect:function(index,data){
                        $(opts.target).Zseleter("removeLabel",data[opts.key]);
                    },
                    onLoadSuccess:function(){
                        var data= $(opts.target).Zseleter("getData");
                        $.each(data,function(i,item){
                            $(opts.dialogTarget).find('#'+opts.tableId).ZTable("selectRow",item[opts.key]);
                        });
                        data=opts.defaultValue();
                        var texts=opts.defaultText();
                        try{
                            if(data){
                                texts=texts.split(",");
                                data=data.split(",");
                                $.each(data,function(i,item){
                                    var _obj={};
                                    _obj[opts.key]=item;
                                    _obj[opts.value]=texts[i];
                                    $(opts.target).Zseleter("addLabel",_obj);
                                    $(opts.dialogTarget).find('#'+opts.tableId).ZTable("selectRow",item);
                                });
                            }
                        }catch(e){

                        }
                    }
                });
            }


        }
    };
    // 插件的defaults
    $.fn.Zseleter.defaults = {
        width:600,
        height:400,
        btnId:null,
        title:'选择器',
        target:null,
        url:null,//数据连接
        dialogTarget:null,
        selectData:null,
        singleSelect:false,
        key:"id",
        value:"label",
        type:"test",//类型,
        createNew:false,
        // 是否级联选择
        cascadeCheck : true,
        // 是否只选择子节点
        onlyLeafCheck : true,
        isInit:false,
        defaultValue:function(){

        },
        defaultText:function(){

        },
        onOk:function(){

        },
        onOpen:function(){

        },
        onClose:function(){

        },
        columns:{test:[[
            {field:'itemid',title:'状态',width:80},
            {field:'productid',title:'申请单号',width:80},
            {field:'unitcost',title:'项目名称',width:80,align:'right',formatter:function(r,v){
                return "<span style='font-size:12px;color:red'>"+v+"</span>";
            }},
            {field:'attr1',title:'客户',width:80},
            {field:'listprice',title:'金额',width:60,align:'center',formatter:function(r,v){
                //alert(r.itemid);
                return v;
            }}
        ]]},
        idField:null,
        defSearchForm:null,
        buttons:[{
            id:'message-btn',
            text:'确定',
            handler:function(){

            }
        },{
            id:'message-btn',
            text:'取消',
            buttonCls:'btn-gray',
            handler:function(){
                // alert($(this).parent("div .zd-window").parent().html());
                //$().Zdialog("close");
            }
        }]
    };

    module.exports=_z;
});