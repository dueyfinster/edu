var jam = {
    "packages": [
        {
            "name": "bootstrap",
            "location": "javascript/bootstrap"
        },
        {
            "name": "jquery",
            "location": "javascript/jquery",
            "main": "dist/jquery.js"
        }
    ],
    "version": "0.2.17",
    "shim": {}
};

if (typeof require !== "undefined" && require.config) {
    require.config({
    "packages": [
        {
            "name": "bootstrap",
            "location": "javascript/bootstrap"
        },
        {
            "name": "jquery",
            "location": "javascript/jquery",
            "main": "dist/jquery.js"
        }
    ],
    "shim": {}
});
}
else {
    var require = {
    "packages": [
        {
            "name": "bootstrap",
            "location": "javascript/bootstrap"
        },
        {
            "name": "jquery",
            "location": "javascript/jquery",
            "main": "dist/jquery.js"
        }
    ],
    "shim": {}
};
}

if (typeof exports !== "undefined" && typeof module !== "undefined") {
    module.exports = jam;
}