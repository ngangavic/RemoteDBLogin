<?php
require "connection.php";

$name=$_POST['name'];
$email=$_POST['email'];
$password=$_POST['password'];

if(isset($name)&&isset($email)&&($password)){
$stmt=$conn->prepare("INSERT INTO tbl_login(name,email,password)VALUES(?,?,?) ");
$stmt->bind_param("sss",$name,$email,$password);
if(!$stmt->execute()){
    echo 'error';
}
echo 'success';

}else{
    echo 'error';
}


?>