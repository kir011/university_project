<?php 

namespace app\controllers;

class Product{
    private string $uri = "http://localhost:8080/product/";

    public function getProduct(int $id){
        $url= $this->uri."getProduct/".$id;
        $ch = curl_init($url);
        header("Access-Control-Allow-Origin: *");
        header('Cache-Control: no-cache, must-revalidate'); 
        header("Content-Type: text/plain; charset=UTF-8");
        header("HTTP/1.1 200 OK");
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        $result = json_decode(curl_exec($ch));
        return $result;
    }

    public function getProducts():array{
        $url= $this->uri."getProduct";
        $ch = curl_init($url);
        header("Access-Control-Allow-Origin: *");
        header('Cache-Control: no-cache, must-revalidate'); 
        header("Content-Type: text/plain; charset=UTF-8");
        header("HTTP/1.1 200 OK");
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        $result = json_decode(curl_exec($ch));
        return $result;
    }

    public function addProduct(Product $product){
        try{
            $url= $this->uri."addProduct";
            $ch = curl_init($url);
            curl_setopt($ch, CURLOPT_URL, $url);
            curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json '));
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch, CURLOPT_POST, true);
            curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($product));
            curl_setopt($ch, CURLOPT_HTTPHEADER, array(
                'Content-Type: application/json',
                'Content-Length: ' . strlen(json_encode($product)))
            );
            $result = json_decode(curl_exec($ch));
            curl_close($ch);
            return json_encode($result);
        }catch(\RuntimeException $ex){
            die(sprintf('Http error %s with code %d', $ex->getMessage(), $ex->getCode()));
        }
    }

    public function deleteProduct(int $id){
        $url= $this->uri."deleteProduct/".$id;
        $ch = curl_init();

        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'  ));
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
        $result = curl_exec($ch);
        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
        curl_close($ch);
        
    }

    public function updateProduct(Product $product){
            $url= $this->uri."updateProduct";
            $ch = curl_init($url);
            header("Access-Control-Allow-Origin: *");
            curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT");
            curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($product));
            curl_setopt($ch, CURLOPT_URL, $url);
            curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
            curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
            $result = json_encode($product);
            curl_exec($ch);
            curl_close($ch);
            return $result;
    }

}

?>