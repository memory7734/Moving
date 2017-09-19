<?php
    header("Content-Type:text/html;charset=utf-8");
    if (isset($_POST['username']) && isset($_POST['phone']) && isset($_POST['password'])) 
{
        $username = $_POST['username'];
        $phone = $_POST['phone'];
        $password = $_POST['password'];
        
        $con = mysql_connect('localhost', 'root', 'wMucYYWBkJ31');
        mysql_select_db('movingmaster');
        mysql_query('set names utf8');
        $sql = "SELECT phone FROM user_info WHERE phone = '$_POST[phone]'";
        $result = mysql_query($sql,$con);
        $num = mysql_num_rows($result);
        if ($num) {
            echo "用户已存在";
        } 
         else {
            $sql_insert = "INSERT INTO user_info (phone, username, password) VALUES ('$_POST[phone]','$_POST[username]', '$_POST[password]')";
            $res_insert = mysql_query($sql_insert);
            $sql = "SELECT userkey FROM user_info WHERE phone = $phone";
            $res = mysql_query($sql);
	   $re=mysql_result($res,0);
            if (!$res_insert) {
                echo "注册成功";
		echo json_encode($re);
                            } 
             else {
                echo "系统繁忙，请稍后再试";
            }
        }
    } else {
        echo "提交未成功";
    }
?>
