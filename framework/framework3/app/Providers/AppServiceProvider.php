<?php

namespace App\Providers;

use Illuminate\Support\ServiceProvider;
use Illuminate\Session\Middleware\TerminableMiddleware;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     *
     * @return void
     */
    public function register()
    {
        $this->app->singleton(TerminableMiddleware::class);
    }

    /**
     * Bootstrap any application services.
     *
     * @return void
     */
    public function boot()
    {
        //
    }
}
