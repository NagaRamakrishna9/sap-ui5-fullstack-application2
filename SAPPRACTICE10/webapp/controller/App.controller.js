sap.ui.define([
    "sap/ui/core/mvc/Controller",
    "sap/ui/core/UIComponent",
    "sap/m/MessageToast"
], function (Controller, UIComponent, MessageToast) {
    "use strict";

    return Controller.extend("RouteApp.controller.App", {
        onInit: function () {
          
        },

        onLoginPress: function () {
            var username = this.byId("userInput").getValue();
            var password = this.byId("passwordInput").getValue();

            var loginPayload = {
                clientname: username, 
                password: password
            };

            var that = this;
            fetch("http://localhost:8082/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(loginPayload)
            })
            .then(function (response) {
                if (!response.ok) {
                    if (response.status === 403) {
                        return response.json().then(function (data) {
                            MessageToast.show(data.message); 
                            return Promise.reject("Client is inactive");
                        });
                    }
                    return response.json().then(function (data) {
                        MessageToast.show(data.message); 
                    }); 
                }
                return response.json();  
            })
            .then(function (data) {
               
                if (data.success) {
        
                    that.getRouter().navTo("home");
                }
            })
            .catch(function (error) {
                if (error !== "Client is inactive") {
                    MessageToast.show("Invalid credentials. Please try again later.");
                }
            });
        },

        getRouter: function () {
            return UIComponent.getRouterFor(this);
        }
    });
});
