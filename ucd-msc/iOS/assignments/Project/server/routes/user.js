
/*
 * GET users listing.
 */

exports.listcust = function(db) {
    return function(req, res) {
        var collection = db.get('customer');
        collection.find({},{},function(e,docs){
            res.json(docs);
        });
    };
};

exports.listloc = function(db) {
    return function(req, res) {
        var collection = db.get('location');
        collection.find({},{},function(e,docs){
            res.json(docs);
        });
    };
};


exports.listprice = function(db) {
    return function(req, res) {
        var collection = db.get('price');
        collection.find({},{},function(e,docs){
            res.json(docs);
        });
    };
};