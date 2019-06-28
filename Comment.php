<?php
  $conn = mysqli_connect("localhost","oseong","android12","foodhelper");

  $id = $_GET['id'];

  $sql ="SELECT * FROM comment_border WHERE id ='$id'";

  $result = mysqli_query($conn,$sql);
  $data = array();

if($result){
  while($row = mysqli_fetch_array($result)){
      array_push($data,
        array('id'=>$row[0],
        'stdID'=>$row[1],
        'comment'=>$row[2],
        'time'=>$row[3]
      ));
  }
  header('Content-Type: application/json; charset=utf8');
  $json = json_encode(array("comment"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
  echo $json;
}
else{
  echo mysqli_errno($conn);
}
 ?>
