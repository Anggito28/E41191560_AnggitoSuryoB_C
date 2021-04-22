<?php

namespace Illuminate\Session\Middleware;

use Closure;
use Illuminate\Http\Request;

class StartSession
{
    public function handle(Request $request, Closure $next)
    {
        return $next($request);
    }

    public function terminate($request, $response)
    {

    }
}
