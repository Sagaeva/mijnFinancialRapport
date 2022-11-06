$(function() {
 $('#createdAt').datepicker({
   dateFormat: "dd/mm/yy",
   changeMonth: true,
   changeYear: true,
   maxDate: new Date()
 });

 const $expenseForm = $("#expenseform");

   if($expenseForm.length) {
   $expenseForm.validate({
        rules: {
          name: {
              minlength: 3
          }

   },
        messages:{
           name: {
           minlength:  'Expense name should minimum 3 characters'
           }
        }
})
}
})

