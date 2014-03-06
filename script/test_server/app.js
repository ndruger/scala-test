(function() {
  "use strict";
  var express, server;

  express = require("express");

  server = express();

  server.configure(function() {
    return server.use(express["static"](__dirname + "/public"));
  });

  server.listen(3000);

}).call(this);
