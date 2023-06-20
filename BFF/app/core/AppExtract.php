<?php
    namespace app\core;

    class AppExtract{
        private array $uri = [];
        private string $controller = '';
        private string $method = '';
        private string $requestMethod = '';
        private array $params = [];
        private int $sliceIndexStartFrom = 2;

        public function controller():string{
            $this->uri = explode('/', ltrim($_SERVER['REQUEST_URI'], '/'));
            if(isset($this->uri[0]) and ($this->uri[0] !== '')){
                $this->controller = ucfirst($this->uri[0]);
            }
            $namespaceAndController = "app\\controllers\\".$this->controller;
            if(class_exists($namespaceAndController)){
                $this->controller = $namespaceAndController;
            }
            return $this->controller;
        }

        public function method():string{
            if(isset($this->uri[1]) and ($this->uri[1] !== '')){
                $this->method = strtolower($this->uri[1]);
            }
            if($this->method === ''){
                $this->method = 'index';
            }
            if(!method_exists($this->controller, $this->method)){
                $this->uri = explode('/', ltrim($_SERVER['REQUEST_URI'], '/'));
                $this->sliceIndexStartFrom = 1;
            }
            return $this->method;
        }

        public function requestMethod():string{
            $metodo = $_SERVER['REQUEST_METHOD'];
            $recurso = explode("/", substr(@$_SERVER['PATH_INFO'], 1));
            $conteudo = file_get_contents("php://input");

        
            
            switch($metodo) {
              case 'PUT':
                return 'PUT';
                break;
              case 'POST':
                return 'POST';
                break;
              case 'GET':
                return 'GET';
                break;
              case 'DELETE':
                return 'DELETE';
                break;
            case 'OPTIONS':
                return 'OPTIONS';
                break;
            default:
                header($_SERVER["SERVER_PROTOCOL"]." 404 Not Found");
                die('{"msg": "Método não encontrado."}');  
                break;
            }
        }

        public function params():array{
            $countUri = count($this->uri);
            $this->params = array_slice($this->uri,$this->sliceIndexStartFrom, $countUri);
            return $this->params;
        }
    }
?>