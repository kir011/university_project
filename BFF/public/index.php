<?php

    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
    header("Access-Control-Allow-Headers: *");

    use App\Core\AppExtract;
    use App\controllers\Product;
    use App\controllers\User;

    require '../vendor/autoload.php';
    
    $app = new AppExtract;
    $controller = $app->controller();
    $method = $app->method();
    $params = $app->params();
    
    $requestMethod = $app->requestMethod();

    

    $product = new Product();
    $userController = new User();


    if($controller === "Product"){
        switch ($requestMethod) {
            case 'GET':
                if(count($params) !== 0){
                    echo json_encode($product->getProduct(intval($params[0])));
                }else{
                    echo json_encode($product->getProducts());
                }
                break;
            case 'POST':
                $data = file_get_contents("php://input");
                $data = json_decode($data, true);
                $productElement = new Product();
                $productElement->name = $data["name"];
                $productElement->archieve = $data["archieve"];
                $productElement->quantity = $data["quantity"];
                $productElement->description = $data["description"];
                $productElement->type = $data["type"];
                $productElement->price = $data["price"];
                echo json_encode($product->addProduct($productElement));
                break;
            case 'PUT':
                $data = file_get_contents("php://input");
                $data = json_decode($data, true);
                $productElement = new Product();
                $productElement->id = $data["id"];
                $productElement->name = $data["name"];
                $productElement->archieve = $data["archieve"];
                $productElement->quantity = $data["quantity"];
                $productElement->description = $data["description"];
                $productElement->type = $data["type"];
                $productElement->price = $data["price"];
                echo json_encode($product->updateProduct($productElement));
                break;
            case 'DELETE':
                $product->deleteProduct(intval($params[0]));
                break;
        }
    }
    
    
?>