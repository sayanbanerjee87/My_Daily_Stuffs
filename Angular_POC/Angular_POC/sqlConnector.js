
var Sequelize = require('sequelize');
var sequelize = new Sequelize('usermanagement', 'root', 'Sayan@007', {
  host: 'localhost',

  pool: {
    max: 5,
    min: 0,
    idle: 10000
  }

});

var db = sequelize.import("persons");
db.persons.findAll({
  where: {
    authorId: 2
  }
});
