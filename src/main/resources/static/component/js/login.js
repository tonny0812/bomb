layui.use(['form', 'element', 'jquery', 'form', 'layer','pearOper'], function () {
    var from = layui.form;
    var element = layui.element;
    var $ = layui.jquery;
    var form = layui.form;
    var layer = layui.layer;
    var pearOper = layui.pearOper;

    form.render();
    /**
     * 用户提交登录请求
     */
    form.on('submit(login-submit)', function (obj) {
        var loading;
        $.ajax({
            url: getRootPath() + "/login",
            data: obj.field,
            type: "post",
            dataType: "json",
            beforeSend: function () {
                loading = showLoading();
            },
            success: function (data) {
                if (data.code == 0) {
                    //登入成功的提示与跳转
                    layer.msg(data.msg, {
                        icon: 1
                        , time: 1000
                    }, function () {
                        location.href = getRootPath() + '/admin/index'; //后台主页
                    });
                } else {
                    layer.msg(data.msg);
                }
            },
            complete: function () {
                hideLoading(loading);
            },
            error: function (data) {
                layer.msg('错误', data.msg);
            }
        });
        return false;
    });
})
