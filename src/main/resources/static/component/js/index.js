$(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    /**
     * 点击开始轰炸
     */
    $("#bombing").click(function () {
        //名称
        var name = $("#name").val();
        if (name == "" || name == null) {
            return layer.msg("请输入大佬名称");
        }
        //轰炸时间
        var attackTime = $("#attackTime").val();
        if (attackTime == "" || attackTime == null) {
            return layer.msg("必须输入时间");
        }

        var maxTime = $("body").attr("maxTime");
        if (Number(attackTime) < 1 || Number(attackTime) > Number(maxTime)) {
            return layer.msg("必须大于1分钟 小于" + maxTime + "分钟");
        }

        //轰炸号码
        var phone = $("#phone").val();
        if (phone == "" || phone == null) {
            return layer.msg("请输入号码");
        }

        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(phone)) {
            return layer.msg("请输入正确号码");
        }

        layer.confirm('确定要提交请求吗?', {icon: 3, title: '提示'}, function (index) {
            //异步创建
            var ii = layer.load(2, {
                shade: [0.1, '#fff']
            });
            $.ajax({
                type: "POST",
                url: getRootPath() + '/saveBomb',
                data: {
                    name: name,
                    attackTime: attackTime,
                    phone: phone
                },
                beforeSend: function(xhr) {
                    xhr.setRequestHeader(header, token);  //发送请求前将csrfToken设置到请求头中
                },
                dataType: "json",
                success: function (data) {
                    layer.close(ii);
                    if (data.code === 0) {
                        layer.msg(data.msg);
                    } else {
                        layer.msg(data.msg);
                    }
                },
                error: function () {
                    layer.close(ii);
                    layer.msg("服务器出错啦！！");
                }
            });
            layer.close(index);
        });

    });


    /**
     * 提交白名单
     */
    $("#submitWhitelist").click(function () {
        var whitelistPhone = $("#whitelistPhone").val();
        if (whitelistPhone == "" || whitelistPhone == null) {
            return layer.msg("请填写号码！！");
        }

        var ii = layer.load(2, {
            shade: [0.1, '#fff']
        });
        $.ajax({
            type: "POST",
            url: getRootPath() + '/saveWhitelist',
            data: {
                phone: whitelistPhone
            },
            dataType: "json",
            success: function (data) {
                layer.close(ii);
                if (data.code === 0) {
                    $("#whitelist").modal('hide');
                    layer.msg(data.msg);
                } else {
                    layer.msg(data.msg);
                }
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);  //发送请求前将csrfToken设置到请求头中
            },
            error: function () {
                layer.close(ii);
                layer.msg("服务器出错啦！！");
            }
        });
    });
});
