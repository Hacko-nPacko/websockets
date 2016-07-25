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

    $("button.btn-connect").click(function() {
        var socket = new SockJS('/listen');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            $("button.btn-ctrl").toggleClass("hide");
            $("button.btn-stock").toggleAttr("disabled");
            getStockValues();
        });
    });

    $("button.btn-disconnect").click(function() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
        $("button.btn-ctrl").toggleClass("hide");
        $("button.btn-stock").toggleAttr("disabled");
    });

    function subscribe(code) {
        if (stompClient != null) {
            stompClient.subscribe('/value/' + code, function (message) {
                console.log(message);
                // updateStock(JSON.parse(greeting.body).content);
            });
        }
    }

    function unsubscribe(code) {
        stompClient.unsubscribe(code)
    }

    function getStockValues() {
        console.log(stompClient.send("/app/listen"));
    }

    function updateStock(message) {
        var response = document.getElementById('response');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message));
        response.appendChild(p);
    }
});