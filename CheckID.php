<?php
  $conn = mysqli_connect("localhost","oseong","android12","foodhelper");

  $stdid = $_GET['stdID'];
  $sql ="SELECT stdID From std WHERE stdid ='$stdid'";
  $result = mysqli_query($conn,$sql);

if($result){
  $row = mysqli_fetch_array($result);
  if(is_null($row['stdID'])){
    echo "true";
  }
  else {
    echo "false";
  }
}else{
  echo mysqli_errno($conn);
}
 ?>
