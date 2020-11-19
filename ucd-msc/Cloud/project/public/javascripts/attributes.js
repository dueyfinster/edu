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

            // start of delete attribute button listener & callback
            $(document).on('click', '.deleteattrib', function(){
                $('form').off();
                $(".modal-body").empty();
                var tr = $(this).closest('.arow');
                var domainName =  $.trim(tr.find('.domain').text());
                var itemName =  $.trim(tr.find('.item').text());
                var attribName =  $.trim(tr.find('.attribname').text());
                var attribValue =  $.trim(tr.find('.attribvalue').text());

                

                $('.modal-header h4').text("Delete an attribute");
                var modalMessage = "Are you sure you want to delete the attribute ";

                var deleteDomainField = document.createElement("input");
                deleteDomainField.type="hidden";
                deleteDomainField.name="domainname";
                deleteDomainField.value=domainName;

                var deleteItemField = document.createElement("input");
                deleteItemField.type="hidden";
                deleteItemField.name="itemname";
                deleteItemField.value=itemName;

                var deleteAttribNameField = document.createElement("input");
                deleteAttribNameField.type="hidden";
                deleteAttribNameField.name="attribname";
                deleteAttribNameField.value=attribName;

                var deleteAttribValueField = document.createElement("input");
                deleteAttribValueField.type="hidden";
                deleteAttribValueField.name="attribvalue";
                deleteAttribValueField.value=attribValue;

                var mb = $('.modal-body');
                mb.html(modalMessage + "<strong>"+attribName+"</strong>"+" ?");
                mb.append(deleteDomainField);
                mb.append(deleteItemField);
                mb.append(deleteAttribNameField);
                mb.append(deleteAttribValueField);
                $('.submission').removeClass('btn-primary').addClass('btn-danger deleteattribsubmit');
                $(".submission").html('Delete');

                
                $('form').submit(function() {
                    var action = "deleteattrib";
                    var actionSubmit = "/" + action;
                    $.ajax({
                        type: 'POST',
                        url: actionSubmit,
                        data: $('#submission-form').serialize(),
                        error: function(){
                           alert("Request Failed");
                        },
                        success: function(response){  
                           console.log('clicked update', response);
                            $(".alert").removeClass().addClass('alert alert-success alert-dismissable');
                            var fieldVal = $(deleteAttribNameField).val();
                            var message = "Attribute "+fieldVal+" is deleted successfully";
                            $(".alert strong").text(message);
                            $('#myModal').modal('hide'); 
                            $(tr).remove();
                        }
                    });
                    
                    return false;
                });
            });



             // start of update attributes  listener & callback
            $(document).on('click', '.updateattribs', function(){
                $(".modal-body").empty();
                var tr = $(this).closest('.arow');
                var domain =  $.trim(tr.find('.domain').text());
                var item =  $.trim(tr.find('.item').text());
                var attribName =  $.trim(tr.find('.attribname').text());
                var attribValueField = tr.find('.attribvalue');
                var attribValue =  $.trim(tr.find('.attribvalue').text());
                var action = "createattribs";
                $("#submission-form").attr("action", "/" + action);

                $('.modal-header h4').text("Update an Item");


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
                    'readonly':'readonly',
                    'required': true,
                    'value': item
                }).appendTo('.inputItem');


                $('<div/>', {
                    'class':'form-group groupAttributeName'
                }).appendTo('.modal-body');

                $('<label/>', {
                    'for':'inputAttribute',
                    'class':'col-sm-2 control-label',
                    'text':'Attribute Name'
                }).appendTo('.groupAttributeName');

                $('<div/>', {
                    'class':'col-sm-10 inputAttributeName'
                }).appendTo('.groupAttributeName');

                $('<input/>', {
                    'type':'text',
                    'name':'attribname',
                    'id':'inputAttributeName',
                    'required': true,
                    'class':'form-control',
                    'readonly':'readonly',
                    'value': attribName
                }).appendTo('.inputAttributeName');
                // end of attribute name

                // start of attribute value
                $('<div/>', {
                    'class':'form-group groupAttributeValue'
                }).appendTo('.modal-body');

                $('<label/>', {
                    'for':'inputAttributeValue',
                    'class':'col-sm-2 control-label',
                    'text':'Attribute Value'
                }).appendTo('.groupAttributeValue');

                $('<div/>', {
                    'class':'col-sm-10 inputAttributeValue'
                }).appendTo('.groupAttributeValue');

                $('<input/>', {
                    'type':'text',
                    'name':'attribvalue',
                    'id':'inputAttributeValue',
                    'required': true,
                    'class':'form-control',
                    'value': attribValue
                }).appendTo('.inputAttributeValue');
                // end of attribute value

                $('<input/>', {
                    'type':'hidden',
                    'name':'replace',
                    'id':'replaceAttribute',
                    'value': true
                }).appendTo('.modal-body');

                $('.submission').removeClass('btn-danger').addClass('btn-primary updateattribsubmit');
                $(".submission").html('Update');

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
                           console.log('clicked update', response);
                            $(".alert").removeClass().addClass('alert alert-success alert-dismissable');
                            var fieldName = $('#inputAttributeName').val();
                            var fieldVal = $('#inputAttributeValue').val();
                            var message = "Attribute: " + fieldName +" is updated successfully with value: " + fieldVal;
                            $(".alert strong").text(message);
                            $('#myModal').modal('hide');
                            attribValueField.html(fieldVal);
                            
                        }
                    });
                    
                    return false;
                }); 
            });
            // end of update attributes button listener & callback
    });
});// end of require