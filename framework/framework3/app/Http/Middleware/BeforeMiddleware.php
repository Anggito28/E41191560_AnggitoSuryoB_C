<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;

class BeforeMiddleware
{
    public function handle($request, Closure $next)
    {
        // Perform action 
        
        return $next($request);
    }
}
