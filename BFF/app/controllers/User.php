<?php 

namespace app\controllers;

class User{
    private string $uri = "http://localhost:8080/user/";
    

    public function addUser(user $user){
        try{
            $url= $this->uri."createdUser";
            $ch = curl_init($url);
            curl_setopt($ch, CURLOPT_URL, $url);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch, CURLOPT_POST, true);
            curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($user));
            curl_setopt($ch, CURLOPT_HTTPHEADER, array(
                'Content-Type: application/json',
                'Content-Length: ' . strlen(json_encode($user)))
            );
            $result = json_decode(curl_exec($ch));
            curl_close($ch);
            return json_encode($result);
        }catch(\RuntimeException $ex){
            die(sprintf('Http error %s with code %d', $ex->getMessage(), $ex->getCode()));
        }
    
    }

    public function auth(user $user){
        try{
            $url= $this->uri."auth";
            $ch = curl_init($url);
            curl_setopt($ch, CURLOPT_URL, $url);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch, CURLOPT_POST, true);
            curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($user));
            curl_setopt($ch, CURLOPT_HTTPHEADER, array(
                'Content-Type: application/json',
                'Content-Length: ' . strlen(json_encode($user)))
            );
            $result = json_decode(curl_exec($ch));
            curl_close($ch);
            return json_encode($result);
        }catch(\RuntimeException $ex){
            die(sprintf('Http error %s with code %d', $ex->getMessage(), $ex->getCode()));
        }
    }


}

?>