<?php
  $conn = mysqli_connect("localhost","oseong","android12","foodhelper");

  $id = $_GET['id'];
  $stdid = $_GET['stdID'];
  $comment=$_GET['comment'];

  $sql ="SELECT * FROM comment_border WHERE id='$id' AND stdID='$stdid'";

  $result = mysqli_query($conn,$sql);

if($result) {
    $row = mysqli_fetch_array($result);
    if(is_null($row)){
      $sql ="INSERT INTO `comment_border`(`id`, `stdID`, `comment`) VALUES ('$id','$stdid','$comment')";
      mysqli_query($conn,$sql);
      echo "true";
    }
    else {
      echo "false";
    }
  }
else {
  echo mysqli_errno($conn);
}
 ?>
