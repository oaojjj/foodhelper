<?php
  $conn = mysqli_connect("localhost","oseong","android12","foodhelper");

  $stdid = $_POST['stdID'];
  $stdpassword = $_POST['stdPassword'];
  $stdphone = $_POST['stdPhone'];

  $result = mysqli_query($conn,"
  INSERT INTO std
    (stdID,stdPassword,stdPhone)
      VALUES('$stdid','$stdpassword','$stdphone'
      )");

if($result){
  echo "success";
}else{
  echo mysqli_error($conn);
}
 ?>
