<?php
    $con = mysql_connect('localhost', 'root', 'wMucYYWBkJ31') or die(mysql_error);
    mysql_query("set names 'utf8'");
    mysql_select_db('movingmaster', $con);

    $response = array();

    if (isset($_POST['userkey']) && isset($_POST['password'])) {
        $userkey = $_POST['userkey'];
        $password = $_POST['password'];
        
        $res = mysql_query("UPDATE user_info SET password = '$password' WHERE userkey = $userkey");

        if($res) {
            $response["success"] = 1;  
            $response["message"] = "user_info successfully updated.";
            echo json_encode($response);
        } else {
            
        }
    } else {
         $response["success"] = 0;  
        $response["message"] = "Required field(s) is missing"; 
        echo json_encode($response);
    }
?>