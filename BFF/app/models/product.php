<?php

    class Product{
        private int $id;
        private string $name;
        private string $archieve;
        private int $quantity;
        private string $description;
        private string $type;
        private int $price;
        
        public function __construct(
            int $id,
            string $name,
            string $archieve,
            int $quantity,
            string $description,
            string $type,
            int $price
            ){
            $this->id = $id;
            $this->name = $name;
            $this->archieve = $archieve;
            $this->quantity = $quantity;
            $this->description = $description;
            $this->type = $type;
            $this->price = $price;
        }
    }

?>