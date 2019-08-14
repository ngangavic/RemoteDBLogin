<?php
$uname="root";
$db="android";
$host="localhost";
$password="";

$conn=mysqli_connect($host,$uname,$password,$db);

if(!$conn){
    echo 'error';
}
?>