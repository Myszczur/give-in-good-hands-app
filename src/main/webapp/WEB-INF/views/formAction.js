$('input, select, textarea')
    .on('change',
        function(e){
            var inputs = $('input:text').map(
                function(){
                    return $(this).val();
                }).get().join(', ');
            var selects = $('select option:selected').map(
                function(){
                    return $(this).text();
                });
            var textareas = $('textarea').map(
                function(t){
                    return $(this).val();
                }).get().join();
            $('#FormSummary')
                .html('<p>Inputs: ' + inputs + '.</p><p>Selects: ' + selects + '.</p><p>Textareas: ' + textareas + '</p>');
        });