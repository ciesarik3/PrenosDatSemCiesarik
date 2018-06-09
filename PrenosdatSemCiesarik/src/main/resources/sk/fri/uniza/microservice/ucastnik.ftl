<#-- @ftlvariable name="" type="sk.fri.uniza.microservice.Ucastnik" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Účastník</title>
      <link rel="stylesheet" type="text/css" href="/assets/view.css" media="all">
      <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.12/css/all.css" integrity="sha384-G0fIWCsCzJIMAVNQPfjH08cyYaUtMwjJwqiRKxxE/rx96Uroj1BtIQ6MLJuheaO9" crossorigin="anonymous">
      <script type="text/javascript" src="/assets/view.js"></script>
      <script type="text/javascript" src="/assets/jquery-3.3.1.min.js"></script>
   </head>
   <body id="main_body" >
      <img id="top" src="/assets/top.png" alt="">
      <div id="form_container">
         <h1><a>Form</a></h1>
         <div class="form_description">
            <h2>Podrobnosti o zúčastnených:</h2>
            <p></p>
         </div>
         <h2>id: ${ucastnik.id}!</h2> 
         <h2>Meno: ${ucastnik.meno}!</h2>
         <h2>Heslo: ${ucastnik.heslo}!</h2>
      </div>
         <a href="/ucastnik/list"><i class="fas fa-th-list fa-3x"></i></a>
      <img id="bottom" src="/assets/bottom.png" alt="">
   </body>
</html>