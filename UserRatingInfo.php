<?php
  $conn = mysqli_connect("localhost","oseong","android12","foodhelper");

  $id = $_GET['id'];
  $stdid = $_GET['stdID'];

  $sql ="SELECT * From rating_food WHERE id='$id' AND stdid ='$stdid'";
  $result = mysqli_query($conn,$sql);

if($result){
  $row = mysqli_fetch_array($result);
  if(is_null($row['stdID'])){
    echo "0";
  }
  else {
    echo $row['rating'];
  }
}else{
  echo mysqli_errno($conn);
}
 ?>
