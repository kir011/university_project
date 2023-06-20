<?php
    function formatException(Throwable $throw){
        var_dump("Archieve error {$throw->getFile()} in line{$throw->getLine()} : {$throw->getMessage()}");
    }
?>