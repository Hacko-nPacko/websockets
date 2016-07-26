# websockets + spring 



## what is a websocket

## how to make one

## what is stomp

## what is sockjs

## how we do it in spring
 
## what is messaging

## 

## refs
- https://en.wikipedia.org/wiki/WebSocket
- http://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html
- https://stomp.github.io
- https://github.com/sockjs/sockjs-client
- https://www.nginx.com/blog/websocket-nginx/
- https://httpd.apache.org/docs/2.4/mod/mod_proxy_wstunnel.html
- https://github.com/Hacko-nPacko/websockets


### notes
26.4.11 User Destinations

An application can send messages targeting a specific user, and Spring’s STOMP support recognizes destinations prefixed with "/user/" for this purpose. For example, a client might subscribe to the destination "/user/queue/position-updates". This destination will be handled by the UserDestinationMessageHandler and transformed into a destination unique to the user session, e.g. "/queue/position-updates-user123". This provides the convenience of subscribing to a generically named destination while at the same time ensuring no collisions with other users subscribing to the same destination so that each user can receive unique stock position updates.

On the sending side messages can be sent to a destination such as "/user/{username}/queue/position-updates", which in turn will be translated by the UserDestinationMessageHandler into one or more destinations, one for each session associated with the user. This allows any component within the application to send messages targeting a specific user without necessarily knowing anything more than their name and the generic destination. This is also supported through an annotation as well as a messaging template.

client: /user/queue/position-update
destination: /queue/position-updates-{user}
send: /user/{user}/queue/position-updates
@SendToUser


clientInboundChannel
clientOutboundChannel

ThreadPoolExecutor

sendTimeLimit
sendBufferSizeLimit
messageSizeLimit

Load the actual Spring configuration with the help of the Spring TestContext framework, inject "clientInboundChannel" as a test field, and use it to send messages to be handled by controller methods.
Manually set up the minimum Spring framework infrastructure required to invoke controllers (namely the SimpAnnotationMethodMessageHandler) and pass messages for controllers directly to it.


- Spring TestContext framework, inject "clientInboundChannel" as a test field, and use it to send messages to be handled by controller methods.
- SimpAnnotationMethodMessageHandler