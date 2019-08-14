<?php
require "connection.php";

$email=$_POST['email'];
$password=$_POST['password'];

if(isset($email)&&isset($password)){
$stmt=$conn->prepare("SELECT * FROM tbl_login WHERE email=? and password=? ");
$stmt->bind_param("ss",$email,$password);
if(!$stmt->execute()){
    echo 'error';
}
echo 'success';

}else{
    echo 'error';
}

?>