Ext.namespace("KE");
KE.app = (function() {
    return {
        /**
         * 初始化editor
         * @param initParam 初始参数。
         */
        init : function (initParam){
            setTimeout(function(){
                KindEditor.create('#' + initParam.renderTo, initParam);
            }, ((!initParam.delayTime || initParam.delayTime) <= 0 ? 5 : initParam.delayTime));
        },
        /**
         * 获取创建后的editor对象。
         * @param renderTO textarea的ID，根据此参数查找已创建的editor对象
         */
        getEditor : function(renderTO) {
            var editors = KindEditor.instances;
            for(var i = 0; i < editors.length; i++){
                if(editors[i].renderTo && editors[i].renderTo === renderTO){
                    return editors[i];
                }
            }
        }
    };
})();  