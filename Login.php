<?php
  $conn = mysqli_connect("localhost","oseong","android12","foodhelper");

  $stdid = $_POST['stdID'];
  $stdpassword = $_POST['stdPassword'];
  $result = mysqli_query($conn,"SELECT * FROM std WHERE stdID = '$stdid'");

//custom error code : 1000 - Success Login
//                    1001 - Can not found id
//                    1002 - Incorrect password
  if($result){
    $row = mysqli_fetch_array($result);
    if(is_null($row['stdID'])){
      echo "1001";
    }
    else{
      if($row['stdPassword']==$stdpassword){
        echo "1000";
      }
      else{
        echo "1002";
      }
    }
  }
  else{
    echo mysqli_error($conn);
  }
 ?>
