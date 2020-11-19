/*
 * Copyright Neil Grogan
 */

exports.create = function(simpledb){ //TODO TODO TODO
	return function(req, res){
		console.log("in create attribs");
		console.log(req);
		var replace = (req.body.replace === 'true');
		if (req.body.attribname instanceof Array) {
			var attribsArray = [];
			for(var i=0; i<req.body.attribname.length; i++){
					newObj= {};
					newObj.Name = req.body.attribname[i];
					newObj.Value = req.body.attribvalue[i];
					newObj.Replace = replace;
					attribsArray.push(newObj);
			}
		} else {
			var attribsArray = [];
			newObj= {};
			newObj.Name = req.body.attribname;
			newObj.Value = req.body.attribvalue;
			newObj.Replace = replace;
			attribsArray.push(newObj);
		}
		

		console.log(req.body.domainname,  req.body.itemname, attribsArray);
        var params = {
            DomainName: req.body.domainname, // required
            ItemName: req.body.itemname, // required
            Attributes: attribsArray
        };
		simpledb.putAttributes(params, function(err, data) {
		  if (err){
		  	console.log(err, err.stack); // an error occurred
		  }else{ 
		  	console.log(data);           // successful response
		  	res.json(null);
		  }
		});
	};
};



exports.list = function(simpledb){
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
                "item" :  req.body.itemname
            });
            res.location("/attriblist");
		  }
		});
	};
};


exports.delete = function(simpledb){
	return function(req, res){
		console.log("in delete attribs");
		console.log(req.body.domainname,req.body.itemname,req.body.attribname,req.body.attribvalue);
		var params = {
		  DomainName:  req.body.domainname, // required
		  ItemName:  req.body.itemname, // required
		  Attributes: [{
		    Name: req.body.attribname,
		    Value: req.body.attribvalue
		    // ... more items ...
		  }]
		};
		simpledb.deleteAttributes(params, function(err, data) {
		  if (err){
		  	console.log(err, err.stack); // an error occurred
		  }else{ 
		  	console.log(data);           // successful response
		  	console.log('sent delete response');
		  	res.json(null);
		  }
		});
	};
};