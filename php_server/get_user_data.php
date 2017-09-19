<?php
    $con = mysql_connect('localhost', 'root', 'wMucYYWBkJ31') or die(mysql_error);
    mysql_query("set names 'utf8'");
    mysql_select_db('movingmaster', $con);

    if (isset($_GET["userkey"])) {
        $userkey = $_POST['userkey'];

        $result = mysql_query("SELECT userkey, phone, username, birthdate, city, gender, blood_type, fitzpatrick_skin_type, wheelchair 
            FROM user_info WHERE userkey = $userkey");
        if (!empty($result)) {
            if (mysql_num_rows($result) > 0) {
                $result = mysql_fetch_array($result);
                $user_info  = array();
                $user_info['userkey'] = $result['userkey'];
                $user_info['phone'] = $result['phone'];
                $user_info['username'] = $result['username'];
                $user_info['birthdate'] = $result['birthdate'];
                $user_info['city'] = $result['city'];
                $user_info['gender'] = $result['gender'];
                $user_info['blood_type'] = $result['blood_type'];
                $user_info['fitzpatrick_skin_type'] = $result['fitzpatrick_skin_type'];
                $user_info['wheelchair'] = $result['wheelchair'];

                echo json_encode($user_info);
            }
        }
    }
?>