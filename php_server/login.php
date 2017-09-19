<?php
     header("Content-Type:text/html;charset=utf-8");
    if (isset($_POST['phone']) && isset($_POST['password'])) {
        $phone = $_POST['phone'];
        $password = $_POST['password'];
        $con = mysql_connect('localhost', 'root', 'wMucYYWBkJ31') or die(mysql_error);
        mysql_select_db('movingmaster', $con);
        mysql_query('set names utf8', $con);
        $sql = "SELECT userkey FROM user_info
            WHERE phone = '$phone' and password = '$password'";
        if (!$sql) {
            echo "sql语句有误.";
        }
        $result = mysql_query($sql, $con);
       if (mysql_num_rows($result) >=0)
       {
            echo "登陆成功!";
            echo $result;
                              }
        else {
            echo "登录失败！";
        }
    }

 else {
        echo "button 未传入！";     
    }
?>