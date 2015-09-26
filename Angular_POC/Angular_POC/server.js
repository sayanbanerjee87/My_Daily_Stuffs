/**
 * Created by SESA379886 on 7/25/2015.
 */

var http = require("http");
var mysql = require("mysql");
var express = require("express");
var app = express();
var connection = mysql.createConnection({
    host  : 'localhost',
    user  : 'root',
    password  : 'Sayan@007',
    database  : 'usermanagement'
});

  connection.connect(function(err){
      if(!err){
        console.log("Data Base is Connected!!!");
      }else{
        console.log("Error connecting DB!!!");
      }
  });

  connection.query('SELECT * from Persons', function(err, rows, fields) {
    if (!err)
      console.log('The solution is: ', rows);
    else
      console.log('Error while performing Query.');
  });

connection.end();

  app.listen(3000);
