// call another service
service.register("toast", function(msg) {
    console.log("hi");
    console.log(msg);

    service.call("luna://com.webos.notification/createToast", {message:"hello"}, function(m2) {
        console.log(logHeader, "SERVICE_METHOD_CALLED:com.webos.notification/createToast");
        msg.respond({
            returnValue: true,
            Response: JSON.stringify(m2.payload)
        });
    });
});

service.register("alert", function(msg) {
    console.log("hi");
    console.log(msg);

    service.call("luna://com.webos.notification/createToast", {message:"are you fine?"}, function(m2) {
        console.log(logHeader, "SERVICE_METHOD_CALLED:com.webos.notification/createToast");
        msg.respond({
            returnValue: true,
            Response: JSON.stringify(m2.payload)
        });
    });
});
