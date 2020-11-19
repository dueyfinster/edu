
/**
 * Module dependencies.
 */

var express = require('express');
var routes = require('./routes');
var attributes = require('./routes/attributes');
var domains = require('./routes/domains');
var http = require('http');
var path = require('path');

var app = express();

// all environments
app.set('port', process.env.PORT || 3000);
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.json());
app.use(express.urlencoded());
app.use(express.methodOverride());
app.use(express.cookieParser('your secret here'));
app.use(express.session());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));
var AWS = require('aws-sdk'); 
AWS.config.loadFromPath('./awsconfig.json');
var simpledb = new AWS.SimpleDB({apiVersion: '2009-04-15'});

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

// Domains
app.get('/', domains.list(simpledb));
app.post('/deleteadomain', domains.delete(simpledb));
app.post('/searchadomain', domains.search(simpledb));
app.post('/createadomain', domains.create(simpledb));

// Attributes
app.post('/getattribs', attributes.list(simpledb));
app.post('/deleteattrib', attributes.delete(simpledb));
app.post('/createattribs', attributes.create(simpledb));
app.post('/updateattrib', attributes.create(simpledb));


http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});
