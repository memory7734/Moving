<?php 
    $con = mysql_connect('localhost', 'root', 'wMucYYWBkJ31') or die(mysql_error);
    mysql_query("set names 'utf8'");
    mysql_select_db('movingmaster', $con);

    $response = array();

    $res = mysql_query("SELECT * FROM user_daily_fit") or die(mysql_error());

    if (mysql_num_rows($res) > 0) {
        $fit = array();
        while ($row = mysql_fetch_array($res)) {
            $fit['datetime'] = $row['datetime'];
            $fit['userkey'] = $row['userkey'];
            $fit['activity'] = $row['activity'];
            $fit['activite_energy'] = $row['activite_energy'];
            $fit['cycling_distance'] = $row['cycling_distance'];
            $fit['exercise_mintues'] = $row['exercise_mintues'];
            $fit['walking_running_distance'] = $row['walking_running_distance'];
            $fit['step'] = $row['step'];
            $fit['blood_glucose'] = $row['blood_glucose'];
            $fit['forced_vital_vapacity'] = $row['forced_vital_vapacity'];
            $fit['blood_alcohol_content'] = $row['blood_alcohol_content'];
            $fit['height'] = $row['height'];
            $fit['weight'] = $row['weight'];

            array_push($response, $fit);
        }
        echo json_encode($response);
    } else {
        $response['success'] = 0;
        $response['message'] = "No matching information found.";
        echo json_encode($response);
    }
?>