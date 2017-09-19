<?php
    $con = mysql_connect('localhost', 'root', 'wMucYYWBkJ31') or die(mysql_error);
    mysql_query("set names 'utf8'");
    mysql_select_db('movingmaster', $con);

    $response = array();
    
    if (isset($_POST['userkey']) && isset($_POST['birthdate']) && isset($_POST['gender']) && isset($_POST['city']) && isset($_POST['wheelchair'])
        && isset($_POST['blood_type']) && isset($_POST['fitzpatrick_skin_type'])) {
            $userkey = $_POST['userkey'];
            $birthdate = $_POST['birthdate'];
            $gender = $_POST['gender'];
            $city = $_POST['city'];
            $blood_type = $_POST['blood_type'];
            $fitzpatrick_skin_type = $_POST['fitzpatrick_skin_type'];
            
            $result = mysql_query("UPDATE user_info SET birthdate = '$birthdate', 
                gender = '$gender', city = '$city', blood_type = '$blood_type', fitzpatrick_skin_type = '$fitzpatrick_skin_type'
                WHERE userkey = $userkey");

            if ($result) {
                $response["success"] = 1;  
                $response["message"] = "Movingmaster successfully updated."; 
                echo json_encode($response);
            } else {

            }
        } else {
             
        }
?>