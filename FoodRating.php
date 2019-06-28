<?php
  $conn = mysqli_connect("localhost","oseong","android12","foodhelper");
  if (!$conn) {
      echo mysqli_errro($conn);
      exit();
  }

  $id = $_GET['id'];
  $stdid = $_GET['stdID'];
  $rating = $_GET['rating'];

  if($rating == '0'){
    $sql = "DELETE FROM rating_food WHERE stdID = '$stdid' AND id = '$id'";
    mysqli_query($conn,$sql);
    echo "true";
    exit();
  }
  else {
    $sql = "SELECT *FROM rating_food WHERE stdID = '$stdid' AND id = '$id'";
  }
  $result = mysqli_query($conn,$sql);

  if($result) {
    $row = mysqli_fetch_array($result);
    if(is_null($row)){
      $sql = "INSERT INTO rating_food(`id`, `stdID`, `rating`) VALUES ('$id','$stdid','$rating')";
      mysqli_query($conn,$sql);
      echo "true";
    }
    else {
      $sql = "UPDATE rating_food SET rating='$rating' WHERE stdID = '$stdid' AND id = '$id'";
      mysqli_query($conn,$sql);
      echo "true";
    }

    $sql = "SELECT AVG(rating) FROM rating_food WHERE id ='$id'";
    $avg = mysqli_fetch_array(mysqli_query($conn,$sql));
    $data = $avg[0];

    $sql = "UPDATE info_food SET avg_rating = '$data' WHERE id = '$id'";
    $result = mysqli_query($conn,$sql);
    // if($result){
    //   echo "success";
    // }else{
    //   echo"fail";
    // }


  }
   else{
     echo mysqli_error($conn);
   }

 ?>
