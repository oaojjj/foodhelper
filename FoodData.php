<?php
  $conn = mysqli_connect("localhost","oseong","android12","foodhelper");
  if (!$conn) {
     echo mysqli_connect_error();
     exit();
  }

  mysqli_set_charset($conn,"utf8");

  $sql = "select * from info_food";

  $result = mysqli_query($conn,$sql);
  $data = array();

  if($result) {
   while($row = mysqli_fetch_array($result)){
       array_push($data,
           array('id'=>$row[0],
           'cafe_type'=>$row[1],
           'kor_name'=>$row[2],
           'eng_name'=>$row[3],
           'type'=>$row[4],
           'price'=>$row[5],
           'info'=>$row[6],
           'avg_rating'=>$row[7]
       ));
        }
        header('Content-Type: application/json; charset=utf8');
        $json = json_encode(array("foodData"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
        echo $json;
 }
   else{
     echo mysqli_errro($conn);
   }

 ?>
