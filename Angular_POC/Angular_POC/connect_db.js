var express = require('express');
var app = express();
var http = require('http');
var router = express.Router();
var mongo = require('mongoose');
var port = process.env.port || 3000;
var userModel = require('./app/models/userModel');
var bodyParser = require('body-parser');

mongo.connect('mongodb://localhost/UserManagement',function(){
	console.log("Connected successfully !!!");
});

app.use(bodyParser.urlencoded({extended:true}));
app.use(bodyParser.json());
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

router.route("/users")
  .post(function(req,res){
    console.log(req.body);
    var users = new userModel(req.body);
    users.save();
    res.status(201).send(users);
  })
  .get(function(req,res){
    userModel.find(function(err,data){
        if(err){
          res.status(500).send(err);
        }else{
          res.json(data);
        }
    });
  })
  .put(function(req,res){
    console.log("Inside put API!!!");
    res.send("PUT is under construction!!!");
  })
  .delete(function(req,res){
    console.log("Inside delete API!!!");
    res.send("Delete is under construction!!!");
  });

app.use("/api",router);
app.listen(port,function(){
	console.log("App is listening to port : : : "+port);
});
