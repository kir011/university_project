<?php
    class user{
        private $login;
        private $password;
    
        public function __construct(
            string $login,
            string $password,
            ){
            $this->login = $login;
            $this->password = $password;
        }
    }
?>