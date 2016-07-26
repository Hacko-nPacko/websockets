/*!
 * toggleAttr() jQuery plugin
 * @link http://github.com/mathiasbynens/toggleAttr-jQuery-Plugin
 * @description Used to toggle selected="selected", disabled="disabled", checked="checked" etc…
 * @author Mathias Bynens <http://mathiasbynens.be/>
 */
jQuery.fn.toggleAttr = function(attr) {
    return this.each(function() {
        var $this = $(this);
        $this.attr(attr) ? $this.removeAttr(attr) : $this.attr(attr, attr);
    });
};

$(document).ready(function () {
    var stompClient = null;

    function connect() {
        var socket = new SockJS('/recv');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            $("button.btn-connect").addClass("hide");
            $("button.btn-disconnect").removeClass("hide");
            $("button.btn-stock").removeAttr("disabled");
        }, function() {
            setTimeout(connect, 2000);
        });
    }
    $("button.btn-connect").click(connect);

    $("button.btn-disconnect").click(function() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
        $("button.btn-ctrl").toggleClass("hide");
        $("button.btn-stock").toggleAttr("disabled");
        $("button.btn-stock.btn-subscribe").removeClass("hide");
        $("button.btn-stock.btn-unsubscribe").addClass("hide");
    });

    $("button.btn-subscribe").click(function() {
        if (stompClient != null) {
            var code = $(this).parents("tr").data("stock");
            var sub = stompClient.subscribe('/stock/' + code, function (message) {
                updateStock(JSON.parse(message.body));
            });
            var send = stompClient.send("/app/recv/" + code, {}, {});
            $(this).parents("tr").data("sub-id", sub);
            $("tr[data-stock=" + code + "]").find("button.btn-stock").toggleClass("hide");
        }
    });

    $("button.btn-unsubscribe").click(function() {
        if (stompClient != null) {
            var code = $(this).parents("tr").data("stock");
            var sub = $(this).parents("tr").data("sub-id");
            stompClient.unsubscribe(sub.id);
            $("tr[data-stock=" + code + "]").find("button.btn-stock").toggleClass("hide");
        }
    });

    function updateStock(message) {
        console.log(message);
        $("tr[data-stock=" + message.stock.code + "]").find("td.value").text(message.value);
    }
});