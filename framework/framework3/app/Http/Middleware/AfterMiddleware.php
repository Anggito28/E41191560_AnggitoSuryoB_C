<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;

class AfterMiddleware
{
    public function handle($request, Closure $next)
    {
        $response =$next($request);

        //Perfom action

        return $response;
    }
}
