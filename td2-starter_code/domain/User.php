<?php

namespace domain;
class User
{
    protected $login;
    protected $password;
    protected $name;
    protected $firstName;
    protected  $date;

    public function __construct($login, $password)
    {
        $this->login = $login;
        $this->password = $password;
    }

    public function getLogin()
    {
        return $this->login;
    }
}