/*input 转 combotree 工具*/

/**
 * 表单中的 input 初始化为 combotree 通用, 排除自身节点，展开节点等
 * @param selector       input 输出框选择器
 * @param selectorValue  input 输入框值
 * @param excludeNodeId   需要排除的Node id, 新增表单 为空字符串，编辑表单为 对象id
 * @param dataUrl        combotree 数据接口，数据接口要返回完整的数据,不支持动态加载
 */
function initFormCombotree(selector,selectorValue,excludeNodeId,dataUrl){
    var easyTree = new EasyTree();
    $(selector).combotree({
        url: dataUrl,
        value: selectorValue,
        required:true,
        loadFilter: function (data, parent) {
            /*数据处理*/
            data = easyTree.treeDataBuild(data, "id", "pid", "text,state");
            return data;
        },
        onLoadSuccess:function(){
            var t = $("#pid").combotree('tree');
            var pNode;
            // 排除 当前 node
            if(excludeNodeId!=null &&  $.trim(excludeNodeId)!=""){
                /*console.log('remove tree node :'+nodeId);*/
                var node= t.tree("find",excludeNodeId);
                pNode = t.tree("getParent",node.target);
                t.tree("remove",node.target);
            };
            // 展开到 当前 node 的 父node
            if(pNode!=null){
                t.tree("expandTo",pNode.target).tree('select', pNode.target);
            }
        }
    });
}