/*
 * Copyright Neil Grogan
 */

exports.newdomain = function(req, res){
  res.render('domainform', { title: 'Add A New Domain', action: 'createadomain' });
};


exports.create = function(simpledb){
	return function(req, res){

		var params = {
		  DomainName:  req.body.domainname, // required
		};
		simpledb.createDomain(params, function(err, data) {
		  if (err){
		  	console.log(err, err.stack); // an error occurred
		  	res.render('domainlist', {
		  		"domainlist" :  new exports.list,
                "alerttype" : "alert-danger",
                "alertmessage" : "Failure creating domain"
            });
		  }else{ 
		  	console.log(data);           // successful response
		  	res.location("/");
            // And forward to success page
            res.redirect("/");
		  }
		});
	};
};

exports.delete = function(simpledb){
	return function(req, res){

		var params = {
		  DomainName:  req.body.domainname, // required
		};
		simpledb.deleteDomain(params, function(err, data) {
		  if (err){
		  	console.log(err, err.stack); // an error occurred
		  	res.render('index', { title: 'Error', data : err.stack });
		  }else{ 
		  	console.log(data);           // successful response
		  	res.location("/");
            // And forward to success page
            res.redirect("/");
		  }
		});
	};
};

exports.search = function(simpledb){
	return function(req, res){
		console.log("in search domain");
		console.log(req.body.query);
		var params = {
		  SelectExpression:  req.body.query // required

		};
		simpledb.select(params, function(err, data) {
		  if (err){
		  	console.log(err, err.stack); // an error occurred
		  }else{ 
		  	console.log(data);           // successful response
		  	//console.log(data.Items[0].Attributes[0])
	  	 	res.render('attriblist', {
                "searchlist" :  data.Items,
                "domain" :  req.body.domainname
            });
            res.location("/searchadomain");
		  }
		});
	};
};

exports.getattribs = function(simpledb){
	return function(req, res){
		console.log("in get attribs");
		console.log(req.body.domainname,req.body.itemname,req.body.attribname);
		var params = {
		  DomainName:  req.body.domainname, // required
		  ItemName:  req.body.itemname, // required
		  AttributeNames: [
		    req.body.attribname
		    // ... more items ...
		  ],
		  ConsistentRead: true,
		};
		simpledb.getAttributes(params, function(err, data) {
		  if (err){
		  	console.log(err, err.stack); // an error occurred
		  }else{ 
		  	console.log(data);           // successful response
	  	 	res.render('attriblist', {
                "attriblist" :  data.Attributes,
                "domain" :  req.body.domainname,
                "item" :  req.body.domainname
            });
            res.location("/attriblist");
		  }
		});
	};
};


exports.list = function(simpledb){
return function(req, res){
  	var params = {
	  MaxNumberOfDomains: 100,
	  NextToken: 'STRING_VALUE',
	};
	simpledb.listDomains(params, function(err, data) {
	  if (err){
	  	console.log(err, err.stack);
	  	res.render('index', { title: 'Error', data : err.stack }); // an error occurred
	  }else{
	  	console.log(data);           // successful response
	  	 res.render('domainlist', {
                "domainlist" :  data.DomainNames,
                "alerttype" : "alert-success",
                "alertmessage" : "Successfully loaded domains"
            });
	  }
	});
};
};