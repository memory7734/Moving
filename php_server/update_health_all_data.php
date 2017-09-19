<?php
    $con = mysql_connect('localhost', 'root', 'wMucYYWBkJ31') or die(mysql_error);
    mysql_query("set names 'utf8'");
    mysql_select_db('movingmaster', $con);

    $response = array();

    if (isset($_POST['json'])) {
        $json = $_POST['json'];
        $de_json = json_decode($json);

        foreach ($de_json as $unit) {
            $userkey = $unit->userkey;
            $date_time = $unit->date_time;
            $activity = $unit->activity;
            $activite_energy = $unit->activite_energy;
            $cycling_distance = $unit->cycling_distance;
            $exercise_mintues = $unit->exercise_mintues;
            $walking_running_distance = $unit->walking_running_distance;
            $step = $unit->step;
            $blood_glucose = $unit->blood_glucose;
            $forced_vital_vapacity = $unit->forced_vital_vapacity;
            $blood_alcohol_content = $unit->blood_alcohol_content;
            $height = $unit->height;
            $weight = $unit->weight;

            $result = mysql_query("UPDATE user_daily_fit SET date_time = $date_time, activity = $activity, activite_energy = $activite_energy, 
                cycling_distance = $cycling_distance, exercise_mintues = $exercise_mintues, walking_running_distance = $walking_running_distance, 
                step = $step, blood_glucose = $blood_glucose, forced_vital_vapacity = $forced_vital_vapacity, blood_alcohol_content = $blood_alcohol_content,
                height = $height, weight =$weight WHERE userkey = $userkey");            
            
             if ($result) {
                $response["success"] = 1;  
                $response["message"] = "Movingmaster successfully updated."; 
                echo json_encode($response);
            } else {

            }
        }
    } else {
        $response["success"] = 0;  
        $response["message"] = "Required field(s) is missing"; 
        echo json_encode($response);
    }
?>