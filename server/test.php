<?php
//     getSmsThreads();
        
//     function getSmsThreads()
//     {
        $user = "j6ojFCdGbegeJGCM4CGkeFmb9Z96OE1bK4AYioO1:flJmDCcBIGnwW0ejBYT8UoPxluXhnWOlRzuFTLbp";
        $url = "https://api.parse.com/1/classes/ParseContacts";

        // Init curl
        $ch = curl_init();
        
        // set URL and other appropriate options
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_USERPWD, $user);

        $response = curl_exec($ch);
        
        echo $response;
        
        // close cURL resource, and free up system resources
        curl_close($ch);
        unset($ch);
        unset($response);
    // }	
?>