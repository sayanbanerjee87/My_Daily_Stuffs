/**
 * Created by SESA379886 on 9/6/2015.
 */
var mongoose = require('mongoose');
var schmea = mongoose.Schema;
var userModel = new schmea({
    firstName : {
      type: String
    },
    lastName :{
      type : String
    },
    address:{
      type: String
    },
    salary:{
      type:Number
    },
    acc_no:{
      type: Number
    },
    company:{
      type: String
    },
    status:{
      type:Boolean,
      default: true
    }
});

module.exports= mongoose.model('User',userModel);
