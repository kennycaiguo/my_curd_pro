/*一些小工具函数*/


function isEmpty(val){
     if(val==null || val == undefined || $.trim(val)==''){
         return true;
     }
     return false;
}

function notEmpty(val){
    return !isEmpty(val);
}