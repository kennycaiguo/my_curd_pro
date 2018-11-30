/* datagrid 页面中 通用函数封装 */

/**
* 打开新增弹窗
* @param url
* @param width
* @param height
*/
function newModel(url,width,height) {
    popup.openIframe('新建', url, width || "500px",height || "400px")
}

/**
 * 打开编辑弹窗
 * @param dgId datagirdid
 * @param url
 * @param width
 * @param height
 */
function editModel(dgId,url,width,height){
    var rows= $("#"+dgId).datagrid("getSelections");
    if (rows.length==1) {
        popup.openIframe('编辑', url+'?id=' + rows[0].id, '500px', '300px');
    } else {
        popup.msg('请选择一行数据进行编辑');
    }
}


/**
 * 删除 Model
 * @param dgid
 * @param url
 */
function deleteModel(dgid,url) {
    var rows = $("#"+dgid).datagrid("getSelections");
    if (rows.length!=0) {
        popup.openConfirm(3, '删除', '您确定要删除选中的'+rows.length+'条记录吗?', function () {
            var ids = [];
            rows.forEach(function(row){
                ids.push(row.id);
            });
            $.post(url+'?ids=' + ids.join(','), function (data) {
                if(data.state=='ok'){
                    popup.msg('删除成功', function () {
                        $('#'+dgid).datagrid('reload');
                    });
                }else{
                    popup.msg('删除失败');
                }
            }, "json").error(function(){ popup.errMsg(); });
        });
    } else {
        popup.msg('请至少选择一行进行删除');
    }
}

/**
 * datagird 过滤
 * @param dgId datagrid id
 * @param inputsSpanId 查询条件父容器id
 */
function queryModel(dgId,inputsSpanId){
    var queryParams = {};
    var inputDomAry = $("#"+inputsSpanId+" input[name*=search_]");
    console.log(inputDomAry.length);
    var val;
    for(var i = 0,len = inputDomAry.length; i < len; i++){
        val = $(inputDomAry[i]).val();
        if($.trim(val)=='' || val==undefined || val==null){
            continue;
        }
        queryParams[$(inputDomAry[i]).attr("name")]=val;
    }
    $('#'+dgId).datagrid('load', queryParams);
}

// datagrid 筛选框 enter 监听
$(".searchInputArea").on("keydown", function (e) {
    var that = this;
    if (e.keyCode == 13) {
        $(".searchBtn",that).first().trigger('click');
    }
});