$(function() {
  const $registerForm =  $('#registerForm');
  if($registerForm.length) {
    $registerForm.validate({
         rules:{
           name: {
             minlength : 3
             },
           password: {
             minlength : 5,
             maxlength: 15
           },
           confirmPassword: {
             equalTo: '#password'
           }
           },
         messages: {
           name: {
             minlength: 'Name schould be at least 3 characters'
           },
           password: {
            minlength: 'Password schould be at least 5 characters',
            maxlength: 'Password schould be less then 15 characters'
           },
           confirmPassword: {
             equalTo: 'Password and confirm password should be same'
           }
         }
    })
  }
})