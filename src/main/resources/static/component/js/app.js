/**
 * 页面加载loading
 */
$(function () {
    setTimeout(function () {
        $(".preloader").css("display", "none");
    }, window == top ? 600 : 100);
});

//工具方法
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}


function printSelect(tag, data, key, value) {
    var target = document.getElementById(tag);
    var html = '<option value="">请选择</option>'
    for (var x = 0; x < data.length; x++) {
        var tempName = "";
        if (data[x][value] != undefined) {
            tempName = data[x][value];
        }
        html += '<option class="' + tag + 'Option" value="' + data[x][key] + '">' + tempName + '</option>'
    }
    target.innerHTML += html
    form.render();
}

function toJson(para) {
    try {
        eval('var temp = ' + para)
        if (temp) {
            var optionStr = JSON.stringify(temp)
            return optionStr;
        } else {
            layer.msg('字符串解析异常')
        }
    } catch (e) {
        layer.msg('字符串解析异常')
    }
    return false
}

//判断是否有值
function isnull(obj) {
    if (typeof (obj) == "undefined") {
        obj = "";
    }
    return obj;
}

//根据筛选条件做hash，避免事件注册的重复
function obHash(ob) {
    var str = JSON.stringify(ob)
    var h = 0
    var len = str.length
    var t = 2147483648
    for (var i = 0; i < len; i++) {
        h = 31 * h + str.charCodeAt(i)
        if (h > 2147483647) h %= t
    }
    return h
}

/**
 * 获取项目根路径(含端口),如“http://localhost:8080”
 * @returns {string}
 */
function getRootPath() {
    var strFullPath = window.document.location.href;
    var pathName = location.pathname;
    return strFullPath.split(pathName)[0];
}

/**
 * 获取项目IP（无端口）,如“http://localhost"
 * @returns {string}
 */
function getRootUrl() {
    var rootPath = getRootPath(), xieGang = "//";
    var rootPaths = rootPath.split(xieGang);
    var httpPrefix = rootPaths[0];//得到"http:"
    var pathNoPrefix = rootPaths[1];//得到"localhost:8080"
    var ip = pathNoPrefix.split(":")[0];
    var rootUrl = httpPrefix + xieGang + ip;
    // console.log("rootUrl:"+rootUrl);
    return rootUrl;
}

/**
 * 开启loading
 * @returns {*}
 */
window.showLoading = function () {
    let index = layer.load(1, {
        shade: [0.5, '#000'] //0.1透明度的背景
    });
    return index;
};

/**
 * 隐藏loading
 * @param index
 */
window.hideLoading = function (index) {
    layer.close(index);
};
