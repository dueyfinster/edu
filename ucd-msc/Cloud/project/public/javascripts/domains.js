require(['jquery'], function ($) {
    require(['bootstrap/js/bootstrap-modal','bootstrap/js/bootstrap-transition']);
    $(function(){
        // start of http://jsfiddle.net/ukW2C/3/
        $("#searchInput").keyup(function () {
        //split the current value of searchInput
        var data = this.value.split(" ");
        //create a jquery object of the rows
        var jo = $("#fbody").find("tr");
        if (this.value == "") {
            jo.show();
            return;
        }
        //hide all the rows
        jo.hide();

        //Recusively filter the jquery object to get results.
        jo.filter(function (i, v) {
            var $t = $(this);
            for (var d = 0; d < data.length; ++d) {
                if ($t.is(":contains('" + data[d] + "')")) {
                    return true;
                }
            }
            return false;
        })
                    //show the rows that match.
                    .show();
                }).focus(function () {
                    this.value = "";
                    $(this).css({
                        "color": "black"
                    });
                    $(this).unbind('focus');
                }).css({
                    "color": "#C0C0C0"
                });
                // end of http://jsfiddle.net/ukW2C/3/

            // start of delete domain button listener & callback
            $(document).on('click', '.delete', function(){
                $(".modal-body").empty();
                var tr = $(this).closest('.arow');
                var domain =  $.trim(tr.find('.domain').text());

                var action = "deleteadomain";
                $("#submission-form").attr("action", "/" + action);

                $('.modal-header h4').text("Delete a domain");
                var modalMessage = "Are you sure you want to delete the domain ";

                var deleteDomainField = document.createElement("input");
                deleteDomainField.type="hidden";
                deleteDomainField.name="domainname";
                deleteDomainField.value=domain;


                $('.modal-body').html(modalMessage + "<strong>"+domain+"</strong>"+" ?");
                $('.modal-body').append(deleteDomainField);
                $('.submission').removeClass('btn-primary').addClass('btn-danger');
                $(".submission").html('Delete');

                $('form').off();
                $('form').submit(function() {
                    var actionSubmit = "/" + action;
                    $.ajax({
                        type: 'POST',
                        url: actionSubmit,
                        data: $('#submission-form').serialize(),
                        error: function(){
                           alert("Request Failed");
                        },
                        success: function(response){  
                            $(".alert").removeClass().addClass('alert alert-success alert-dismissable');
                            var message = "Domain "+domain+" is deleted successfully"
                            $(".alert strong").text(message);
                            $(tr).remove();
                           $('#myModal').modal('hide');
                        }
                    });
                    
                    return false;
                }); 
            });
            // end of delete domain button listener & callback

            // end of search attribs button listener & callback
            $(document).on('click', '.searchattribs', function(){
                $('form').off();
                $(".modal-body").empty();
                var tr = $(this).closest('.arow');
                var domain =  $.trim(tr.find('.domain').text());

                var action = "searchadomain";
                $("#submission-form").attr("action", "/" + action);

                $('.modal-header h4').text("Search a domain");
                var inputMessage = "select * from " + domain;

                var domainField = document.createElement("input");
                domainField.type="hidden";
                domainField.name="domainname";
                domainField.value=domain;

                var searchAttribsField = document.createElement("input");
                searchAttribsField.type="text";
                searchAttribsField.name="query";
                searchAttribsField.required=true;
                searchAttribsField.value=inputMessage;

                $('.modal-body').append(domainField);
                $('.modal-body').append(searchAttribsField);
                $('.submission').removeClass('btn-danger').addClass('btn-primary');
                $('.submission').html('Search');

                console.log(domain);
            });
            // end of search attribs button listener & callback

            // start of create domain button listener & callback
            $(document).on('click', '.create', function(){
                var action = "createadomain";
                $("#submission-form").attr("action", "/" + action);

                $('.modal-header h4').text("Create a domain");

                var createDomainField = document.createElement("input");
                createDomainField.type="text";
                createDomainField.name="domainname";
                $(".modal-body").html(createDomainField);

                $('.submission').removeClass('btn-danger').addClass('btn-primary');
                $(".submission").html('Create');
            });
            // end of create domain button listener & callback


            // start of retrieve attributes  listener & callback
            $(document).on('click', '.getattribs', function(){
                $('form').off();
                $(".modal-body").empty();
                var tr = $(this).closest('.arow');
                var domain =  $.trim(tr.find('.domain').text());
                var action = "getattribs";
                $("#submission-form").attr("action", "/" + action);

                $('.modal-header h4').text("Get an Attribute");


                $('<div/>', {
                    'class':'form-group groupDomain'
                }).appendTo('.modal-body');

                $('<label/>', {
                    'for':'inputDomain',
                    'class':'col-sm-2 control-label',
                    'text':'Domain Name'
                }).appendTo('.groupDomain');

                $('<div/>', {
                    'class':'col-sm-10 inputDomain'
                }).appendTo('.groupDomain');

                $('<input/>', {
                    'type':'text',
                    'name':'domainname',
                    'id':'inputDomain',
                    'class':'form-control',
                    'text':'Text Only',
                    'readonly':'readonly',
                    'required': true,
                    'value': domain
                }).appendTo('.inputDomain');


                $('<div/>', {
                    'class':'form-group groupItem'
                }).appendTo('.modal-body');

                $('<label/>', {
                    'for':'inputItem',
                    'class':'col-sm-2 control-label',
                    'text':'Item Name'
                }).appendTo('.groupItem');

                $('<div/>', {
                    'class':'col-sm-10 inputItem'
                }).appendTo('.groupItem');

                $('<input/>', {
                    'type':'text',
                    'name':'itemname',
                    'id':'inputItem',
                    'class':'form-control',
                    'required': true
                }).appendTo('.inputItem');


                $('<div/>', {
                    'class':'form-group groupAttribute'
                }).appendTo('.modal-body');

                $('<label/>', {
                    'for':'inputAttribute',
                    'class':'col-sm-2 control-label',
                    'text':'Attribute Name'
                }).appendTo('.groupAttribute');

                $('<div/>', {
                    'class':'col-sm-10 inputAttribute'
                }).appendTo('.groupAttribute');

                $('<input/>', {
                    'type':'text',
                    'name':'attribname',
                    'id':'inputAttribute',
                    'required': true,
                    'class':'form-control'
                }).appendTo('.inputAttribute');

                $('.submission').removeClass('btn-danger').addClass('btn-primary');
                $(".submission").html('Search');

                //console.log(domain);
            });
            // end of retrieve attributes button listener & callback

            // start of create attributes  listener & callback
            $(document).on('click', '.createattribs', function(){
                $(".modal-body").empty();
                var tr = $(this).closest('.arow');
                var domain =  $.trim(tr.find('.domain').text());
                var action = "createattribs";
                $("#submission-form").attr("action", "/" + action);

                $('.modal-header h4').text("Create Attribute(s)");


                $('<div/>', {
                    'class':'form-group groupDomain'
                }).appendTo('.modal-body');

                $('<label/>', {
                    'for':'inputDomain',
                    'class':'col-sm-2 control-label',
                    'text':'Domain Name'
                }).appendTo('.groupDomain');

                $('<div/>', {
                    'class':'col-sm-10 inputDomain'
                }).appendTo('.groupDomain');

                $('<input/>', {
                    'type':'text',
                    'name':'domainname',
                    'id':'inputDomain',
                    'class':'form-control',
                    'text':'Text Only',
                    'readonly':'readonly',
                    'required': true,
                    'value': domain
                }).appendTo('.inputDomain');


                $('<div/>', {
                    'class':'form-group groupItem'
                }).appendTo('.modal-body');

                $('<label/>', {
                    'for':'inputItem',
                    'class':'col-sm-2 control-label',
                    'text':'Item Name'
                }).appendTo('.groupItem');

                $('<div/>', {
                    'class':'col-sm-10 inputItem'
                }).appendTo('.groupItem');

                $('<input/>', {
                    'type':'text',
                    'name':'itemname',
                    'id':'inputItem',
                    'class':'form-control',
                    'required': true
                }).appendTo('.inputItem');

                $('<input/>', {
                    'type':'hidden',
                    'name':'replace',
                    'id':'replaceAttribute',
                    'value': false
                }).appendTo('.modal-body');


                // start of elements to be cloned
                $('<div/>', {
                    'id':'clonedInput', //create the first
                    'class':'clonedInput'
                }).appendTo('.modal-body');


                $('<div/>', {
                    'class':'form-group groupAttribute'
                }).appendTo('.clonedInput');

                $('<label/>', {
                    'for':'inputAttribute',
                    'class':'col-sm-2 control-label',
                    'text':'Attribute Name'
                }).appendTo('.groupAttribute');

                $('<div/>', {
                    'class':'col-sm-10 inputAttribute'
                }).appendTo('.groupAttribute');

                $('<input/>', {
                    'type':'text',
                    'name':'attribname',
                    'id':'inputAttribute',
                    'required': true,
                    'class':'form-control'
                }).appendTo('.inputAttribute');

                // start of attrib value
                $('<div/>', {
                    'class':'form-group groupAttributeVal'
                }).appendTo('.clonedInput');

                $('<label/>', {
                    'for':'inputAttribute',
                    'class':'col-sm-2 control-label',
                    'text':'Attribute Value'
                }).appendTo('.groupAttributeVal');

                $('<div/>', {
                    'class':'col-sm-10 inputAttributeVal'
                }).appendTo('.groupAttributeVal');

                $('<input/>', {
                    'type':'text',
                    'name':'attribvalue',
                    'required': true,
                    'id':'inputAttributeVal',
                    'class':'form-control'
                }).appendTo('.inputAttributeVal');

                $('<span/>', {
                    'class':'glyphicon glyphicon-plus clone'
                }).appendTo('.inputAttributeVal');
                $('<span/>', {
                    'class':'glyphicon glyphicon-minus cloneremove'
                }).appendTo('.inputAttributeVal');


                // start cloning logic
                var regex = /^(.*)(\d)+$/i;
                var cloneIndex = $(".clonedInput").length;

                $(document).on('click', '.clone', function(){
                    $(this).parents(".clonedInput").clone()
                        .appendTo(".modal-body")
                        .attr("id", "clonedInput" +  cloneIndex)
                        .find("*").each(function() {
                            var id = this.id || "";
                            var match = id.match(regex) || [];
                            if (match.length == 3) {
                                this.id = match[1] + (cloneIndex);
                            }
                    });

                    $( ".clonedInput:odd" ).css( "background-color", "#bbbbff" );
                    $( ".clonedInput:even" ).css( "background-color", "#ffffff" );

                    
                    cloneIndex++;
                });

                $(document).on('click', '.cloneremove', function(){
                    var length = $(".clonedInput").length;
                    $( ".clonedInput:odd" ).css( "background-color", "#bbbbff" );
                    $( ".clonedInput:even" ).css( "background-color", "#ffffff" );
                    if(length > 1){ // has to be at least one, can't remove all
                        $(this).parents(".clonedInput").remove();
                    }
                });

                $('.submission').removeClass('btn-danger').addClass('btn-primary');
                $(".submission").html('Create');

                $('form').off();
                $('form').submit(function() {
                    console.log('submitted create attribs');
                    var actionSubmit = "/" + action;
                    $.ajax({
                        type: 'POST',
                        url: actionSubmit,
                        data: $('#submission-form').serialize(),
                        error: function(){
                           alert("Request Failed");
                        },
                        success: function(response){  
                            console.log('clicked create attribs');
                            $(".alert").removeClass().addClass('alert alert-success alert-dismissable');
                            var fieldVal = $('#inputItem').val();
                            var message = "Item " + fieldVal +" is added successfully"
                            $(".alert strong").text(message);
                           $('#myModal').modal('hide');
                        }
                    });
                    
                    return false;
                }); 

                //console.log(domain);
            });
            // end of create attributes button listener & callback

    });
});